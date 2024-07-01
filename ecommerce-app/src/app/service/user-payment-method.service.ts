import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface UserPaymentMethod {
  id: number;
  user: { id: number };
  paymentType: { id: number, typeName: string };
  provider: string;
  accountNumber: string;
  expiryDate: string;
  isDefault: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class UserPaymentMethodService {
  private apiUrl = 'http://localhost:8080/api/user-payment-methods';

  constructor(private http: HttpClient) {}

  getAllUserPaymentMethods(): Observable<UserPaymentMethod[]> {
    return this.http.get<UserPaymentMethod[]>(this.apiUrl);
  }

  createUserPaymentMethod(paymentMethod: UserPaymentMethod): Observable<UserPaymentMethod> {
    return this.http.post<UserPaymentMethod>(this.apiUrl, paymentMethod);
  }

  updateUserPaymentMethod(id: number, paymentMethod: UserPaymentMethod): Observable<UserPaymentMethod> {
    return this.http.put<UserPaymentMethod>(`${this.apiUrl}/${id}`, paymentMethod);
  }

  deleteUserPaymentMethod(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  setDefaultPaymentMethod(id: number, paymentMethod: UserPaymentMethod): Observable<UserPaymentMethod> {
    return this.http.put<UserPaymentMethod>(`${this.apiUrl}/${id}`, paymentMethod);
  }
}
