import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  
})
export class AppComponent {
  title = 'THE covid api, you mad lad';


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
