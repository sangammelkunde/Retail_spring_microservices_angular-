package com.cognizant.transactionservice.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.transactionservice.models.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	/**
	 * Gets list of transactions present in transaction table
	 * 
	 * @param accountId
	 * @return list of transactions
	 */

	List<Transaction> findBySourceAccountIdAndDateOfTransactionBetweenOrderByDateOfTransaction(long accountId,
			LocalDate from_date, LocalDate to_date);

	List<Transaction> findBySourceAccountIdOrderByDateOfTransaction(long accountId);

// 	List<Transaction> findBySourceAccountIdAndTargetAccountIdOrderByDateOfTransaction(long sourceAccountId,
//		long targetAccountId);

//   List<Transaction> findByTargetAccountIdOrderByDateOfTransaction(long targetAccountId);

}
