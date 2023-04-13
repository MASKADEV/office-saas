import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { SignupComponent } from './components/authentication/signup/signup.component';
import { SigninComponent } from './components/authentication/signin/signin.component';
import { TeamManagmentComponent } from './components/dashboard/teamManagment/teamManagment.component';
import { LayoutComponent } from './components/dashboard/components/layout/layout.component';
import { FileManagmentComponent } from './components/dashboard/file-managment/file-managment.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptorService} from "./service/jwt-interceptor.service";
import { TeamModalComponent } from './components/dashboard/components/team-modal/team-modal.component';
import { DocToPdfComponent } from './components/dashboard/coverter/doc-to-pdf/doc-to-pdf.component';
import { AnalyticsComponent } from './components/dashboard/analytics/analytics.component';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    SignupComponent,
    SigninComponent,
    TeamManagmentComponent,
    LayoutComponent,
    FileManagmentComponent,
    TeamModalComponent,
    DocToPdfComponent,
    AnalyticsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    {
      provide:HTTP_INTERCEPTORS,
      useClass:JwtInterceptorService,
      multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
