import { Router } from '@angular/router';
import { CustomerService } from './../service/customer.service';
import { TransactionInput } from './../objects/TransactionInput';
import { Component, OnInit } from '@angular/core';
import { Account } from '../objects/Account';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css'],
})
export class TransferComponent implements OnInit {
  transactionInput: TransactionInput = {
    sourceAccount: 0,
    targetAccount: 0,
    amount: 0,
    typeOfTrasaction: '',
  };
  accounts: Account[] = [
    
  ];
  isTransfered = false;
  constructor(private router:Router,private customerService: CustomerService,private toastr: ToastrService) {}

  ngOnInit(): void {
    this.getCustomerAccounts();
    if (sessionStorage.role!="CUSTOMER"){
      this.router.navigate(['employeedashboard']);

    }
  }

  transfer() {
    this.customerService.transfer(this.transactionInput).subscribe((data) => {
      console.log(data);
      this.isTransfered = true;
    },error=>{
      this.toastr.error(error.message,"Error");
    });
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
