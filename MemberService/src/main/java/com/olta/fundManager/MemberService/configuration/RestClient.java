package com.olta.fundManager.MemberService.configuration;

import com.olta.fundManager.MemberService.model.TransactionDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface RestClient {
    @PostExchange("/transactions")
    void createTransactions(@RequestBody List<TransactionDTO> transactionDTO);
}
