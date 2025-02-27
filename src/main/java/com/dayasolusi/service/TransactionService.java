package com.dayasolusi.service;

import com.dayasolusi.model.dto.TransactionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface TransactionService {
    ResponseEntity<Object> getTransactions();
    ResponseEntity<Object> saveTransaction(TransactionDTO transactionDTO);
    ResponseEntity<Object> updateTransaction(Long id, TransactionDTO transactionDTO);
    ResponseEntity<Object> getTransaction(Long id);
}
