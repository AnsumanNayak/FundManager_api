package com.olta.fundManager.AdminApp.service;




import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.model.TransactionDTO;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();

    public List<TransactionDTO> getAllTransactions(Integer monthCounter, Long fundId);
    public Transaction getTransactionById(Long transactionId);
    public Transaction saveTransaction(Transaction transaction);
    public Transaction updatePrincipalAmtFlag(Long transactionId, boolean isPrincipalAmtPaid);
    public Transaction updateInterestAmtFlag(Long transactionId, boolean isInterestAmtPaid);
    public Transaction updateLoanAndInterest(TransactionDTO transactionDTO);
    public void deleteTransaction(Long transactionId);

    public List<Transaction> createTransactions(List<Transaction> transactions);
}
