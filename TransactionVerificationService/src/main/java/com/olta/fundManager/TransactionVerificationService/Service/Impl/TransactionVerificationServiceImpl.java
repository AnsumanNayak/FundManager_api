package com.olta.fundManager.TransactionVerificationService.Service.Impl;

import com.olta.fundManager.TransactionVerificationService.Repository.TransactionRepository;
import com.olta.fundManager.TransactionVerificationService.Service.TransactionVerificationService;
import com.olta.fundManager.TransactionVerificationService.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionVerificationServiceImpl implements TransactionVerificationService {
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
