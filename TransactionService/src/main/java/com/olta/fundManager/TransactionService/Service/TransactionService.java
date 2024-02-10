package com.olta.fundManager.TransactionService.Service;


import com.olta.fundManager.TransactionService.entities.Transaction;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();

    public List<Transaction> getAllTransactions(String monthYear,Long fundId);
    public Transaction getTransactionById(Long transactionId);
    public Transaction saveTransaction(Transaction transaction);
    public void deleteTransaction(Long transactionId);
}
