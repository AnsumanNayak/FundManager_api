package com.olta.fundManager.AdminApp.service.Impl;

import com.olta.fundManager.AdminApp.constants.AdminAppConstant;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.mapper.FundMapper;
import com.olta.fundManager.AdminApp.model.FundDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if(funds.isEmpty()){
            throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.ADMIN_ID,adminId));
        }
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
    public Fund saveFund(Fund fund) {
        if(!fundRepository.findByFundNameAndAdminId(fund.getFundName(),fund.getAdminId()).isEmpty()){
            throw new CustomException(AdminAppConstant.FUND_ALREADY_EXIST);
        }
        return fundRepository.save(fund);
    }

    @Override
    public void deleteFund(Long fundId) {
        fundRepository.deleteById(fundId);
    }
}
