package com.olta.fundManager.MemberService.Service.Impl;


import com.olta.fundManager.MemberService.Exception.MemberServiceException;
import com.olta.fundManager.MemberService.Repository.MemberRepository;
import com.olta.fundManager.MemberService.Service.MemberService;
import com.olta.fundManager.MemberService.configuration.RestClient;
import com.olta.fundManager.MemberService.entities.Member;
import com.olta.fundManager.MemberService.model.MemberDTO;
import com.olta.fundManager.MemberService.model.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    RestClient restClient;
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    @Override
    public List<Member> saveMember(List<MemberDTO> members) {
        List<Member> memberList= new ArrayList<>();
        members.forEach(dto -> {
            if(!this.isExistingMember(dto)) {
                Long memberId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
                Arrays.stream(dto.getFundIds()).forEach(fundId -> {

                    Member member = mapper.map(dto, Member.class);
                    member.setMemberId(memberId);
                    member.setFundId(fundId);
                    memberList.add(member);
                });
            }
        });
        if(memberList.isEmpty())
            throw new MemberServiceException("Member is already exist in the system.");
        memberRepository.saveAll(memberList);
        addTransactionsForMembers(memberList);
        return memberList;
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    @Override
    public List<Member> getMembersByFundId(Long fundId) {
        return  memberRepository.findByFundId(fundId);
    }

    @Override
    public boolean isExistingMember(MemberDTO memberDTO) {
        return !memberRepository.findByNameAndDob(memberDTO.getName(),memberDTO.getDob()).isEmpty();
    }


    protected void addTransactionsForMembers(List<Member> members){
        List<TransactionDTO> transactionDTOS = new ArrayList<>();
        members.forEach(member ->{
            for(int i=1;i<=member.getFundTenure();i++){
                TransactionDTO dto = new TransactionDTO();
                dto.setMemberId(member.getMemberId());
                dto.setFundId(member.getFundId());
                dto.setMonthCounter(i);
                transactionDTOS.add(dto);
            }
        });
        restClient.createTransactions(transactionDTOS);
    }
}
