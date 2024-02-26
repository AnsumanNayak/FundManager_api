package com.olta.fundManager.TransactionService.controller;

import com.olta.fundManager.TransactionService.Repository.TransactionDetails;
import com.olta.fundManager.TransactionService.Service.TransactionService;
import com.olta.fundManager.TransactionService.entities.Transaction;
import com.olta.fundManager.TransactionService.model.TransactionDTO;
import lombok.SneakyThrows;
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
    public List<TransactionDetails> getMonthlyTransactions(@RequestParam(name = "monthCounter", required = true) Integer monthCounter
            , @RequestParam(name = "fundId", required = true) Long fundId) {
        return transactionService.getAllTransactions(monthCounter,fundId);
    }

    @GetMapping("/updatePrincipalAmtFlag")
    public Transaction updatePrincipalAmtFlag(@RequestParam(name = "transactionId", required = true) Long transactionId
            , @RequestParam(name = "isPrincipalAmtPaid", required = true) Character isPrincipalAmtPaid) {
        return transactionService.updatePrincipalAmtFlag(transactionId,isPrincipalAmtPaid);
    }
    @GetMapping("/updateInterestAmtFlag")
    public Transaction updateInterestAmtFlag(@RequestParam(name = "transactionId", required = true) Long transactionId
            , @RequestParam(name = "isPrincipalAmtPaid", required = true) Character isInterestAmtPaid) {
        return transactionService.updateInterestAmtFlag(transactionId,isInterestAmtPaid);
    }

    @PostMapping("/transaction")
    public Transaction saveTransaction(@RequestBody Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @PostMapping("/updateLoan")
    public Transaction updateLoanAndInterest(@RequestBody TransactionDTO transactionDTO) {
        return transactionService.updateLoanAndInterest(transactionDTO);
    }

    @PostMapping
    public List<Transaction> createTransactions(@RequestBody List<Transaction> transactions) {
        return transactionService.createTransactions(transactions);
    }


    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
