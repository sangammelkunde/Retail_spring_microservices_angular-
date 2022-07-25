import { MS_URLS } from './../objects/MsUrls';
import { AppUser } from './../objects/AppUser';
import { AuthenticationService } from './../service/authentication.service';
import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userid = ''
  password = ''
  invalidLogin = false
  authData:AppUser={
    userid:"",
    username:"",
    password:"",
    authToken:"",
    role:"",
  }
  
  @Input() error: string | null=null;

  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

  ngOnInit() {
  }
  //validating the logins of customer and employee
  checkLogin() {
    (this.loginservice.authenticate(this.userid, this.password).subscribe(
      data => {
        if(data.userid.startsWith("EMP")){
          this.router.navigate(['/employeedashboard']);
          data.role="EMPLOYEE";
        }else{
          this.router.navigate(['/customerdashboard']);
          data.role="CUSTOMER";
        }
        sessionStorage.setItem("role",data.role)
        console.log(data)
        this.invalidLogin = false
      },
      error => {
        this.invalidLogin = true
        this.error = error.message;

      }
    )
    );

  }

  
}
