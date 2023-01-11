import { Component, OnInit } from '@angular/core';
import { Center } from '../models/center';
import { CenterService } from '../services/center.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.scss']
})
export class CenterComponent implements OnInit {

  constructor(private centerservice : CenterService) { }
  centers! : Center[];
 

  ngOnInit(): void {
    this.centers = this.centerservice.getAllVaxxCenters();
  }

  clickEvent(){
    const tee = document.getElementById("kewl"); // Typescript consider tee might be null, 
                                                 // so direct property modification won't work

    tee!.innerHTML = "Oops ! No reserving for today.";  // add ! after variable name to force non-null on this variable.
                                 // This is known as Non-null assertion 

  }


}
