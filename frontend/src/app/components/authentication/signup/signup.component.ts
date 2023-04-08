import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthenticationService} from "../../../service/authentication.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {CookieService} from "ngx-cookie-service";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit, OnDestroy{

  signupForm = new FormGroup({
    full_name : new FormControl("youness sidki"),
    username : new FormControl("username"+ Date.now()),
    email : new FormControl("sidki"+'-' + Date.now()+ "@gmail.com"),
    password : new FormControl("password"),
    confirm_password : new FormControl("password")
  });
  error_msg : String | null = null;

  private signupSubscription : Subscription = new Subscription();

  constructor(private authService : AuthenticationService, private router:Router) {
  }
  ngOnInit() {
    if(this.authService.isLoggedIn()){
      this.router.navigate(['/dashboard']);
    };
  }

  signup(){
    if(this.signupForm.controls.password.value == this.signupForm.controls.confirm_password.value)
    {
      this.error_msg = null;
      let payload = {
        "full_name" : this.signupForm.controls.full_name.value,
        "username" : this.signupForm.controls.full_name.value,
        "email" : this.signupForm.controls.email.value,
        "password" : this.signupForm.controls.password.value
      }
      this.signupSubscription = this.authService.register(payload).subscribe(res => {
        console.log(res)
      }, error => {
        this.error_msg = error;
      });
    }else {
      this.error_msg = "password not matched";
    }
  }


  ngOnDestroy() {
    this.signupSubscription.unsubscribe();
  }
}
