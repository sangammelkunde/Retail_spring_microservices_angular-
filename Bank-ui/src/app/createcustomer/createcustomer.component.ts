import { Router } from '@angular/router';
import { EmployeeService } from './../service/employee.service';
import { Component, OnInit } from '@angular/core';
import { CustomerEntity } from './../objects/CustomerEntity';
import { ToastrService } from 'ngx-toastr';

//this method is used to create customer
@Component({
  selector: 'app-createcustomer',
  templateUrl: './createcustomer.component.html',
  styleUrls: ['./createcustomer.component.css']
})
export class CreatecustomerComponent implements OnInit {

  

  customer:CustomerEntity={
    userid:"",
    username:"",
    password:"",
    dateOfBirth:"",
    pan_no:"",
    address:"",
  }

  isCustomerCreated=false;

  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }

  createCustomer(){
    console.log(this.customer)
    this.employeeService.createCustomer(this.customer).subscribe(
      data=>{
        console.log(data);
        this.isCustomerCreated=true;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }

}
