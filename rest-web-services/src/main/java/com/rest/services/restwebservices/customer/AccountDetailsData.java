package com.rest.services.restwebservices.customer;

import java.util.ArrayList;
import java.util.List;

public class AccountDetailsData extends Data {

	private List<Payment> payments;

	@Override
	public List<Payment> getPayments() {
		if (this.payments == null) {
			this.payments = new ArrayList<>();
		}
		return null;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

}
