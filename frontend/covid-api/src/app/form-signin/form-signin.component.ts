import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-signin',
  templateUrl: './form-signin.component.html',
  styleUrls: ['./form-signin.component.scss']
})
export class FormSigninComponent implements OnInit {

  signInForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.signInForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      fullname: ['', Validators.required],
      lastname: ['', Validators.required]
  });
  }

  get username() { return this.signInForm.get('username'); }
  get password() { return this.signInForm.get('password'); }
  get fullname() { return this.signInForm.get('fullname'); }
  get lastname() { return this.signInForm.get('lastname'); }

  onSubmit(){
    this.submitted = true;

    if (this.signInForm.invalid) {
      return;
  }
    this.router.navigate(['centers']);

  }
}
