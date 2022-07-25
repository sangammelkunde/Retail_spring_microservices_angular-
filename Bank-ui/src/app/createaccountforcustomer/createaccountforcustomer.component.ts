import { Router } from '@angular/router';
import { Account } from './../objects/Account';
import { EmployeeService } from './../service/employee.service';
import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
//Creates account for customer
@Component({
  selector: 'app-createaccountforcustomer',
  templateUrl: './createaccountforcustomer.component.html',
  styleUrls: ['./createaccountforcustomer.component.css']
})
export class CreateaccountforcustomerComponent implements OnInit {

  account:Account={
    accountId:0,
    customerId:"",
    balance:0,
    accountType:"",
    ownerName:"",
  } 

  isAccountCreated=false;

  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }
//The method calls the employee service to create account
  createAccount(){
    this.employeeService.createAccount(this.account.customerId,this.account).subscribe(
      data=>{
        console.log(data);
        this.isAccountCreated=true;
      },
      error=>{
        this.toastr.error(error.message,"Error");
      }
      );
  }
}
