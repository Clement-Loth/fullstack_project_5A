import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../_models/user';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
}

  getAll(){
    return this.http.get<User[]>(`${environment.apiPath}/admin/user/`);
  }

  getById(id:bigint){
    return this.http.get<User>(`${environment.apiPath}/admin/user/${id}`);
  }

  createDoctor(firstName: string, lastName:string, email:string, phone:string, centerId:bigint, password:string){
    let formData = new FormData();
    formData.append('firstName',firstName);
    formData.append('lastName',lastName);
    formData.append('email',email);
    formData.append('phone',phone);
    formData.append('centerId',centerId.toString());
    formData.append('password',password);

    return this.http.post(`${environment.apiPath}/admin/user/`,formData);
    
  }
}
