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
            throw new CustomException("No record found for the transaction id: "+transactionId);
        }
    }

    @Override
    public Transaction updateInterestAmtFlag(Long transactionId, Character isInterestAmtPaid) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);
        if(transactionOptional.isPresent()){
            Transaction transaction = transactionRepository.save(transactionOptional.get());
            transaction.setIsPrincipalAmtPaid(isInterestAmtPaid);
            return transactionRepository.save(transaction);
        }
        else {
            throw new CustomException("No record found for the transaction id: "+transactionId);
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
        Transaction transactionEntity = mapper.mapDTOToEntity(transactionDTO);
        transactionEntity.setLoanBorrowed(loanBorrowed);
        transactionEntity.setLoanReturned(loanReturned);
        transactionEntity.setTotalLoan(updatedTotalLoan);
        Transaction transaction= transactionRepository.save(transactionEntity);
        Optional<Fund> fund =fundRepository.findById(transactionDTO.getFundId());
        if(fund.isEmpty())
            throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.FUND_ID,transactionDTO.getFundId()));
        Optional<Member> member =fund.get().getMembers().stream().filter(e -> e.getMemberId().equals(transactionDTO.getMemberId())).findFirst();
        if(member.isEmpty())
            throw new CustomException(String.format(AdminAppConstant.MEMBER_NOT_FOUND,AdminAppConstant.MEMBER_ID,transactionDTO.getMemberId()));
        transactionRepository.saveAll(member.get()
                .getTransactions()
                .stream()
                .filter(e -> e.getMonthCounter()
                        .compareTo(transactionDTO.getMonthCounter()) > 0)
                .peek(e-> e.setInterestAmount(updatedInterestAmount)).collect(Collectors.toList()));
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
