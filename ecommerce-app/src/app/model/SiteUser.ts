export interface SiteUser {
  id: number; // Campo opcional para manejar la ausencia de ID en un nuevo registro
  emailAddress: string;
  userName: string;
  phoneNumber: string;
  password: string;
  userAddresses?: UserAddress[]; // Campo opcional para manejar la ausencia de direcciones de usuario
}

export class SiteUserDTO {
  id: number = 0; // Campo opcional para manejar la ausencia de ID en un nuevo registro
  emailAddress: string = '';
  userName: string = '';
  phoneNumber: string= '';
}

export interface UserAddress {
  id?: number;
  street: string;
  city: string;
  state: string;
  postalCode: string;
  country: string;
}
