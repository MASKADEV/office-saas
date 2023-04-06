import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private isAuthenticated = false;

  login() {
    // Perform authentication logic here
    this.isAuthenticated = true;
  }

  logout() {
    // Perform logout logic here
    this.isAuthenticated = false;
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }
}
