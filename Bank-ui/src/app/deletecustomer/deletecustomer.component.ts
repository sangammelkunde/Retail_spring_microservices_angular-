import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Account } from './../objects/Account';
import { EmployeeService } from './../service/employee.service';
import { ToastrService } from 'ngx-toastr';
//Delete accounts of the customer
@Component({
  selector: 'app-deletecustomer',
  templateUrl: './deletecustomer.component.html',
  styleUrls: ['./deletecustomer.component.css']
})
export class DeletecustomerComponent implements OnInit {

  account:Account={
    accountId:0,
    customerId:"",
    balance:0,
    accountType:"",
    ownerName:"",
  } 
  isCustomerDeleted=false;
  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }
  //to delete customer details from the employee side
  deleteCustomer(){
    this.employeeService.deleteCustomer(this.account.customerId).subscribe(
      data=>{
        console.log("customer deleted:"+data);
        this.isCustomerDeleted=true;

      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
}
