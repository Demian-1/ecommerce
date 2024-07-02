import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ShopOrder } from '../model/ShopOrder';

@Injectable({
  providedIn: 'root'
})
export class ShopOrderService {

  private apiUrl = 'http://localhost:8080/api/shop-orders';

  constructor(private http: HttpClient) { }

  getOrdersByUserId(userId: number): Observable<ShopOrder[]> {
    return this.http.get<ShopOrder[]>(`${this.apiUrl}/user/${userId}`);
  }

  createOrder(order: ShopOrder): Observable<ShopOrder> {
    return this.http.post<ShopOrder>(this.apiUrl, order);
  }
}
