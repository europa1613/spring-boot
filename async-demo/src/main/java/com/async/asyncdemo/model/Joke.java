package com.async.asyncdemo.model;

import com.async.asyncdemo.domain.JokeDomain;

public class Joke {

	private long id;

	private String joke;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJoke() {
		return joke;
	}

	public void setJoke(String joke) {
		this.joke = joke;
	}

	public static Joke map(JokeDomain domain) {
		Joke joke = new Joke();
		joke.setId(domain.getId());
		joke.setJoke(domain.getJoke());
		return joke;
	}

}
