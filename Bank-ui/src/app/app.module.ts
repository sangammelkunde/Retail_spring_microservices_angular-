import { BasicAuthHtppInterceptorService } from './service/basic-auth-interceptor.service';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { CustomeraccountsComponent } from './customeraccounts/customeraccounts.component';
import { DepositComponent } from './deposit/deposit.component';
import { TransferComponent } from './transfer/transfer.component';
import { TransactionaldetailsComponent } from './transactionaldetails/transactionaldetails.component';
import { WithdrawComponent } from './withdraw/withdraw.component';
import { EmployeedashboardComponent } from './employeedashboard/employeedashboard.component';
import { CreatecustomerComponent } from './createcustomer/createcustomer.component';
import { CreateaccountforcustomerComponent } from './createaccountforcustomer/createaccountforcustomer.component';
import { DeletecustomerComponent } from './deletecustomer/deletecustomer.component';
import { ViewthecustomerComponent } from './viewthecustomer/viewthecustomer.component';
import { ServicechargedeductionComponent } from './servicechargededuction/servicechargededuction.component';
import { ErrorstatusComponent } from './errorstatus/errorstatus.component';
import { CustomerService } from './service/customer.service';
import { EmployeeService } from './service/employee.service';
import { NavbarComponent } from './navbar/navbar.component';
import { ErrorInterceptor } from './service/error-interceptor.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatCardModule} from '@angular/material/card';
import { NavbarTopComponent } from './navbar-top/navbar-top.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    CustomerdashboardComponent,
    CustomeraccountsComponent,
    DepositComponent,
    TransferComponent,
    TransactionaldetailsComponent,
    WithdrawComponent,
    EmployeedashboardComponent,
    CreatecustomerComponent,
    CreateaccountforcustomerComponent,
    DeletecustomerComponent,
    ViewthecustomerComponent,
    ServicechargedeductionComponent,
    ErrorstatusComponent,
    NavbarComponent,
    NavbarTopComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    ToastrModule.forRoot()
  ],

  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    CustomerService, 
    EmployeeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
