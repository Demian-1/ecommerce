import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface ShoppingCart {
  id?: number;
  user?: SiteUser;
  items?: ShoppingCartItem[];
}

export interface ShoppingCartItem {
  id?: number;
  product: Product;
  qty: number;
  totalPrice?: number; // Agrega esta propiedad para la vista calculada
}

export interface Product {
  id?: number;
  name: string;
  description: string;
  price: number;
  image?: string;
}


export interface SiteUser {
  id?: number;
  username?: string;
  email?: string;
}


@Injectable({
  providedIn: 'root'
})
export class ShoppingCartService {
  private apiUrl = 'http://localhost:8080/api/shopping-carts';
  private baseUrl = 'http://localhost:8080/api/shopping-carts';

  constructor(private http: HttpClient) { }

  emptyCart(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${userId}/empty`);
  }

  addProductToCart(idProducto: number, idUsuario: number, qty: number): Observable<void>{
    return this.http.post<void>(`${this.apiUrl}/user/${idUsuario}/product/${idProducto}/qty/${qty}`, null);
  }

  getShoppingCartItems(userId: number): Observable<ShoppingCartItem[]> {
    const url = `${this.baseUrl}/${userId}/items`;
    return this.http.get<ShoppingCartItem[]>(url);
  }

  addProductToShoppingCart(userId: number, productId: number, qty: number): Observable<ShoppingCartItem> {
    const url = `${this.baseUrl}/user/${userId}/product/${productId}/qty/${qty}`;
    return this.http.post<ShoppingCartItem>(url, {});
  }

  getCartByUserId(userId: number): Observable<ShoppingCart> {
    const url = `${this.baseUrl}/${userId}`;
    return this.http.get<ShoppingCart>(url);
  }

  createOrUpdateCart(cart: ShoppingCart): Observable<ShoppingCart> {
    return this.http.post<ShoppingCart>(this.baseUrl, cart);
  }

  deleteCart(cartId: number): Observable<void> {
    const url = `${this.baseUrl}/${cartId}`;
    return this.http.delete<void>(url);
  }

  getItemsByCartId(cartId: number): Observable<ShoppingCartItem[]> {
    const url = `${this.baseUrl}/${cartId}/items`;
    return this.http.get<ShoppingCartItem[]>(url);
  }

  addItemToCart(cartId: number, item: ShoppingCartItem): Observable<ShoppingCartItem> {
    const url = `${this.baseUrl}/${cartId}/items`;
    return this.http.post<ShoppingCartItem>(url, item);
  }

  updateItemQuantity(itemId: number, qty: number): Observable<ShoppingCartItem> {
    const url = `${this.baseUrl}/items/${itemId}`;
    const params = new HttpParams().set('qty', qty.toString());
    return this.http.put<ShoppingCartItem>(url, {}, { params });
  }

  removeItemFromCart(itemId: number): Observable<void> {
    const url = `${this.baseUrl}/items/${itemId}`;
    return this.http.delete<void>(url);
  }
  
}
