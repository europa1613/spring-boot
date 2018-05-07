package com.springcloud.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment env;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quatity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		BigDecimal multiple = BigDecimal.valueOf(65);
		BigDecimal value = multiple.multiply(quantity);

		CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(1001L, from, to, quantity, multiple,
				value);
		currencyConversionBean.setPort(Integer.parseInt(env.getProperty("local.server.port")));

		return currencyConversionBean;
	}
}
