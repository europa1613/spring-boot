package com.springcloud.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	Environment env;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {

		ExchangeValue exchangeValue = new ExchangeValue(1L, from, to, BigDecimal.valueOf(65));
		exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));

		return exchangeValue;
	}

}
