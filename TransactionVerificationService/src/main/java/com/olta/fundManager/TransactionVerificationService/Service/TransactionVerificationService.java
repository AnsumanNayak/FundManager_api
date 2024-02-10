package com.olta.fundManager.TransactionVerificationService.Service;


import com.olta.fundManager.TransactionVerificationService.entities.Transaction;

import java.util.List;

public interface TransactionVerificationService {
    public List<Transaction> getAllTransactions();

    public List<Transaction> getAllTransactions(String monthYear,Long fundId);
    public Transaction getTransactionById(Long transactionId);
    public Transaction saveTransaction(Transaction transaction);
    public void deleteTransaction(Long transactionId);
}
