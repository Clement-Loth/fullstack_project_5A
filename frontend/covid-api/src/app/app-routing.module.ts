import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { CenterComponent } from './center/center.component';
import { AppointmentComponent } from './appointment/appointment.component';
import { FormLoginComponent } from './form-login/form-login.component';
import { FormSigninComponent } from './form-signin/form-signin.component';
import { AuthGuard } from './_guards/auth.guard';

const routes: Routes = [
  {path: "centers", component: CenterComponent},
  {path: "appointment/:id", component: AppointmentComponent},
  {path: "", redirectTo: "/centers", pathMatch: "full"},
  {path: "login", component: FormLoginComponent},
  {path: "signin", component: FormSigninComponent, canActivate:[AuthGuard]}
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
