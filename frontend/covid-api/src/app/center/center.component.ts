import { Component, OnInit } from '@angular/core';
import { Center } from '../center';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.scss']
})
export class CenterComponent implements OnInit {

  constructor() { }

  center : Center = {
    id : 45,
    name: "Salle des fêtes Bernie BONVOISIN",
    address: "All. de Champagne",
    postalCode: 54500,
    city: "Vandoeuvre-lès-Nancy"
  }

  ngOnInit(): void {
  }

  clickEvent(){
    const tee = document.getElementById("kewl"); // Typescript consider tee might be null, 
                                                 // so direct property modification won't work

    tee!.innerHTML = "Oops ! No reserving for today.";  // add ! after variable name to force non-null on this variable.
                                 // This is known as Non-null assertion 

  }


}
