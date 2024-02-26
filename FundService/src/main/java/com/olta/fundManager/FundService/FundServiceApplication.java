package com.olta.fundManager.FundService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.olta.fundManager.*")
public class FundServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundServiceApplication.class, args);
	}

}
