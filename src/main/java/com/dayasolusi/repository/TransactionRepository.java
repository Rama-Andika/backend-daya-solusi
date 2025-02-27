package com.dayasolusi.repository;

import com.dayasolusi.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t ORDER BY t.transactionDate desc")
    List<Transaction> findAll();
    Optional<Transaction> findTransactionByProductID(String productID);
}
