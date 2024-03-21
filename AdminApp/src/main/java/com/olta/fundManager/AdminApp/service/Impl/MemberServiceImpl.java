package com.olta.fundManager.AdminApp.service.Impl;


import com.olta.fundManager.AdminApp.constants.AdminAppConstant;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.mapper.MemberMapper;
import com.olta.fundManager.AdminApp.model.MemberDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.repository.MemberRepository;
import com.olta.fundManager.AdminApp.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private MemberMapper mapper;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(String.format(AdminAppConstant.MEMBER_NOT_FOUND,AdminAppConstant.MEMBER_ID,memberId)));
    }

    @Override
    @Transactional
    public Set<Member> saveMember(List<MemberDTO> members) {
        Set<Member> membersResp = new HashSet<>();
        Fund fund = null;
        boolean isMemberUpdated = false;
        for (MemberDTO dto : members) {
            if (Objects.isNull(dto.getMemberId()) && !this.isExistingMember(dto)) {
//                Long memberId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                for (Long fundId : dto.getFundIds()) {
                    fund = fundRepository.findById(fundId).orElseThrow(() -> new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND, AdminAppConstant.FUND_ID, fundId)));

                    Member member = mapper.mapDTOToEntity(dto);
//                    member.setMemberId(memberId);


                    for (int i = 1; i <= fund.getTenure(); i++) {
                        Transaction transaction = new Transaction();
                        transaction.setMonthCounter(i);
                        transaction.setFund(fund);
                        member.addTransaction(transaction);
                    }
                    member.addFund(fund);
                    memberRepository.save(member);
                }
            } else if (Objects.nonNull(dto.getMemberId())) {
                isMemberUpdated = true;
                Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new CustomException(String.format(AdminAppConstant.MEMBER_NOT_FOUND,AdminAppConstant.MEMBER_ID,dto.getMemberId())));
                mapper.updateEntity(member,dto);
                membersResp.add(memberRepository.save(member));
            }
        }
        if(!isMemberUpdated && Objects.isNull(fund)){
            throw new CustomException(AdminAppConstant.MEMBER_ALREADY_EXIST);
        }
        else if(Objects.nonNull(fund)){
            return fundRepository.save(fund).getMembers();
        }else {
            return membersResp;
        }
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public List<MemberDTO> getMemberTransactionDetails(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Member member = memberOptional.orElseThrow(() -> new CustomException(String.format(AdminAppConstant.MEMBER_NOT_FOUND, AdminAppConstant.MEMBER_ID, memberId)));
        Set<Transaction> transactions = member.getTransactions();

        return transactions
                .stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getFund().getFundId(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                transaction -> transaction.getLoanBorrowed().subtract(transaction.getLoanReturned()),
                                BigDecimal::add)
                )).entrySet()
                .stream()
                .map(entry -> {
                    MemberDTO memberDTO = new MemberDTO();
                    memberDTO.setFundId(entry.getKey());
                    memberDTO.setTotalLoan(entry.getValue());
                    BigDecimal totalInterest = transactions.stream()
                            .filter(t -> t.getFund().getFundId().equals(entry.getKey())
                                    && t.getIsInterestAmtPaid())
                            .map(Transaction::getInterestAmount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    memberDTO.setTotalInterest(totalInterest);
                    final BigDecimal[] totalPrincipal = {BigDecimal.ZERO};
                    transactions.forEach(t -> {
                        Fund fund = t.getFund();
                        if (fund.getFundId().equals(entry.getKey())
                                && t.getIsPrincipalAmtPaid()) {
                            totalPrincipal[0] = totalPrincipal[0].add(fund.getMonthlyInstallment());
                        }
                    });
                    memberDTO.setTotalPrincipal(totalPrincipal[0]);
                    return memberDTO;
                }).collect(Collectors.toList());
    }


    @Override
    public Set<Member> getMembersByFundId(Long fundId) {

         Optional<Fund> fund = fundRepository.findById(fundId);
         if(fund.isEmpty()){
             throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND,AdminAppConstant.FUND_ID,fundId));
         }
         return fund.get().getMembers();
    }

    @Override
    public boolean isExistingMember(MemberDTO memberDTO) {
        return !memberRepository.findByNameContainingAndDob(memberDTO.getName(),memberDTO.getDob()).isEmpty();
    }

    @Override
    public Set<Member> getAllMembersByAdminId(Integer adminId) {
        Set<Member> members = new HashSet<>();
        List<Fund> funds = fundRepository.findByAdminId(adminId);
        funds.forEach(fund -> {
                members.addAll(fund.getMembers());
        });
        return members;
    }

}
