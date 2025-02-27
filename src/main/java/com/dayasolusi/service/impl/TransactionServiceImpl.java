package com.dayasolusi.service.impl;

import com.dayasolusi.exception.NotFoundException;
import com.dayasolusi.mapper.TransactionMapper;
import com.dayasolusi.model.dto.SuccessResponse;
import com.dayasolusi.model.dto.TransactionDTO;
import com.dayasolusi.model.entity.Transaction;
import com.dayasolusi.repository.TransactionRepository;
import com.dayasolusi.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ResponseEntity<Object> getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();
        SuccessResponse<List<Transaction>> response = new SuccessResponse<>("success", transactions);
        System.out.println(response);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Object> saveTransaction(TransactionDTO transactionDTO) {
        Transaction transaction;
        transaction = TransactionMapper.toEntity(transactionDTO);

        int randomNumber;
        do {
            Random random = new Random();
            randomNumber = 10000 + random.nextInt(90000);
        } while (transactionRepository.findTransactionByProductID(String.valueOf(randomNumber)).isPresent());

        transaction.setProductID(String.valueOf(randomNumber));

        transaction = transactionRepository.save(transaction);
        SuccessResponse<Long> response = new SuccessResponse<>("success", transaction.getId());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Object> updateTransaction(Long id, TransactionDTO transactionDTO) {
        Transaction transaction;
        transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("transaction not found"));
        transaction = TransactionMapper.updateToEntity(transaction, transactionDTO);

        transaction = transactionRepository.save(transaction);
        SuccessResponse<Long> response = new SuccessResponse<>("success", transaction.getId());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Object> getTransaction(Long id) {
        Transaction transaction;
        transaction = transactionRepository.findById(id).orElseThrow(() -> new NotFoundException("transaction not found"));

        SuccessResponse<Transaction> response = new SuccessResponse<>("success", transaction);
        return ResponseEntity.ok(response);
    }
}
