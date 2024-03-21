package com.olta.fundManager.AdminApp.mapper;

import com.olta.fundManager.AdminApp.entities.Fund;
import com.olta.fundManager.AdminApp.model.FundDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class FundMapper {
    private final ModelMapper modelMapper;
    public FundMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<Fund, FundDTO> typeMap = modelMapper.createTypeMap(Fund.class, FundDTO.class);
        // Custom mappings
//        typeMap.addMapping(src -> src.getMembers().size(), FundDTO::setTotalMembers);
    }

    public FundDTO mapEntityToDTO(Fund fund) {
        return modelMapper.map(fund, FundDTO.class);
    }
    public Fund mapDTOToEntity(FundDTO fundDTO) {
        return modelMapper.map(fundDTO, Fund.class);
    }
    public void updateEntity(Fund entity, FundDTO entityDTO) {
        modelMapper.map(entityDTO, entity);
    }

}
