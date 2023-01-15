import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor( private http : HttpClient) { }

  newApp(firstname :string, lastname: string, phone: string, email: string, date: string, doctorId : bigint){
    let formData = new FormData();
    formData.append("firstName",firstname);
    formData.append("lastName",lastname);
    formData.append("phone",phone);
    formData.append("email",email);
    formData.append("date",date);
    formData.append("doctorId",doctorId.toString());
    
    return this.http.post(`${environment.apiPath}/public/app/`,formData);
  }
}
