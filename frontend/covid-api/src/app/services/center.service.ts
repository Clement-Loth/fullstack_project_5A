import { Injectable } from '@angular/core';
import { Center } from '../models/center' ;

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  CENTERS : Center[]  = [ 
    {id: 45, name:"Salle des fêtes Bernie BONVOISIN", address:"All. de Champagne", postalCode: 54500, city: "Vandoeuvre-lès-Nancy" },
    {id: 23, name:"Centre de Réadaptation Lay St Christophe", address:"4 Rue du Professeur Montaut", postalCode: 54690, city: "Lay-Saint-Christophe"}

  ];
  constructor() { }

  getAllVaxxCenters() : Center[] {
    return this.CENTERS;
  }

  // ICI requête HTTP vers le back pour récupérer les centres !
}
