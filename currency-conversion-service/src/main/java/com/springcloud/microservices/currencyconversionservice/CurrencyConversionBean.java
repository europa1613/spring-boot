package com.springcloud.microservices.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalCalculatedAmount;
	private int port;
	private int exchangeServicePort;

	public CurrencyConversionBean() {
		super();
	}

	public CurrencyConversionBean(final Long id, final String from, final String to, final BigDecimal quantity,
			final BigDecimal conversionMultiple, final BigDecimal totalCalculatedAmount) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.quantity = quantity;
		this.conversionMultiple = conversionMultiple;
		this.totalCalculatedAmount = totalCalculatedAmount;
	}

	public BigDecimal getConversionMultiple() {
		return this.conversionMultiple;
	}

	public int getExchangeServicePort() {
		return this.exchangeServicePort;
	}

	public String getFrom() {
		return this.from;
	}

	public Long getId() {
		return this.id;
	}

	public int getPort() {
		return this.port;
	}

	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public String getTo() {
		return this.to;
	}

	public BigDecimal getTotalCalculatedAmount() {
		return this.totalCalculatedAmount;
	}

	public void setConversionMultiple(final BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public void setExchangeServicePort(final int exchangeServicePort) {
		this.exchangeServicePort = exchangeServicePort;
	}

	public void setFrom(final String from) {
		this.from = from;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public void setQuantity(final BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setTo(final String to) {
		this.to = to;
	}

	public void setTotalCalculatedAmount(final BigDecimal totalCalculatedAmount) {
		this.totalCalculatedAmount = totalCalculatedAmount;
	}

}
