package com.rest.services.restwebservices.customer;

import java.util.List;

public class Customer {

	private String firstname;

	private String lastname;
	
	private List<Account> accounts; // list of object, how does it show up in swagger. 
	//see Customer_Swagger_Definition.PNG

	public Customer() {
		super();
	}

	public Customer(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}	

}
