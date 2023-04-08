import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {AuthenticationService} from "../../../service/authentication.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent implements OnInit, OnDestroy{
  signinForm = new FormGroup({
    email:new FormControl("email00@email.com"),
    password : new FormControl("password")
  })

  error :String | null = null;
  private signinSubscribtion : Subscription = new Subscription();
  constructor(private authService : AuthenticationService, private router : Router) {
  }

  signin(){
    let payload = {
      email : this.signinForm.controls.email.value,
      password : this.signinForm.controls.password.value,
    }
    this.signinSubscribtion = this.authService.login(payload).subscribe(res => {
      if(res && res.results.token){
        this.router.navigate(['/dashboard'])
      }
    }, error => {
      this.error = error;
    })
  }

  ngOnInit() {
    if(this.authService.isLoggedIn()){
      this.router.navigate(['/dashboard']);
    };
  }

  ngOnDestroy() {
    this.signinSubscribtion.unsubscribe();
  }

}
