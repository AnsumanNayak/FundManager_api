package com.olta.fundManager.MemberService.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "olta")
public class ApplicationProperties {
    private final TransactionService transactionService = new TransactionService();

    @Data
    public static class TransactionService{
        private final String baseURL="http://localhost:8084";
    }

}
