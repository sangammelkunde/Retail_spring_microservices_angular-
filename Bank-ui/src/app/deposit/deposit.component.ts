import { Router } from '@angular/router';
import { AccountInfo } from './../objects/AccountInfo';
import { EmployeeService } from './../service/employee.service';
import { Component, OnInit } from '@angular/core';
import { Account } from '../objects/Account';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {

  accountInfo:AccountInfo={
    accountId:0,
    balance:0,
  }
  accounts:Account[]=[
    
  ];
  isDeposited=false;
  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getAllAccounts();
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }
  //to deposit the amount
  deposit(){
    this.employeeService.deposit(this.accountInfo).subscribe(
      data=>{
        console.log(data);
        this.isDeposited=true;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
  //to display all the accounts of the customers
  getAllAccounts(){
    this.employeeService.getAllAccounts().subscribe(
      data=>{
        // this.accounts.push(...data);
        this.accounts=data;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
}
