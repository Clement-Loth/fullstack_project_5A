import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.scss']
})
export class BannerComponent implements OnInit {

  constructor(private authService : AuthService) { }

  ngOnInit(): void {
    let user = this.authService.userValue;
    if(user){
      this.isLogged = true;
    }else{
      this.isLogged = false;
    }
  }

  title = 'THE covid web app, you mad lad';
  isLogged: boolean = false;

  getRoutePath(){
    const routepath = window.location.pathname;
    if(routepath == "/centers") return true;
    else return false;
  }

  getRoute(){
    const routepath = window.location.pathname;
    return routepath;
  }


}
