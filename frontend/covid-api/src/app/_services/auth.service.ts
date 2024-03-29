import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, catchError, map, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../_models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userSubject: BehaviorSubject<User | null>;
  public user: Observable<User | null>;

  constructor(private router: Router, private http: HttpClient) {
      this.userSubject = new BehaviorSubject(JSON.parse(localStorage.getItem('user')!));
      this.user = this.userSubject.asObservable();
  }

  public get userValue() {
      return this.userSubject.value;
  }

  login(username: string, password: string) {
    localStorage.removeItem('user');
    let mockUser: User = {email: "", authdata : window.btoa(username + ':' + password), password: "", firstName: "", lastName: "", phone: "", disabled:false};
    this.userSubject = new BehaviorSubject<User | null>(mockUser);
      return this.http.get<any>(`${environment.apiPath}/admin/user/email/${username}`)
          .pipe(map(user => {
              // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
              user.authdata = window.btoa(username + ':' + password);
              localStorage.setItem('user', JSON.stringify(user));
              this.userSubject.next(user);
              return user;
            }), catchError(err => {
            if ([401, 403].includes(err.status)) {
                // auto logout if 401 response returned from api
                this.logout();
            }
            return throwError(() => err.error.message || err.statusText);
          })
            
            );
  }

//   login(username: string, password: string) {
//     return this.http.post<any>(`${environment.apiPath}/admin/users/email/${username}`, { username, password })
//         .pipe(map(user => {
//             user.authdata = window.btoa(username + ':' + password);
//             localStorage.setItem('user', JSON.stringify(user));
//             this.userSubject.next(user);
//             return user;
//         }));
// }

  logout() {
      // remove user from local storage to log user out
      localStorage.removeItem('user');
      this.userSubject.next(null);
      this.router.navigate(['/login']);
  }
}
