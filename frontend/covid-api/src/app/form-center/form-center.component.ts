import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Center } from '../_models/center';
import { CenterService } from '../_services/center.service';

@Component({
  selector: 'app-form-center',
  templateUrl: './form-center.component.html',
  styleUrls: ['./form-center.component.scss']
})
export class FormCenterComponent implements OnInit {

  centerForm!: FormGroup;
  loading = false;
  submitted = false;
  returnUrl!: string;
  error = '';

  center!: Center;
  title: string =  '';

  isAddCenter: boolean = false;
  isEditCenter: boolean = false; 

  constructor(private formBuilder: FormBuilder,
    private router: Router, private centerService : CenterService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.centerForm = this.formBuilder.group({
      location : ['',Validators.required],
      name: ['', Validators.required],
      city: ['', Validators.required]
    });
    if(this.router.url == "/center/add"){
      this.isAddCenter = true;
      this.title = "Add";
    }else if(this.route.snapshot.paramMap.get('id')){
      let id = BigInt(this.route.snapshot.paramMap.get('id')!);
      this.isEditCenter = true;
      this.centerForm.addControl('state', new FormControl('', Validators.required));
      this.centerService.getById(id).pipe(first()).subscribe((center : Center) =>{
        this.center = center;
        console.log(this.center)      
        this.state?.setValue(this.center.state);
        this.location?.setValue(this.center.location);
        this.name?.setValue(this.center.name);
        this.city?.setValue(this.center.city);
      });
      this.title = "Edit";
      
      
    }
  }

  get location() {return this.centerForm.get('location');}
  get name() {return this.centerForm.get('name');}
  get city() {return this.centerForm.get('city');}
  get state() {return this.centerForm.get('state');}


  onSubmit(){
    this.submitted = true;

    if (this.centerForm.invalid) {
      return;
    }

  }
}
