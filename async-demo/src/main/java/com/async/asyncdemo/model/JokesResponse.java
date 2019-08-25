package com.async.asyncdemo.model;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.async.asyncdemo.domain.JokesDomainResponse;

public class JokesResponse {

	private Duration took;

	private String status;

	private List<Joke> jokes;

	public Duration getTook() {
		return took;
	}

	public void setTook(Duration took) {
		this.took = took;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Joke> getJokes() {
		if (this.jokes == null) {
			this.jokes = new ArrayList<>();
		}
		return jokes;
	}

	public void setJokes(List<Joke> jokes) {
		this.jokes = jokes;
	}

	public static JokesResponse map(JokesDomainResponse domain) {
		JokesResponse response = new JokesResponse();
		response.setStatus(domain.getType());
		List<Joke> jokes = domain	.getValue()
									.stream()
									.map(Joke::map)
									.collect(Collectors.toList());
		response.setJokes(jokes);
		return response;
	}

}
