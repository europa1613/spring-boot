package com.async.asyncdemo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.async.asyncdemo.restclient.RestClient;

@Service
public class ThousandJokesService {

	final static String URL = "http://api.icndb.com/jokes/random/1000";

	@Autowired
	private RestClient<String, JokesDomainResponse> client;

	public JokesDomainResponse getThousandJokes() throws ResourceAccessException, Exception {
		JokesDomainResponse response = client.execute(URL, HttpMethod.GET, "", JokesDomainResponse.class);
		return response;
	}

}
