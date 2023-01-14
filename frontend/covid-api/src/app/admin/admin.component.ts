import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Center } from '../_models/center';
import { CenterService } from '../_services/center.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(private centerService : CenterService) { }
  centers? : Center[];

  ngOnInit(): void {
    this.centers = this.centerService.getAllVaxxCenters();
    
    // this.centerService.getAll().pipe(first()).subscribe((centers : Center[]) =>{
    //   this.centers = centers;
    // });

  }

}
