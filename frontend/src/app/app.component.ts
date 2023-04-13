import { Component } from '@angular/core';
import {AuthenticationService} from "./service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';

  constructor(private authService : AuthenticationService, private router:Router) {
  }

  isLogedin() : boolean {
    return this.authService.isLoggedIn();
  }

  checkurl() {
    const url = window.location.href;
    if (url.includes('signin')) {
      this.router.navigate(['/signin']);
      return true;
    }else {
      this.router.navigate(['/signup']);
      return false
    }
  }


}
