import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'covid-api';


  getRoutePath(){
    const routepath = window.location.pathname;
    if(routepath == "/centers") return true;
    else return false;
  }
}
