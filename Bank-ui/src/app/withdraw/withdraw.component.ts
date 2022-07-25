import { Router } from '@angular/router';
import { CustomerService } from './../service/customer.service';
import { AccountInfo } from './../objects/AccountInfo';
import { Component, OnInit } from '@angular/core';
import { Account } from '../objects/Account';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'app-withdraw',
  templateUrl: './withdraw.component.html',
  styleUrls: ['./withdraw.component.css']
})
export class WithdrawComponent implements OnInit {
  accountInfo:AccountInfo={
    accountId:0,
    balance:0,
  }
  balanceObj={
    balance:0

  };

  accounts:Account[]=[
    
  ];
  isWithdrawn=false;
  isBalanceRetrieved=false;
  constructor(private router:Router,private customerService:CustomerService,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getCustomerAccounts();
    if (sessionStorage.role!="CUSTOMER"){
      this.router.navigate(['employeedashboard']);

    }
  }

  withdraw(){
    this.customerService.withdraw(this.accountInfo).subscribe(
      data=>{
        console.log("withdrawn");
        this.isWithdrawn=true;
        this.isBalanceRetrieved=false;
      },error=>{
        console.log(error);
        this.toastr.error(error.message,"Error");
      }
      );
    }
    
    checkBalance(){
      this.customerService.checkBalance(<any>this.accountInfo.accountId).subscribe(
        data=>{
          console.log(data);
          this.balanceObj.balance=data;
          this.isBalanceRetrieved=true;
          this.isWithdrawn=false;

      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }

  getCustomerAccounts(){
    console.log(sessionStorage.userid);
    this.customerService.getCustomerAccounts(sessionStorage.userid).subscribe(
      data=>{
        this.accounts=data;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
    }
}

