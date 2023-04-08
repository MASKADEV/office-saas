import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LandingPageComponent} from "./components/landing-page/landing-page.component";
import {SigninComponent} from "./components/authentication/signin/signin.component";
import {SignupComponent} from "./components/authentication/signup/signup.component";
import {HomeComponent} from "./components/dashboard/home/home.component";
import {AuthGuard} from "./security/auth.guard";
import {FileManagmentComponent} from "./components/dashboard/file-managment/file-managment.component";

const routes: Routes = [
  { path: '', component: LandingPageComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'signin', component: SigninComponent },
  {
    path: 'dashboard',
    canActivate: [AuthGuard],
    component: HomeComponent,
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'file-management', component: FileManagmentComponent },
      // { path: 'settings', component: SettingsComponent },
      { path: '**', redirectTo: 'home', pathMatch: 'full' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
