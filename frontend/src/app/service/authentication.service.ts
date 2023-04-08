import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, tap} from "rxjs";
import {API_URL} from "../util/config";
import {catchError, throwError} from "rxjs";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
    constructor(private http: HttpClient, private cookieService: CookieService) { }
  private isAuthenticated = false;

  login(data : any):Observable<any> {
    return this.http.post<any>(API_URL + '/auth/signin', data).pipe(
      tap(res => {
        if(res && res.results.token){
          this.cookieService.set("token", res.results.token, {expires:32323})
          this.isAuthenticated = true;
        }
      }),
      catchError(error => {
        let errorMessage = 'An error occurred while signing up. Please try again later.';
        if (error.error && error.error.message) {
          errorMessage = error.error.message;
        }
        return throwError(errorMessage);
      })
    );
  }

  register(data : any):Observable<any> {
      return this.http.post<any>(API_URL + '/auth/register', data).pipe(
        catchError(error => {
          let errorMessage = 'An error occurred while signing up. Please try again later.';
          if (error.error && error.error.message) {
            errorMessage = error.error.message;
          }
          return throwError(errorMessage);
        })
      );
  }

  logout() {
    this.cookieService.delete("token", '/', '')
    console.log("token",this.cookieService.get("token"))
    this.isAuthenticated = false;
  }

  isLoggedIn(): boolean {
    if(this.cookieService.get("token")){
      this.isAuthenticated = true;
      return this.isAuthenticated;
    }else {
      this.isAuthenticated = false;
      return this.isAuthenticated;
    }
  }
}
