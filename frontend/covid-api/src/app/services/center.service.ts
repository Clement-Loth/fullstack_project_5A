import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Center } from '../models/center' ;

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  CENTERS : Center[]  = [ 
    {id: 45, name:"Salle des fêtes Bernie BONVOISIN", location:"All. de Champagne", state: "Open", city: "Vandoeuvre-lès-Nancy",disabled:false },
    {id: 23, name:"Centre de Réadaptation Lay St Christophe", location:"4 Rue du Professeur Montaut", state:"Closed", city: "Lay-Saint-Christophe", disabled:false}

  ];
  constructor(private http: HttpClient) { 
  }

  getAllVaxxCenters() : Center[] {
    return this.CENTERS;
  }

  getAll(){
    return this.http.get<Center[]>(`${environment.apiPath}/public/center`) ;
  }

  // ICI requête HTTP vers le back pour récupérer les centres !
}
