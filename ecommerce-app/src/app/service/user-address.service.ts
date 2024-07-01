import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UserAddressPayload {
  id?: number; // Agregar id para UserAddressPayload
  user: { id: number };
  address: {
    id: number;
    unitNumber?: string;
    streetNumber?: string;
    addressLine1?: string;
    addressLine2?: string;
    city?: string;
    region?: string;
    postalCode?: string;
    countryId?: number;
  };
  default: boolean;
}


@Injectable({
  providedIn: 'root'
})
export class UserAddressService {
  private apiUrl = 'http://localhost:8080/api/user_addresses';

  constructor(private http: HttpClient) {}

  getUserAddressesByUserId(userId: number): Observable<UserAddressPayload[]> {
    return this.http.get<UserAddressPayload[]>(`${this.apiUrl}/user/${userId}`);
  }

  createUserAddress(userAddress: UserAddressPayload): Observable<UserAddressPayload> {
    return this.http.post<UserAddressPayload>(this.apiUrl, userAddress);
  }

  updateUserAddress(id: number, userAddress: UserAddressPayload): Observable<UserAddressPayload> {
    return this.http.put<UserAddressPayload>(`${this.apiUrl}/${id}`, userAddress);
  }

  deleteUserAddress(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
