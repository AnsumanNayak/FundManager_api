package com.olta.fundManager.TransactionService.Service.Impl;

import com.olta.fundManager.TransactionService.Exception.TransactionServiceException;
import com.olta.fundManager.TransactionService.Repository.TransactionDetails;
import com.olta.fundManager.TransactionService.Repository.TransactionRepository;
import com.olta.fundManager.TransactionService.Service.TransactionService;

import com.olta.fundManager.TransactionService.entities.Transaction;
import com.olta.fundManager.TransactionService.model.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionDetails> getAllTransactions(Integer monthCounter, Long fundId) {
        return transactionRepository.findByMonthCounterAndFundId(monthCounter,fundId);
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
    public Transaction updatePrincipalAmtFlag(Long transactionId, Character isPrincipalAmtPaid) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()){
            Transaction transaction =transactionRepository.save(transactionOptional.get());
            transaction.setIsPrincipalAmtPaid(isPrincipalAmtPaid);
            return transactionRepository.save(transaction);
        }
        else {
            throw new TransactionServiceException("No record found for the transaction id: "+transactionId);
        }
    }

    @Override
    public Transaction updateInterestAmtFlag(Long transactionId, Character isInterestAmtPaid) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()){
            Transaction transaction =transactionRepository.save(transactionOptional.get());
            transaction.setIsPrincipalAmtPaid(isInterestAmtPaid);
            return transactionRepository.save(transaction);
        }
        else {
            throw new TransactionServiceException("No record found for the transaction id: "+transactionId);
        }
    }

    @Override
    @Transactional
    public Transaction updateLoanAndInterest(TransactionDTO transactionDTO) {
        BigDecimal totalLoan = transactionDTO.getTotalLoan();
        BigDecimal loanBorrowed = transactionDTO.getLoanBorrowed();
        BigDecimal loanReturned = transactionDTO.getLoanReturned();
        BigDecimal updatedTotalLoan = totalLoan.add(loanBorrowed).subtract(loanReturned);
        BigDecimal updatedInterestAmount = updatedTotalLoan.multiply(transactionDTO.getInterestAmount().divide(BigDecimal.valueOf(100)));
        Transaction transactionEntity = mapper.map(transactionDTO,Transaction.class);
        transactionEntity.setLoanBorrowed(loanBorrowed);
        transactionEntity.setLoanReturned(loanReturned);
        transactionEntity.setTotalLoan(updatedTotalLoan);
        Transaction transaction= transactionRepository.save(transactionEntity);
        transactionRepository
                .updateInterestAmount(transactionDTO.getFundId(),
                        transactionDTO.getMemberId(),
                        transactionDTO.getMonthCounter(),
                        transactionDTO.getInterestAmount());
        return transaction;
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    @Override
    public List<Transaction> createTransactions(List<Transaction> transactions) {
        return transactionRepository.saveAll(transactions);
    }

}
