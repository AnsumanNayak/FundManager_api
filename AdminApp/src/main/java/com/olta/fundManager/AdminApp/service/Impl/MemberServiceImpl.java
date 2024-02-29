package com.olta.fundManager.AdminApp.service.Impl;


import com.olta.fundManager.AdminApp.constants.AdminAppConstant;
import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.exception.CustomException;
import com.olta.fundManager.AdminApp.mapper.MemberMapper;
import com.olta.fundManager.AdminApp.mapper.TransactionMapper;
import com.olta.fundManager.AdminApp.model.MemberDTO;
import com.olta.fundManager.AdminApp.repository.FundRepository;
import com.olta.fundManager.AdminApp.repository.MemberRepository;
import com.olta.fundManager.AdminApp.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    @Transactional
    public Set<Member> saveMember(List<MemberDTO> members) {
        Fund fund = null;
        for (MemberDTO dto : members) {
            if (!this.isExistingMember(dto)) {
                Long memberId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                for (Long fundId : dto.getFundIds()) {
                    Optional<Fund> fundOptional = fundRepository.findById(fundId);
                    if (fundOptional.isEmpty()) {
                        throw new CustomException(String.format(AdminAppConstant.FUND_NOT_FOUND, AdminAppConstant.FUND_ID, fundId));
                    }
                    Member member = mapper.mapDTOToEntity(dto);
                    member.setMemberId(memberId);
                    fund = fundOptional.get();

                    for (int i = 1; i <= dto.getFundTenure(); i++) {
                        Transaction transaction = new Transaction();
                        transaction.setMonthCounter(i);
                        transaction.setFund(fund);
                        member.addTransaction(transaction);
                    }
                    member.addFund(fund);
                    Member savedMember = memberRepository.save(member);
                }
            }
        }
        if(Objects.isNull(fund)){
            throw new CustomException(AdminAppConstant.MEMBER_ALREADY_EXIST);
        }
        return fundRepository.save(fund).getMembers();
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public Set<Member> getMembersByFundId(Long fundId) {

         Set<Member> members = fundRepository.findMembersByFundId(fundId);
         if(members.isEmpty()){
             throw new CustomException(String.format(AdminAppConstant.MEMBER_NOT_FOUND,AdminAppConstant.FUND_ID,fundId));
         }
         return members;
    }

    @Override
    public boolean isExistingMember(MemberDTO memberDTO) {
        return !memberRepository.findByNameAndDob(memberDTO.getName(),memberDTO.getDob()).isEmpty();
    }

}
