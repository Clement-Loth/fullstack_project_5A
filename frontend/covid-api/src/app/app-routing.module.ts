import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CenterComponent } from './center/center.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { FormLoginComponent } from './form-login/form-login.component';
import { FormSigninComponent } from './form-signin/form-signin.component';
import { AuthGuard } from './_guards/auth.guard';
import { AdminComponent } from './admin/admin.component';
import { FormCenterComponent } from './form-center/form-center.component';
import { HttpcompComponent } from './httpcomp/httpcomp.component';

const routes: Routes = [
  {path: "centers", component: CenterComponent},
  {path: "appointment/:id", component: AppointmentComponent},
  {path: "", redirectTo: "/centers", pathMatch: "full"},
  {path: "login", component: FormLoginComponent},
  {path: "signin", component: FormSigninComponent, canActivate:[AuthGuard], data: {role: ("SuperAdministrator")}},
  {path: "signin/:id", component: FormSigninComponent, canActivate:[AuthGuard], data: {role: ("SuperAdministrator")}},
  {path: "admin", component: AdminComponent, canActivate:[AuthGuard], data: {role: ("Administrator" || "SuperAdministrator")}},
  {path: "center/edit/:id", component:FormCenterComponent, canActivate:[AuthGuard], data: {role: ("SuperAdministrator")}},
  {path: "center/add", component:FormCenterComponent, canActivate:[AuthGuard], data: {role: ("SuperAdministrator")}},
  {path: "logout", component:FormLoginComponent},
  {path: "httperr", component:HttpcompComponent}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
