package com.olta.fundManager.AdminApp.controller;


import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.mapper.TransactionMapper;
import com.olta.fundManager.AdminApp.model.TransactionDTO;
import com.olta.fundManager.AdminApp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
@CrossOrigin
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    TransactionMapper mapper;
    @GetMapping
    public List<TransactionDTO> getAllTransactions() {
        return transactionService.getAllTransactions().stream().map(e -> mapper.mapEntityToDTO(e)).collect(Collectors.toList());
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable Long transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @GetMapping("/monthlyTransactions")
    public List<TransactionDTO> getMonthlyTransactions(@RequestParam(name = "monthCounter", required = true) Integer monthCounter
            , @RequestParam(name = "fundId", required = true) Long fundId) {
        return transactionService.getAllTransactions(monthCounter,fundId);

    }

    @GetMapping("/updatePrincipalAmtFlag")
    public Transaction updatePrincipalAmtFlag(@RequestParam(name = "transactionId", required = true) Long transactionId
            , @RequestParam(name = "isPrincipalAmtPaid", required = true) boolean isPrincipalAmtPaid) {
        return transactionService.updatePrincipalAmtFlag(transactionId,isPrincipalAmtPaid);
    }
    @GetMapping("/updateInterestAmtFlag")
    public Transaction updateInterestAmtFlag(@RequestParam(name = "transactionId", required = true) Long transactionId
            , @RequestParam(name = "isPrincipalAmtPaid", required = true) boolean isInterestAmtPaid) {
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
