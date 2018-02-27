package com.rest.services.restwebservices.customer;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@ResponseBody
	@RequestMapping("/all")
	public List<Customer> getAllCustomers() {
		return Arrays.asList(new Customer("John", "Doe"), new Customer("Jane", "Doe"));
	}
}
