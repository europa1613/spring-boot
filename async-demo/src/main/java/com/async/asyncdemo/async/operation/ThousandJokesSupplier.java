package com.async.asyncdemo.async.operation;

import java.util.Collections;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.async.asyncdemo.domain.JokesDomainResponse;
import com.async.asyncdemo.restclient.RestClient;

@Component
public class ThousandJokesSupplier implements Supplier<JokesDomainResponse> {

	private static final Logger logger = LoggerFactory.getLogger(ThousandJokesSupplier.class);

	final static String URL = "http://api.icndb.com/jokes/random/1000";

	@Autowired
	private RestClient<String, JokesDomainResponse> client;

	@Override
	public JokesDomainResponse get() {
		logger.info("ThousandJokesSupplier");
		JokesDomainResponse response;
		try {
			response = client.execute(URL, HttpMethod.GET, "", JokesDomainResponse.class);
			logEvent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new JokesDomainResponse();
			response.setType("failed");
			response.setValue(Collections.emptyList());
		}
		return response;
	}

	private void logEvent() {
		logger.info("Supplied 1000 Jokes");
	}

}
