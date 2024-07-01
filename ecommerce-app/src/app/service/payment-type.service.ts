import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PaymentType {
  id: number;
  typeName: string;
}

@Injectable({
  providedIn: 'root'
})
export class PaymentTypeService {
  private apiUrl = 'http://localhost:8080/api/payment-types';

  constructor(private http: HttpClient) {}

  getAllPaymentTypes(): Observable<PaymentType[]> {
    return this.http.get<PaymentType[]>(this.apiUrl);
  }
}
