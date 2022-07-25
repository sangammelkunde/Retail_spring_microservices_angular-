import { Router } from '@angular/router';
import { MS_URLS } from './../objects/MsUrls';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";

export class User {
  constructor(public status: string) {}
}

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private router: Router,private httpClient: HttpClient) {}
// Provide username and password for authentication, and once authentication is successful, 
//store JWT token in session
  authenticate(userid: string, password: any) {
    return this.httpClient
      .post<any>(`${MS_URLS.auth}/login`, { userid, password })
      .pipe(
        map(userData => {
          sessionStorage.setItem("userid", userid);
          let tokenStr = "Bearer " + userData.authToken;
          sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("userid");
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem("userid");
    sessionStorage.removeItem("token");
  }

  unauthorizedRedirect(){
    this.router.navigate(['errorstatus']);
  }
}
