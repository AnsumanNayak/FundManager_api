package com.olta.fundManager.AdminApp.service.Impl;

import com.olta.fundManager.AdminApp.constants.AdminAppConstant;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.mapper.TransactionMapper;
import com.olta.fundManager.AdminApp.model.TransactionDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.repository.TransactionRepository;
import com.olta.fundManager.AdminApp.service.TransactionService;
import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<TransactionDTO> getAllTransactions(Integer monthCounter, Long fundId) {
        Optional<Fund> fund = fundRepository.findById(fundId);
        if(fund.isEmpty()){
            throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.FUND_ID,fundId));
        }
        return fund.get().getTransactions().stream()
                .filter(e -> e.getMonthCounter().equals(monthCounter))
                .map(e-> mapper.mapEntityToDTO(e))
                .collect(Collectors.toList());

        //return transactionRepository.findByMonthCounterAndFundId(monthCounter,fundId);
    }

    @Override
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new CustomException(String.format(AdminAppConstant.TRANSACTION_NOT_FOUND,
                        AdminAppConstant.TRANSACTION_ID,
                        transactionId)));
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction updatePrincipalAmtFlag(Long transactionId, boolean isPrincipalAmtPaid) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()){
            Transaction transaction =transactionOptional.get();
            transaction.setIsPrincipalAmtPaid(isPrincipalAmtPaid);
            return transactionRepository.save(transaction);
        }
        else {
            throw new CustomException(String.format(AdminAppConstant.TRANSACTION_NOT_FOUND,
                    AdminAppConstant.TRANSACTION_ID,
                    transactionId));
        }
    }

    @Override
    public Transaction updateInterestAmtFlag(Long transactionId, boolean isInterestAmtPaid) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()){
            Transaction transaction = transactionOptional.get();
            transaction.setIsPrincipalAmtPaid(isInterestAmtPaid);
            return transactionRepository.save(transaction);
        }
        else {
            throw new CustomException(String.format(AdminAppConstant.TRANSACTION_NOT_FOUND,
                    AdminAppConstant.TRANSACTION_ID,
                    transactionId));
        }
    }

    @Override
    @Transactional
    public Transaction updateLoanAndInterest(TransactionDTO transactionDTO) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionDTO.getTransactionId());
        Transaction transaction = transactionOptional
                .orElseThrow(() -> new CustomException(String.format(AdminAppConstant.TRANSACTION_NOT_FOUND,
                        AdminAppConstant.TRANSACTION_ID, transactionDTO.getTransactionId())));
        BigDecimal totalLoan = transaction.getTotalLoan()
                        .add(transaction.getLoanReturned()
                        .subtract(transaction.getLoanBorrowed()));
        BigDecimal loanBorrowed = transactionDTO.getLoanBorrowed();
        BigDecimal loanReturned = transactionDTO.getLoanReturned();
        if(loanReturned.compareTo(totalLoan) > 0){
            throw new CustomException(AdminAppConstant.LOAN_IS_LESS);
        }
        BigDecimal updatedTotalLoan = totalLoan.add(loanBorrowed).subtract(loanReturned);

        BigDecimal updatedInterestAmount = updatedTotalLoan.multiply(transaction.getFund().getMonthlyInterestRate().divide(BigDecimal.valueOf(100)));

        transaction.setLoanBorrowed(loanBorrowed);
        transaction.setLoanReturned(loanReturned);
        transaction.setTotalLoan(updatedTotalLoan);
        List<Transaction> updateTransactions = transactionRepository
                .findByFund_FundIdAndMember_MemberIdAndMonthCounterGreaterThan(transaction.getFund().getFundId()
                        ,transaction.getMember().getMemberId()
                        ,transaction.getMonthCounter())
                .stream()
                .peek(e ->{
                    e.setInterestAmount(updatedInterestAmount);
                    e.setTotalLoan(updatedTotalLoan);
                }).collect(Collectors.toList());
        transactionRepository.saveAll(updateTransactions);
        return transactionRepository.save(transaction);
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
