import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { CountryService } from '../service/country.service';
import { AddressService } from '../service/address.service';
import { UserAddressService, UserAddressPayload } from '../service/user-address.service';
import { AuthService } from '../service/auth.service';

interface Country {
  id: number;
  countryName: string;
}

interface Address {
  id: number;
  unitNumber: string;
  streetNumber: string;
  addressLine1: string;
  addressLine2: string;
  city: string;
  region: string;
  postalCode: string;
  countryId: number;
  isDefault?: boolean;
}

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, InputTextModule, ButtonModule, CardModule, DropdownModule, TableModule]
})
export class AddressComponent implements OnInit {
  addressForm!: FormGroup;
  countries: Country[] = [];
  addresses: Address[] = [];
  userAddresses: UserAddressPayload[] = [];
  userId: number | null = null;
  editingAddressId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private countryService: CountryService,
    private addressService: AddressService,
    private userAddressService: UserAddressService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.userId = this.authService.getUserId();

    if (this.userId) {
      this.addressForm = this.fb.group({
        addressLine1: ['', [Validators.required, Validators.maxLength(240)]],
        addressLine2: ['', Validators.maxLength(240)],
        city: ['', [Validators.required, Validators.maxLength(240)]],
        region: ['', [Validators.required, Validators.maxLength(240)]],
        postalCode: ['', [Validators.required, Validators.maxLength(20), Validators.pattern('^[0-9]+$')]],
        streetNumber: ['', [Validators.required, Validators.maxLength(240)]],
        unitNumber: ['', Validators.maxLength(240)],
        countryId: [null, Validators.required]
      });

      this.countryService.getCountries().subscribe((data: Country[]) => {
        this.countries = data;
      });

      this.loadUserAddresses();
    } else {
      console.error('Usuario no autenticado');
    }
  }

  loadUserAddresses(): void {
    if (this.userId) {
      this.userAddressService.getUserAddressesByUserId(this.userId).subscribe((data: UserAddressPayload[]) => {
        this.userAddresses = data;
        this.addresses = this.userAddresses.map(ua => ({
          id: ua.address.id,
          unitNumber: ua.address.unitNumber || '', // Valor por defecto
          streetNumber: ua.address.streetNumber || '', // Valor por defecto
          addressLine1: ua.address.addressLine1 || '', // Valor por defecto
          addressLine2: ua.address.addressLine2 || '', // Valor por defecto
          city: ua.address.city || '', // Valor por defecto
          region: ua.address.region || '', // Valor por defecto
          postalCode: ua.address.postalCode || '', // Valor por defecto
          countryId: ua.address.countryId !== undefined ? ua.address.countryId : 0, // Valor por defecto
          isDefault: ua.default
        }));
      });
    }
  }

  onSubmit(): void {
    if (this.addressForm.valid && this.userId !== null) {
      const formValue = this.addressForm.value;
      const addressPayload = {
        ...formValue,
        countryId: formValue.countryId.id
      };

      if (this.editingAddressId === null) {
        this.addressService.createAddress(addressPayload).subscribe(address => {
          this.addresses.push({ ...address, isDefault: false });
          this.addressForm.reset();
          this.linkAddressToUser(address.id);
        });
      } else {
        this.addressService.updateAddress(this.editingAddressId, addressPayload).subscribe(() => {
          this.loadUserAddresses(); // Recargar las direcciones después de la actualización
          this.resetForm();
        });
      }
    } else {
      this.addressForm.markAllAsTouched();
    }
  }

  linkAddressToUser(addressId: number): void {
    if (this.userId !== null) {
      const userAddressPayload: UserAddressPayload = {
        user: { id: this.userId! },
        address: { id: addressId },
        default: false
      };
      this.userAddressService.createUserAddress(userAddressPayload).subscribe(() => {
        this.loadUserAddresses();
      });
    }
  }

  deleteAddress(id: number): void {
    this.addressService.deleteAddress(id).subscribe(() => {
      this.addresses = this.addresses.filter(address => address.id !== id);
      this.loadUserAddresses();
    });
  }

  setDefaultAddress(address: Address): void {
    if (this.userId !== null) {
      const currentDefault = this.userAddresses.find(ua => ua.default);

      if (currentDefault) {
        const updatePayload: UserAddressPayload = {
          user: { id: this.userId! },
          address: { id: currentDefault.address.id },
          default: false
        };

        this.userAddressService.updateUserAddress(currentDefault.id!, updatePayload).subscribe(() => {
          this.setNewDefaultAddress(address);
        });
      } else {
        this.setNewDefaultAddress(address);
      }
    }
  }

  setNewDefaultAddress(address: Address): void {
    if (this.userId !== null) {
      const userAddress = this.userAddresses.find(ua => ua.address.id === address.id);
      if (userAddress) {
        const userAddressPayload: UserAddressPayload = {
          user: { id: this.userId! },
          address: { id: address.id },
          default: true
        };

        this.userAddressService.updateUserAddress(userAddress.id!, userAddressPayload).subscribe(() => {
          this.loadUserAddresses();
        });
      }
    }
  }

  editAddress(address: Address): void {
    this.editingAddressId = address.id;
    this.addressForm.patchValue({
      addressLine1: address.addressLine1,
      addressLine2: address.addressLine2,
      city: address.city,
      region: address.region,
      postalCode: address.postalCode,
      streetNumber: address.streetNumber,
      unitNumber: address.unitNumber,
      countryId: this.countries.find(country => country.id === address.countryId)
    });
  }

  resetForm(): void {
    this.addressForm.reset();
    this.editingAddressId = null;
  }

  getCountryName(countryId: number): string {
    const country = this.countries.find(country => country.id === countryId);
    return country ? country.countryName : '';
  }
}
