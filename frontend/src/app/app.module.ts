import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { SignupComponent } from './components/authentication/signup/signup.component';
import { SigninComponent } from './components/authentication/signin/signin.component';
import { HomeComponent } from './components/dashboard/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    SignupComponent,
    SigninComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
