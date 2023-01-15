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
    return this.http.get<User[]>(`${environment.apiPath}/admin/user`);
  }

  getById(id:bigint){
    return this.http.get<User>(`${environment.apiPath}/admin/user/${id}`);
  }
}
