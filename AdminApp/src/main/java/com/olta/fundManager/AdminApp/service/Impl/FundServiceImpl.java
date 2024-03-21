package com.olta.fundManager.AdminApp.service.Impl;

import com.olta.fundManager.AdminApp.constants.AdminAppConstant;
import com.olta.fundManager.AdminApp.constants.Verification;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.entities.TransactionVerification;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.mapper.FundMapper;
import com.olta.fundManager.AdminApp.model.FundDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    private FundRepository fundRepository;

    @Autowired
    FundMapper fundMapper;
    @Override
    public List<Fund> getAllFunds() {
        return fundRepository.findAll();
    }

    @Override
    public Fund getFundById(Long fundId) {
        return fundRepository.findById(fundId).orElseThrow(() -> new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.FUND_ID,fundId)));
    }

    @Override
    public List<FundDTO> getFundByAdminId(Integer adminId) {
        List<Fund> funds = fundRepository.findByAdminId(adminId);
        /*if(funds.isEmpty()){
            throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.ADMIN_ID,adminId));
        }*/
        return funds.stream().map(e -> {
            FundDTO fundDTO = fundMapper.mapEntityToDTO(e);
            fundDTO.setTotalMembers(e.getMembers().size());
            return fundDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Set<Member> addMembersToFund(List<Member> members, Long fundId) {
        Fund fund = fundRepository.findById(fundId).orElseThrow(() -> new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND, AdminAppConstant.FUND_ID, fundId)));
        members.forEach(member -> {
            for (int i = 1; i <= fund.getTenure(); i++) {
                Transaction transaction = new Transaction();
                transaction.setMonthCounter(i);
                transaction.setFund(fund);
                member.addTransaction(transaction);
                fund.addTransaction(transaction);
            }
            fund.addMember(member);
        });
        return fundRepository.save(fund).getMembers();
    }

    @Override
    public Fund saveFund(FundDTO fundDTO) {
        Long fundId = fundDTO.getFundId();
        if(Objects.isNull(fundId) && !fundRepository.findByFundNameAndAdminId(fundDTO.getFundName(),fundDTO.getAdminId()).isEmpty()){
            throw new CustomException(AdminAppConstant.FUND_ALREADY_EXIST);
        } else if (!Objects.isNull(fundId)) {
            Fund fund = fundRepository.findById(fundId).orElse(new Fund());
            fundMapper.updateEntity(fund,fundDTO);
            return fundRepository.save(fund);
        }
        Fund fund = fundMapper.mapDTOToEntity(fundDTO);
        for (int i = 1; i <= fundDTO.getTenure(); i++) {
            TransactionVerification transactionVerification = new TransactionVerification();
            transactionVerification.setMonthCounter(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
            if(i==1){
                transactionVerification.setVerification(Verification.INPROGRESS.getValue());
                transactionVerification.setMonthYear(fund.getEffBeginDt().format(formatter));
            }else{
                transactionVerification.setMonthYear(fund.getEffBeginDt().plusMonths(i-1).format(formatter));
            }
            fund.addTransactionVerification(transactionVerification);
        }
        return fundRepository.save(fund);
    }

    @Override
    public void deleteFund(Long fundId) {
        fundRepository.deleteById(fundId);
    }
}
