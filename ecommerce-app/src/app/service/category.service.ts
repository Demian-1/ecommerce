import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Category,CategoryDTO } from "../model/Category";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class CategoryService {
    private url = 'http://localhost:8080/api/category';

    constructor(
        private http: HttpClient
    ){}qs = new URLSearchParams();

    getCategories(): Observable<Category[]> {
        return this.http.get<Category[]>(this.url);
    }

    getCategoryById(id: number): Observable<Category> {
        return this.http.get<Category>(`${this.url}/${id}`);
    }

    createCategory(c: CategoryDTO): Observable<Category> {
        return this.http.post<Category>(`${this.url}`, c);
    }
    editCategory(id: number, c: CategoryDTO): Observable<Category> {
        return this.http.post<Category>(`${this.url}/${id}`, c);
    }
    deleteCategory(id: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${id}`);
    }
    
      getProductsByCategory(categoryId: number): Observable<Category> {
        return this.http.get<Category>(`${this.url}/${categoryId}`);
      }
}