package com.olta.fundManager.MemberService.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class MemberServiceConfiguration {

    @Autowired
    ApplicationProperties applicationProperties;
    @Bean
    public RestClient getRestClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(applicationProperties.getTransactionService().getBaseURL())
                .build();
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(RestClient.class);
    }
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
