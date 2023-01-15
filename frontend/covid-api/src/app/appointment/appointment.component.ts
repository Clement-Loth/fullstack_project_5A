import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { AppointmentService } from '../_services/appointment.service';

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
  isPhonechecked : boolean = false;

  constructor(private formBuilder: FormBuilder,
    private router: Router, private route: ActivatedRoute, private appService : AppointmentService) { }

  ngOnInit(): void {
    this.appointForm = this.formBuilder.group({
      fullname: ['', Validators.required],
      lastname: ['', Validators.required],
      appointDate: ['',Validators.required],
      mail: ['', Validators.required],
      appointTime: ['',Validators.required]
  });
  }

  get fullname() { return this.appointForm.get('fullname') }
  get lastname() { return this.appointForm.get('lastname') }
  get appointDate() {return this.appointForm.get('appointDate')}
  get mail() {return this.appointForm.get('mail')}
  get phoneNum() {return this.appointForm.get('phoneNum')}
  get appointTime() {return this.appointForm.get('appointTime')}

  onCancel(){
    this.router.navigate(['centers']);
  }

  addPhone(checking : boolean){
    this.isPhonechecked = checking;
    if(checking){
      this.appointForm.addControl("phoneNum", new FormControl('', Validators.required));
    }
    else{
      this.appointForm.removeControl("phoneNum");
    }
    return;
  }

  onSubmit(){
    this.submitted = true;
    let dateObj = new Date(this.appointDate?.value+ 'T'+ this.appointTime?.value);
    let centerId = BigInt(this.route.snapshot.paramMap.get('id')!);
    let phoneNumber = '';
    if (this.appointForm.invalid) {
      return;
    }
    if(this.isPhonechecked){
      phoneNumber = this.phoneNum?.value || "non";
    }else{
      phoneNumber = "non";
    }
      this.appService.newApp(this.fullname?.value,this.lastname?.value,this.phoneNum?.value,this.mail?.value,dateObj.toISOString(),centerId)
      .pipe(first())
      .subscribe({
        next : () => {
          this.router.navigate(['/centers']);
        },
        error: () => {
          this.error = "Oops"
        }
      })
    


    

  }
}
