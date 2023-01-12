import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-form-login',
  templateUrl: './form-login.component.html',
  styleUrls: ['./form-login.component.scss']
})
export class FormLoginComponent implements OnInit {
  loginForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router, private authService : AuthService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  get username() { return this.loginForm.get('username'); }
  get password() { return this.loginForm.get('password'); }

  onSubmit(){
    this.submitted = true;

    if (this.loginForm.invalid) {
      return;
  }
  this.error = '';
  this.loading = true;
  this.authService.login(this.username!.value, this.password!.value)
      .pipe(first())
      .subscribe({
          next: () => {
              // get return url from route parameters or default to '/'
              const returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
              this.router.navigate([returnUrl]);
          },
          error: error => {
              this.error = error;
              this.loading = false;
          }
      });
      this.route.snapshot.queryParams['returnUrl']
  }

}
