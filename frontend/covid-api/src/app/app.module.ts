import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCheckboxModule } from '@angular/material/checkbox';

import { BasicAuthInterceptor } from './_interceptor/basic-auth.interceptor';
import { ErrorInterceptor } from './_interceptor/error.interceptor';

import { AppComponent } from './app.component';
import { CenterComponent } from './center/center.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { AppRoutingModule } from './app-routing.module';
import { FormLoginComponent } from './form-login/form-login.component';
import { FormSigninComponent } from './form-signin/form-signin.component';
import { AdminComponent } from './admin/admin.component';
import { FormCenterComponent } from './form-center/form-center.component';

@NgModule({
  declarations: [
    AppComponent,
    CenterComponent,
    AppointmentComponent,
    FormLoginComponent,
    FormSigninComponent,
    AdminComponent,
    FormCenterComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatCheckboxModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true},
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi:true }

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
