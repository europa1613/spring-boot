package com.springcloud.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {

	@Autowired
	private Environment env;

	@Autowired
	private FeignProxy feignProxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quatity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable final String from, @PathVariable final String to,
			@PathVariable final BigDecimal quantity) {

		final Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		final ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		final CurrencyConversionBean response = responseEntity.getBody();

		final BigDecimal value = response.getConversionMultiple().multiply(quantity);

		response.setQuantity(quantity);
		response.setTotalCalculatedAmount(value.multiply(quantity));
		response.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));

		return response;
	}

	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quatity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable final String from, @PathVariable final String to,
			@PathVariable final BigDecimal quantity) {

		final CurrencyConversionBean response = feignProxy.getExchangeValue(from, to);

		final BigDecimal value = response.getConversionMultiple().multiply(quantity);

		response.setQuantity(quantity);
		response.setTotalCalculatedAmount(value.multiply(quantity));
		response.setExchangeServicePort(response.getPort());		
		response.setPort(Integer.parseInt(this.env.getProperty("local.server.port")));

		return response;
	}
}
