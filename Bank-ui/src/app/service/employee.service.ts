import { CustomerEntity } from './../objects/CustomerEntity';
import { AccountCreationStatus } from './../objects/AccountCreationStatus';
import { MS_URLS } from './../objects/MsUrls';
import { TransactionStatus } from './../objects/TransactionStatus';
import { AccountInfo } from './../objects/AccountInfo';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Account } from '../objects/Account';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private httpClient: HttpClient) { }

  deposit(accountInfo:AccountInfo){
    return this.httpClient.post<TransactionStatus>(`${MS_URLS.account}/deposit`,accountInfo);
  }

  createAccount(customerId:string,account:Account){
    return this.httpClient.post<AccountCreationStatus>(`${MS_URLS.account}/createAccount/${customerId}`,account);
  }

  getCustomerAccounts(customerId:string){
    return this.httpClient.get<Account[]>(`${MS_URLS.account}/getCustomerAccounts/${customerId}`);

  }

  getAllAccounts(){
    return this.httpClient.get<Account[]>(`${MS_URLS.account}/getAllAccounts`);

  }

  getCustomerDetails(customerId:string){
    return this.httpClient.get<CustomerEntity>(`${MS_URLS.customer}/getCustomerDetails/${customerId}`);
  }

  createCustomer(customer:CustomerEntity){
    return this.httpClient.post<AccountCreationStatus>(`${MS_URLS.customer}/createCustomer`,customer);

  }

  deleteCustomer(customerId:string){
    return this.httpClient.delete(`${MS_URLS.customer}/deleteCustomer/${customerId}`);
  }

  deductServiceCharges(){
    return this.httpClient.get(`${MS_URLS.customer}/deductServiceCharges`);
  
  }

  
}
