package com.dayasolusi.service;

import com.dayasolusi.mapper.TransactionMapper;
import com.dayasolusi.model.dto.SuccessResponse;
import com.dayasolusi.model.dto.TransactionDTO;
import com.dayasolusi.model.entity.Transaction;
import com.dayasolusi.model.enums.Status;
import com.dayasolusi.repository.TransactionRepository;
import com.dayasolusi.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void testGetTransactions() {
        Transaction transaction = new Transaction(1L,"2","iphone","1","rama", Status.SUCCESS, LocalDateTime.now(),"rama",LocalDateTime.now());
        Transaction transaction2 = new Transaction(2L,"4","samsung","1","rama", Status.SUCCESS, LocalDateTime.now(),"rama", LocalDateTime.now());
        List<Transaction> transactionList = Arrays.asList(transaction, transaction2);

        when(transactionRepository.findAll()).thenReturn(transactionList);

        ResponseEntity<Object> response = transactionService.getTransactions();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("success", ((SuccessResponse<?>) Objects.requireNonNull(response.getBody())).getMessage());
        assertEquals(transactionList, ((SuccessResponse<?>) response.getBody()).getData());

        verify(transactionRepository).findAll();
    }

    @Test
    void testSaveTransaction(){
        TransactionDTO transactionDTO = new TransactionDTO("Iphone 15 pro max","2","Rama", Status.FAILED, LocalDateTime.now(), "Juan");
        Transaction transaction = new Transaction(1L, "2", "Iphone 15 pro max","2","Rama",Status.FAILED,LocalDateTime.now(),"Juan", LocalDateTime.now());

        try(MockedStatic<TransactionMapper> mockMapper = Mockito.mockStatic(TransactionMapper.class)){
            mockMapper.when(() -> TransactionMapper.toEntity(transactionDTO)).thenReturn(transaction);

            when(transactionRepository.findTransactionByProductID(anyString()))
                    .thenReturn(Optional.of(new Transaction()))
                    .thenReturn(Optional.empty());

            when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

            ResponseEntity<Object> response = transactionService.saveTransaction(transactionDTO);

            assertThat(response.getStatusCodeValue()).isEqualTo(200);
            assertThat(((SuccessResponse<?>) Objects.requireNonNull(response.getBody())).getMessage()).isEqualTo("success");
            assertThat(((SuccessResponse<?>) response.getBody()).getData()).isEqualTo(1L);

            verify(transactionRepository).save(any(Transaction.class));
        }


    }

    @Test
    void testUpdateTransaction(){
        TransactionDTO transactionDTO = new TransactionDTO("Iphone 15 pro max","2","Rama", Status.FAILED, LocalDateTime.now(), "Juan");
        Transaction transaction = new Transaction(1L, "2", "Iphone 15 pro max","2","Rama",Status.FAILED,LocalDateTime.now(),"Juan", LocalDateTime.now());

        try(MockedStatic<TransactionMapper> mockedStatic = Mockito.mockStatic(TransactionMapper.class)) {
            when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));
            mockedStatic.when(() -> TransactionMapper.updateToEntity(transaction, transactionDTO)).thenReturn(transaction);
            when(transactionRepository.save(transaction)).thenReturn(transaction);

            ResponseEntity<Object> response = transactionService.updateTransaction(1L, transactionDTO);
            SuccessResponse<?> successResponse = (SuccessResponse<?>) response.getBody();

            assertEquals(200, response.getStatusCodeValue());
            assertEquals("success", successResponse.getMessage());
            assertEquals(1L, successResponse.getData());

            verify(transactionRepository).findById(1L);
            verify(transactionRepository).save(transaction);
        }
    }

    @Test
    void testGetTransaction(){
        Transaction transaction = new Transaction(1L, "2", "Iphone 15 pro max","2","Rama",Status.FAILED,LocalDateTime.now(),"Juan", LocalDateTime.now());
        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        ResponseEntity<Object> response = transactionService.getTransaction(1L);
        SuccessResponse<?> successResponse = (SuccessResponse<?>) response.getBody();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("success", successResponse.getMessage());
        assertEquals(transaction, successResponse.getData());

        verify(transactionRepository).findById(1L);

    }
}
