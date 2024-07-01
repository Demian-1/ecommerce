import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { PaymentTypeService, PaymentType } from '../service/payment-type.service';
import { UserPaymentMethodService, UserPaymentMethod } from '../service/user-payment-method.service';
import { AuthService } from '../service/auth.service';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-payment-form',
  templateUrl: './payment-form.component.html',
  styleUrls: ['./payment-form.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ButtonModule,
    CardModule,
    DropdownModule,
    TableModule,
    CalendarModule,
    CheckboxModule,
    InputTextModule
  ]
})
export class PaymentFormComponent implements OnInit {
  paymentForm!: FormGroup;
  paymentTypes: PaymentType[] = [];
  paymentMethods: UserPaymentMethod[] = [];
  userId: number | null = null;
  editingPaymentMethodId: number | null = null;

  constructor(
    private fb: FormBuilder,
    private paymentTypeService: PaymentTypeService,
    private userPaymentMethodService: UserPaymentMethodService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();

    if (this.userId) {
      this.paymentForm = this.fb.group({
        paymentType: [null, Validators.required],
        provider: ['', [Validators.required, Validators.maxLength(240)]],
        accountNumber: ['', [Validators.required, Validators.maxLength(100)]],
        expiryDate: ['', Validators.required],
        isDefault: [false, Validators.required]
      });

      this.paymentTypeService.getAllPaymentTypes().subscribe((data: PaymentType[]) => {
        this.paymentTypes = data;
      });

      this.loadUserPaymentMethods();
    } else {
      console.error('Usuario no autenticado');
    }
  }

  loadUserPaymentMethods(): void {
    if (this.userId) {
      this.userPaymentMethodService.getAllUserPaymentMethods().subscribe((data: UserPaymentMethod[]) => {
        this.paymentMethods = data.filter(pm => pm.user.id === this.userId);
      });
    }
  }

  onSubmit(): void {
    if (this.paymentForm.valid && this.userId !== null) {
      const formValue = this.paymentForm.value;
      const paymentMethodPayload: UserPaymentMethod = {
        id: this.editingPaymentMethodId ?? 0, // Usar ID temporal o el ID de edición
        user: { id: this.userId },
        paymentType: formValue.paymentType,
        provider: formValue.provider,
        accountNumber: formValue.accountNumber,
        expiryDate: formValue.expiryDate,
        isDefault: formValue.isDefault
      };

      if (this.editingPaymentMethodId === null) {
        this.userPaymentMethodService.createUserPaymentMethod(paymentMethodPayload).subscribe(paymentMethod => {
          this.paymentMethods.push(paymentMethod);
          this.resetForm();
          this.loadUserPaymentMethods(); // Recargar los métodos de pago después de agregar uno nuevo
        });
      } else {
        this.userPaymentMethodService.updateUserPaymentMethod(this.editingPaymentMethodId, paymentMethodPayload).subscribe(() => {
          this.loadUserPaymentMethods(); // Recargar los métodos de pago después de la actualización
          this.resetForm();
        });
      }
    } else {
      this.paymentForm.markAllAsTouched();
    }
  }

  deletePaymentMethod(id: number): void {
    this.userPaymentMethodService.deleteUserPaymentMethod(id).subscribe(() => {
      this.paymentMethods = this.paymentMethods.filter(pm => pm.id !== id);
    });
  }

  setDefaultPaymentMethod(paymentMethod: UserPaymentMethod): void {
    if (this.userId !== null) {
      // Establecer los demás métodos de pago como no predeterminados
      this.paymentMethods.forEach(pm => {
        if (pm.id !== paymentMethod.id) {
          pm.isDefault = false;
          this.userPaymentMethodService.updateUserPaymentMethod(pm.id, pm).subscribe();
        }
      });

      // Establecer el método de pago seleccionado como predeterminado
      const updatedPaymentMethod = { ...paymentMethod, isDefault: true };
      this.userPaymentMethodService.updateUserPaymentMethod(paymentMethod.id, updatedPaymentMethod).subscribe(() => {
        this.loadUserPaymentMethods(); // Recargar los métodos de pago después de la actualización
      });
    }
  }

  editPaymentMethod(paymentMethod: UserPaymentMethod): void {
    this.editingPaymentMethodId = paymentMethod.id;
    this.paymentForm.patchValue({
      paymentType: paymentMethod.paymentType,
      provider: paymentMethod.provider,
      accountNumber: paymentMethod.accountNumber,
      expiryDate: paymentMethod.expiryDate,
      isDefault: paymentMethod.isDefault
    });
  }

  resetForm(): void {
    this.paymentForm.reset();
    this.paymentForm.patchValue({ isDefault: false }); // Reset isDefault to false
    this.editingPaymentMethodId = null;
  }

  censorAccountNumber(accountNumber: string): string {
    return accountNumber.slice(0, -4).replace(/./g, '*') + accountNumber.slice(-4);
  }
}
