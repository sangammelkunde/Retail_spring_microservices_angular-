import { Router } from '@angular/router';
import { CustomerService } from './../service/customer.service';
import { Component, OnInit } from '@angular/core';
import { Transaction } from '../objects/Transaction';
import { Account } from '../objects/Account';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-transactionaldetails',
  templateUrl: './transactionaldetails.component.html',
  styleUrls: ['./transactionaldetails.component.css']
})
export class TransactionaldetailsComponent implements OnInit {

  transactions:Transaction[]=[];

  accountId:string="";
  fromDate:string="";
  toDate:string="";
  accounts: Account[] = [
    
  ];
  isGenerated=false;
  constructor(private router:Router,private customerService:CustomerService,private toastr: ToastrService) { }

  ngOnInit(): void {
    this.getCustomerAccounts();
    if (sessionStorage.role!="CUSTOMER"){
      this.router.navigate(['employeedashboard']);

    }
  }
// to get the transactions details
  getTransactions(){
    this.customerService.getAccountStatement(this.accountId,this.fromDate,this.toDate).subscribe(
      data=>{
        console.log(data);
        this.transactions=data;
        this.isGenerated=true;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
  //to get the customer accounts
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
