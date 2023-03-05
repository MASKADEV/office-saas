import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  constructor(private http: HttpClient) {}
  private url = 'localhost:8080';
  httpOptions = {
    headers: new HttpHeaders(
      this.isAuthenticated()
        ? {
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + localStorage.getItem('access_token'),
          }
        : {
            'Content-Type': 'application/json',
          }
    ),
    withCredentials: true,
  };

  login(
    email: string | undefined | null,
    password: string | undefined | null
  ): Observable<any> {
    let body = { email, password };
    return this.http.post<any>(`${this.url}/auth/signin`, JSON.stringify(body));
  }

  logout(): void {
    localStorage.removeItem('access_token');
  }

  isAuthenticated(): boolean {
    return localStorage.getItem('access_token') !== null;
  }
}
