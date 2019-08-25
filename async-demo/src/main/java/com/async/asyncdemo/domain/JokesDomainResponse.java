package com.async.asyncdemo.domain;

import java.util.List;

public class JokesDomainResponse {

	private String type;

	private List<JokeDomain> value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<JokeDomain> getValue() {
		return value;
	}

	public void setValue(List<JokeDomain> value) {
		this.value = value;
	}

}
