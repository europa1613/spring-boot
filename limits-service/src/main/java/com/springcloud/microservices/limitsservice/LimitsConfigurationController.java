package com.springcloud.microservices.limitsservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsConfigurationController {

	@GetMapping("/limits")
	public LimitConfiguration getLimitsFromConfigurations() {
		return new LimitConfiguration(1000, 1);
	}
}
