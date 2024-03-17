package com.olta.fundManager.AdminApp.mapper;

import com.olta.fundManager.AdminApp.entities.Transaction;
import com.olta.fundManager.AdminApp.model.TransactionDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Transaction, TransactionDTO> typeMap = modelMapper.createTypeMap(Transaction.class, TransactionDTO.class);
        // Custom mappings
        typeMap.addMapping(src -> src.getFund().getFundId(), TransactionDTO::setFundId);
        typeMap.addMapping(src -> src.getMember().getMemberId(), TransactionDTO::setMemberId);
        typeMap.addMapping(src -> src.getMember().getName(), TransactionDTO::setName);
        typeMap.addMapping(src -> src.getFund().getMonthlyInstallment(), TransactionDTO::setMonthlyInstallment);

    }

    public TransactionDTO mapEntityToDTO(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }
    public Transaction mapDTOToEntity(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
