import { Router } from '@angular/router';
import { CustomerService } from './../service/customer.service';
import { Account } from './../objects/Account';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-customeraccounts',
  templateUrl: './customeraccounts.component.html',
  styleUrls: ['./customeraccounts.component.css']
})
export class CustomeraccountsComponent implements OnInit {

  accounts:Account[]=[
    {
      accountId:0,
      customerId:"",
      balance:0,
      accountType:"",
      ownerName:"",
    },
  ];
  constructor(private router:Router, private customerService:CustomerService,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getCustomerAccounts();
    if (sessionStorage.role!="CUSTOMER"){
      this.router.navigate(['employeedashboard']);

    }
  }
  //This method is to get the customer accounts 
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
