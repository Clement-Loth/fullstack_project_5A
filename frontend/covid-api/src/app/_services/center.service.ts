import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Center } from '../_models/center' ;

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  constructor(private http: HttpClient) { 
  }


  getAll(){
    return this.http.get<Center[]>(`${environment.apiPath}/public/center`) ;
  }

  getById(id: bigint){
    return this.http.get<Center>(`${environment.apiPath}/public/center/${id}`)
  }

  newCenter(centerName: string, centerCity: string, centerState: string, centerLocation: string){
    let formData = new FormData();
    formData.append("centerName",centerName);
    formData.append("centerCity",centerCity);
    formData.append("centerState",centerState);
    formData.append("centerLocation",centerLocation);

    return this.http.post(`${environment.apiPath}/admin/center`, formData);
  }

  updateCenter(centerName: string, centerCity: string, centerState: string, centerLocation: string, id: bigint){
    let formData = new FormData();
    formData.append("centerName",centerName);
    formData.append("centerCity",centerCity);
    formData.append("centerState",centerState);
    formData.append("centerLocation",centerLocation);

    return this.http.put(`${environment.apiPath}/admin/center/${id}`,formData);
  }

  // ICI requête HTTP vers le back pour récupérer les centres !
}
