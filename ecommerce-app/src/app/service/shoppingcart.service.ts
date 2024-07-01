import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private apiUrl = 'http://localhost:8080/api/shopping-carts';

  constructor(private http: HttpClient) { }

  addProductToCart(idProducto: number, idUsuario: number, qty: number): Observable<void>{
    return this.http.post<void>(`${this.apiUrl}/user/${idUsuario}/product/${idProducto}/qty/${qty}`, null);
  }
  
}
