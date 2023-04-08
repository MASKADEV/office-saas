import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../../../../service/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
  isList: number = 0;
  isMenu: boolean = false;
  isSearch: boolean = false;

  constructor(private authServidce: AuthenticationService, private router : Router) {
  }

  ngOnInit(): void {
  }

  logout(){
    this.authServidce.logout();
    this.router.navigate(['/'])
  }
}
