package com.olta.fundManager.TransactionService.controller;

import com.olta.fundManager.TransactionService.Service.TransactionService;
import com.olta.fundManager.TransactionService.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping("/monthlyTransactions")
    public List<Transaction> getMonthlyTransactions(@RequestParam(name = "monthYear", required = true) String monthYear
            ,@RequestParam(name = "fundId", required = false) Long fundId) {
        return transactionService.getAllTransactions(monthYear,fundId);
    }

    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }


    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
