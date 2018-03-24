package com.springcloud.microservices.limitsservice.bean;

public class LimitConfiguration {

	private int maximun;

	private int minimum;

	public LimitConfiguration() {
		super();
	}

	public LimitConfiguration(int maximun, int minimum) {
		super();
		this.maximun = maximun;
		this.minimum = minimum;
	}

	public int getMaximun() {
		return maximun;
	}

	public int getMinimum() {
		return minimum;
	}

}
