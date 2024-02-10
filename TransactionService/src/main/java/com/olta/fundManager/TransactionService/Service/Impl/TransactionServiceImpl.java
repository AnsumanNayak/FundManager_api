package com.olta.fundManager.TransactionService.Service.Impl;

import com.olta.fundManager.TransactionService.Repository.TransactionRepository;
import com.olta.fundManager.TransactionService.Service.TransactionService;
import com.olta.fundManager.TransactionService.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactions(String monthYear, Long fundId) {
        return transactionRepository.findByMonthYearAndFundId(monthYear,fundId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
