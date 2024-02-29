package com.olta.fundManager.AdminApp.mapper;

import com.olta.fundManager.AdminApp.entities.Member;
import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.model.MemberDTO;
import com.olta.fundManager.AdminApp.model.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {
    private final ModelMapper modelMapper;

    public MemberMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public MemberDTO mapEntityToDTO(Member member) {
        return modelMapper.map(member, MemberDTO.class);
    }
    public Member mapDTOToEntity(MemberDTO memberDTO) {
        return modelMapper.map(memberDTO, Member.class);
    }
}
