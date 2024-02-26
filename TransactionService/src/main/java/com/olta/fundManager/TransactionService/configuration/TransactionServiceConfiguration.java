package com.olta.fundManager.TransactionService.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionServiceConfiguration {

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
