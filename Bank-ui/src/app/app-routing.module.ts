import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateaccountforcustomerComponent } from './createaccountforcustomer/createaccountforcustomer.component';
import { CreatecustomerComponent } from './createcustomer/createcustomer.component';
import { CustomeraccountsComponent } from './customeraccounts/customeraccounts.component';
import { CustomerdashboardComponent } from './customerdashboard/customerdashboard.component';
import { DeletecustomerComponent } from './deletecustomer/deletecustomer.component';
import { DepositComponent } from './deposit/deposit.component';
import { EmployeedashboardComponent } from './employeedashboard/employeedashboard.component';
import { ErrorstatusComponent } from './errorstatus/errorstatus.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { ServicechargedeductionComponent } from './servicechargededuction/servicechargededuction.component';
import { TransactionaldetailsComponent } from './transactionaldetails/transactionaldetails.component';
import { TransferComponent } from './transfer/transfer.component';
import { ViewthecustomerComponent } from './viewthecustomer/viewthecustomer.component';
import { WithdrawComponent } from './withdraw/withdraw.component';

const routes: Routes = [
  {path:"",redirectTo:"/home", pathMatch:"full"},
  {path:"home",component:HomeComponent,},
  {path:"customerdashboard",component:CustomerdashboardComponent,canActivate:[AuthGaurdService]},
  {path:"customeraccounts",component:CustomeraccountsComponent,canActivate:[AuthGaurdService]},
  {path:"deposit",component:DepositComponent,canActivate:[AuthGaurdService]},
  {path:"transfer",component:TransferComponent,canActivate:[AuthGaurdService]},
  {path:"transactionaldetails",component:TransactionaldetailsComponent,canActivate:[AuthGaurdService]},
  {path:"withdraw",component:WithdrawComponent,canActivate:[AuthGaurdService]},
  {path:"employeedashboard",component:EmployeedashboardComponent,canActivate:[AuthGaurdService] },
  {path:"createcustomer",component:CreatecustomerComponent,canActivate:[AuthGaurdService]},
  {path:"createaccountforcustomer",component:CreateaccountforcustomerComponent,canActivate:[AuthGaurdService]},
  {path:"deletecustomer",component:DeletecustomerComponent,canActivate:[AuthGaurdService]},
  {path:"viewthecustomer",component:ViewthecustomerComponent,canActivate:[AuthGaurdService]},
  {path:"servicechargededuction",component:ServicechargedeductionComponent,canActivate:[AuthGaurdService]},
  {path:"login",component:LoginComponent},
  {path:"error",component:ErrorstatusComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
