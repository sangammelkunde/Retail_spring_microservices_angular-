package com.cognizant.RetailBanking.Customers.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.RetailBanking.Customers.model.CustomerEntity;



@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

}