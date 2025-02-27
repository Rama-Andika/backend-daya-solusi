package com.dayasolusi.controller;

import com.dayasolusi.model.dto.TransactionDTO;
import com.dayasolusi.repository.TransactionRepository;
import com.dayasolusi.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(description = "Fetch a list of all transactions")
    @GetMapping("/transactions")
    public ResponseEntity<Object> getTransactions(){
        return transactionService.getTransactions();
    }

    @PostMapping("/transaction")
    public ResponseEntity<Object> saveTransaction(@Valid @RequestBody TransactionDTO transactionDTO){
        return transactionService.saveTransaction(transactionDTO);
    }

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Object> saveTransaction(@PathVariable Long id, @Valid @RequestBody TransactionDTO transactionDTO){
        return transactionService.updateTransaction(id, transactionDTO);
    }

    @GetMapping("/transaction/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id){
        return transactionService.getTransaction(id);
    }

//    @GetMapping("/")
//    public ResponseEntity<Object> helloWorld()  {
//        try{
//            InputStream inputStream = new ClassPathResource("data.json").getInputStream();
//            List<Transaction> transactions = objectMapper.readValue(inputStream, objectMapper.getTypeFactory().constructCollectionType(List.class, Transaction.class));
//            transactionRepository.saveAll(transactions);
//            return ResponseEntity.ok().body(transactions);
//        }catch (Exception e){
//            return ResponseEntity.internalServerError().body(e.getMessage());
//        }
//    }
}
