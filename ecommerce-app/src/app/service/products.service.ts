import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Product, ProductCategory } from "../model/Product";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class ProductsService {
    private url = 'http://localhost:8080/api/product';

    constructor(
        private http: HttpClient
    ){}qs = new URLSearchParams();

    getProducts(): Observable<ProductCategory[]> {
        return this.http.get<ProductCategory[]>(this.url);
    }

    getProductById(id: number): Observable<ProductCategory> {
        return this.http.get<ProductCategory>(`${this.url}/${id}`);
    }

    createProductByCategory(idCategory: number, p: Product): Observable<Product> {
        return this.http.post<Product>(`${this.url}/category${idCategory}`, p);
    }
    editProduct(id: number, p: Product): Observable<Product> {
        return this.http.post<Product>(`${this.url}/${id}`, p);
    }
    deleteProduct(id: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${id}`);
    }
}