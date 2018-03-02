package com.rest.services.restwebservices.customer;

import java.util.List;
import java.util.Map;

public abstract class Data {

	private Map<String, List<AccountsData>> accountsData;

	public Map<String, List<AccountsData>> getAccountsData() {
		return accountsData;
	}

	public void setAccountsData(Map<String, List<AccountsData>> accountsData) {
		this.accountsData = accountsData;
	}
	
	public abstract List<Payment> getPayments();

}
