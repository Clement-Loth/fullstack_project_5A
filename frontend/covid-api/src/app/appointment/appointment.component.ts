import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.scss']
})
export class AppointmentComponent implements OnInit {

  appointForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.appointForm = this.formBuilder.group({
      fullname: ['', Validators.required],
      lastname: ['', Validators.required],
      appointDate: ['',Validators.required],
      mail: ['', Validators.required]
  });
  }

  get fullname() { return this.appointForm.get('fullname'); }
  get lastname() { return this.appointForm.get('lastname'); }
  get appointDate() {return this.appointForm.get('appointDate');}
  get mail() {return this.appointForm.get('mail');}

  onSubmit(){
    this.submitted = true;

    if (this.appointForm.invalid) {
      return;
  }
    this.router.navigate(['centers']);

  }

  onCancel(){
    this.router.navigate(['centers']);
  }
}
