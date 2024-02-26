package com.olta.fundManager.TransactionService.Service;



import com.olta.fundManager.TransactionService.Repository.TransactionDetails;
import com.olta.fundManager.TransactionService.entities.Transaction;
import com.olta.fundManager.TransactionService.model.TransactionDTO;

import java.util.List;

public interface TransactionService {
    public List<Transaction> getAllTransactions();

    public List<TransactionDetails> getAllTransactions(Integer monthYear, Long fundId);
    public Transaction getTransactionById(Long transactionId);
    public Transaction saveTransaction(Transaction transaction);
    public Transaction updatePrincipalAmtFlag(Long transactionId, Character isPrincipalAmtPaid);
    public Transaction updateInterestAmtFlag(Long transactionId, Character isInterestAmtPaid);
    public Transaction updateLoanAndInterest(TransactionDTO transactionDTO);
    public void deleteTransaction(Long transactionId);

    public List<Transaction> createTransactions(List<Transaction> transactions);
}
