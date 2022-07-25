import { AuthenticationService } from './../service/authentication.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customerdashboard',
  templateUrl: './customerdashboard.component.html',
  styleUrls: ['./customerdashboard.component.css']
})
export class CustomerdashboardComponent implements OnInit {

  constructor(private router:Router,private authenticationService:AuthenticationService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="CUSTOMER"){
      this.router.navigate(['employeedashboard']);

    }
  }

  logout(){
    console.log("logout clicked")
    this.authenticationService.logOut();
  }
}
