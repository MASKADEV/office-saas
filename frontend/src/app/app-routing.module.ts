import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LandingPageComponent} from "./components/landing-page/landing-page.component";
import {SigninComponent} from "./components/authentication/signin/signin.component";
import {SignupComponent} from "./components/authentication/signup/signup.component";
import {TeamManagmentComponent} from "./components/dashboard/teamManagment/teamManagment.component";
import {AuthGuard} from "./security/auth.guard";
import {DocToPdfComponent} from "./components/dashboard/coverter/doc-to-pdf/doc-to-pdf.component";

const routes: Routes = [
  { path: '', component: SignupComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'signin', component: SigninComponent },
  {
    path: 'dashboard',
    canActivate: [AuthGuard],
    children: [
      { path: '', component: LandingPageComponent, pathMatch: 'full' },
      { path: 'team-management', component: TeamManagmentComponent},
      { path: 'convert',
        children:[
          { path : 'doc-pdf', component: DocToPdfComponent},
          { path: '**', redirectTo: 'home', pathMatch: 'full' },
        ]
      },
      { path: '**', redirectTo: 'home', pathMatch: 'full' },
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
