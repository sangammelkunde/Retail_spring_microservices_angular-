import { AuthenticationService } from './../service/authentication.service';
import { Component, OnInit } from '@angular/core';
//implementing the navbar
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isEmployee=(sessionStorage.role=="EMPLOYEE");
  constructor(private authenticationService:AuthenticationService) { }

  ngOnInit(): void {
  }

  logout(){
    this.authenticationService.logOut();
  }
}
