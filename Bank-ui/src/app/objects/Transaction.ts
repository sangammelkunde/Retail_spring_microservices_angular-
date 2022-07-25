export interface Transaction{
    id:number,
    sourceAccountId:number,
    sourceOwnerName:string,
    targetAccountId:number,
    targetOwnerName:string,
    amount:number,
    dateOfTransaction:Date,
    typeOfTransaction:string,
}