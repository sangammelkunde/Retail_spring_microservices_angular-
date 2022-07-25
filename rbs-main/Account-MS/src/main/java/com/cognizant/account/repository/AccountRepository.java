package com.cognizant.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.account.model.Account;

/*
 * The repository component for handling database transactions of the account microservice.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Account findByAccountId(long accountId);
	
	List<Account> findByCustomerId(String customerId);
	
	void deleteByCustomerId(String customerId);

}
