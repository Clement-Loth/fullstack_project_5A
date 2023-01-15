import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs';
import { Center } from '../_models/center';
import { User } from '../_models/user';
import { CenterService } from '../_services/center.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-supadmin',
  templateUrl: './supadmin.component.html',
  styleUrls: ['./supadmin.component.scss']
})


export class SupadminComponent implements OnInit {

  constructor(private centerService : CenterService, private userService : UserService) { }
  centers? : Center[];
  users? : User[];

  ngOnInit(): void {
    //this.centers = this.centerService.getAllVaxxCenters();

    this.userService.getAll().pipe(first()).subscribe((users : User[]) =>{
      this.users = users;
    })

    this.centerService.getAll().pipe(first()).subscribe((centers : Center[]) =>{
      this.centers = centers;
    });
  }
}
