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

		List<Customer> customers = Arrays.asList(new Customer("John", "Doe"), new Customer("Jane", "Doe"));

		for (Customer customer : customers) {
			Account checking = new Account("1234567890", "Checking");
			Account savings = new Account("0987654321", "Savings");
			customer.setAccounts(Arrays.asList(checking, savings));
		}

		return customers;
	}
}
