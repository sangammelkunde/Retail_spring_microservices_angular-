import { Router } from '@angular/router';
import { CustomerEntity } from './../objects/CustomerEntity';
import { EmployeeService } from './../service/employee.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-viewthecustomer',
  templateUrl: './viewthecustomer.component.html',
  styleUrls: ['./viewthecustomer.component.css']
})
export class ViewthecustomerComponent implements OnInit {

  customerId="";
  customer:CustomerEntity={
    userid:"",
    username:"",
    password:"",
    dateOfBirth:"",
    pan_no:"",
    address:"",
  };
  isFetched=false;
  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }

  viewCustomer(){
    this.employeeService.getCustomerDetails(this.customerId).subscribe(
      data=>{
        console.log(data);
        this.isFetched=true;
        data.dateOfBirth=data.dateOfBirth.substr(0,10);
        this.customer=data;
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
}
