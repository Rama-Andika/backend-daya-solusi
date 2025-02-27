package com.dayasolusi.mapper;

import com.dayasolusi.model.dto.TransactionDTO;
import com.dayasolusi.model.entity.Transaction;

public class TransactionMapper {
    public static Transaction toEntity(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();
        transaction.setProductName(transactionDTO.getProductName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCustomerName(transactionDTO.getCustomerName());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setCreateBy(transactionDTO.getCreateBy());
        return transaction;
    }

    public static Transaction updateToEntity(Transaction transaction, TransactionDTO transactionDTO){
        transaction.setProductName(transactionDTO.getProductName());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setCustomerName(transactionDTO.getCustomerName());
        transaction.setStatus(transactionDTO.getStatus());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());
        transaction.setCreateBy(transactionDTO.getCreateBy());
        return transaction;
    }
}
