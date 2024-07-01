import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SiteUser, SiteUserDTO } from '../model/SiteUser';

@Injectable({
  providedIn: 'root'
})
export class SiteUserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getUserById(id: number): Observable<SiteUser> {
    return this.http.get<SiteUser>(`${this.apiUrl}/${id}`);
  }

  registerUser(user: SiteUser): Observable<SiteUser> {
    return this.http.post<SiteUser>(`${this.apiUrl}/register`, user, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  loginUser(emailAddress: string, password: string): Observable<SiteUser> {
    const body = new HttpParams()
      .set('emailAddress', emailAddress)
      .set('password', password);

    return this.http.post<SiteUser>(`${this.apiUrl}/login`, body.toString(), {
      headers: new HttpHeaders({ 'Content-Type': 'application/x-www-form-urlencoded' })
    });
  }

  getAllUsers(): Observable<SiteUser[]> {
    return this.http.get<SiteUser[]>(`${this.apiUrl}/all`);
  }

  updateUser(id: number, user: SiteUserDTO): Observable<SiteUserDTO> {
    return this.http.put<SiteUserDTO>(`${this.apiUrl}/${id}`, user, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
