import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Center } from '../_models/center';
import { CenterService } from '../_services/center.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.scss']
})
export class CenterComponent implements OnInit {

  constructor(private centerservice : CenterService) { }
  centers? : Center[];
 

  ngOnInit(): void {
    //this.centers = this.centerservice.getAllVaxxCenters();

    this.centerservice.getAll().pipe(first()).subscribe((centers : Center[]) =>{
        this.centers = centers;
    });

  }

  clickEvent(){
    

  }


}
