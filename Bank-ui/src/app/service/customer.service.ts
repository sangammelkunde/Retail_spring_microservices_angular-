import { TransactionStatus } from './../objects/TransactionStatus';
import { AccountInfo } from './../objects/AccountInfo';
import { MS_URLS } from './../objects/MsUrls';
import { AccountCreationStatus } from './../objects/AccountCreationStatus';
import { AppUser } from '../objects/AppUser';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transaction } from '../objects/Transaction';
import { Account } from '../objects/Account';
import { TransactionInput } from '../objects/TransactionInput';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {


  constructor(private httpClient: HttpClient) { }

  createAccount(customerId:string, account:Account){
    return this.httpClient.post<AccountCreationStatus>(`${MS_URLS.account}/createAccount/${customerId}`,account);
  }

  withdraw(accountInfo:AccountInfo){
    return this.httpClient.post<TransactionStatus>(`${MS_URLS.account}/withdraw`,accountInfo);
  }

  getCustomerAccounts(customerId:string){
    return this.httpClient.get<Account[]>(`${MS_URLS.account}/getCustomerAccounts/${customerId}`);
  }

  checkBalance(customerId:string){
    return this.httpClient.post<number>(`${MS_URLS.account}/checkBalance/${customerId}`,{});
  }

  getAccountStatement(accountId:string,fromDate:string,toDate:string,){
    return this.httpClient.get<Transaction[]>(`${MS_URLS.account}/getAccountStatement/${accountId}`,{
      params:{
        "from_date":fromDate,
        "to_date":toDate,

      }
    });
  }

  transfer(transactionInput:TransactionInput){
    return this.httpClient.post<TransactionStatus>(`${MS_URLS.transaction}/transfer`,transactionInput);
  }

  getAllTransactions(accountId:string){
    return this.httpClient.get<Transaction[]>(`${MS_URLS.transaction}/getAllTrans/${accountId}`);
  }
}
