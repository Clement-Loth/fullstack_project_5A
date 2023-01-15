import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Center } from '../_models/center';
import { User } from '../_models/user';
import { CenterService } from '../_services/center.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-form-signin',
  templateUrl: './form-signin.component.html',
  styleUrls: ['./form-signin.component.scss']
})
export class FormSigninComponent implements OnInit {

  isSignIn: boolean = true; 
  signInForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  error = '';
  centers? : Center[];

  constructor(private formBuilder: FormBuilder,
    private router: Router, private route:ActivatedRoute, private userService: UserService, private centerService: CenterService) { }

  ngOnInit(): void {

  // Build controls
  this.signInForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      fullname: ['', Validators.required],
      lastname: ['', Validators.required],
      center: ['', Validators.required],
      phone: ['', Validators.required]
  });

  // Get list of centers 
  this.centerService.getAll().pipe(first()).subscribe((centers : Center[]) =>{
    this.centers = centers;
  });

  //
  if(this.route.snapshot.paramMap.get('id')){
    this.isSignIn = false;
    let id = BigInt(this.route.snapshot.paramMap.get('id')!);
    this.userService.getById(id).pipe(first()).subscribe((user : User) => {
      this.signInForm.removeControl('password');
      this.username?.setValue(user.email);
      this.fullname?.setValue(user.firstName);
      this.lastname?.setValue(user.lastName);
      this.center?.setValue(user.center?.name);
    });
  }
  }

  get username() { return this.signInForm.get('username'); }
  get password() { return this.signInForm.get('password'); }
  get fullname() { return this.signInForm.get('fullname'); }
  get lastname() { return this.signInForm.get('lastname'); }
  get center() { return this.signInForm.get('center');}
  get phone() {return this.signInForm.get('phone')}

  onSubmit(){
    this.submitted = true;
    let centerId = this.centers?.find(cent => cent.name == this.center?.value)?.id!
    if (this.signInForm.invalid) {
      return;
  }
    if(this.isSignIn){
      this.userService.createDoctor(this.fullname?.value,this.lastname?.value,this.username?.value,this.phone?.value,centerId,this.password?.value)
      .pipe(first())
      .subscribe({
        next: () => {
          this.router.navigate(["/supadmin"]);
        },
        error: () => {
          this.error = "Oopsies ;D";
        }
      });
    }

    // this.router.navigate(['centers']);

  }
}
