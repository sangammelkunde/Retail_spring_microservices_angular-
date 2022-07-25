import { Router } from '@angular/router';
import { EmployeeService } from './../service/employee.service';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-servicechargededuction',
  templateUrl: './servicechargededuction.component.html',
  styleUrls: ['./servicechargededuction.component.css']
})
export class ServicechargedeductionComponent implements OnInit {

  no_of_deducted=0;
  isDeducted=false;
  isDeductable=true;
  constructor(private router: Router,private employeeService:EmployeeService,private toastr: ToastrService) { }

  ngOnInit(): void {
    if (sessionStorage.role!="EMPLOYEE"){
      this.router.navigate(['customerdashboard']);

    }
  }
//this method calls the employeeservice to deduct the service charge
  deduct(){
    this.employeeService.deductServiceCharges().subscribe(
      data=>{
        console.log("No. of accounts bieng deducted:"+data);
        this.no_of_deducted=<number>data;
        if(this.no_of_deducted!=-1){
          console.log("not equal to -1");
          this.isDeducted=true;
          this.isDeductable=true;
        }
        else{
          console.log("equal to -1");
          this.isDeducted=false;
          this.isDeductable=false;
        }
      },error=>{
        this.toastr.error(error.message,"Error");
      }
    );
  }
}
