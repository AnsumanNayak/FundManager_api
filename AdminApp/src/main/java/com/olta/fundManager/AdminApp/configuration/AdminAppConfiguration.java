package com.olta.fundManager.AdminApp.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AdminAppConfiguration {

    @Bean
//    @Scope("prototype")
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
