import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from 'src/app/service/authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.scss'],
})
export class AuthenticationComponent implements OnInit {
  pass: boolean = false;
  loginForm = new FormGroup({
    email: new FormControl('email00@email.com'),
    password: new FormControl('password'),
  });
  showPassword() {
    this.pass = !this.pass;
  }
  constructor(private auth: AuthenticationService) {}

  ngOnInit(): void {}

  onSubmit() {
    this.auth
      .login(this.loginForm.value.email, this.loginForm.value.password)
      .subscribe(
        (res) => {
          console.log(res);
        },
        (error) => {
          console.log(error);
        }
      );
  }
}
