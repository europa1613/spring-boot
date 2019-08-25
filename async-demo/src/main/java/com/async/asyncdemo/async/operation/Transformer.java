package com.async.asyncdemo.async.operation;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.async.asyncdemo.domain.JokesDomainResponse;
import com.async.asyncdemo.model.JokesResponse;

@Component
public class Transformer implements Function<JokesDomainResponse, JokesResponse> {

	private static final Logger logger = LoggerFactory.getLogger(Transformer.class);

	@Override
	public JokesResponse apply(JokesDomainResponse t) {
		logger.info("Transformer");
		JokesResponse jokesResponse = JokesResponse.map(t);
		logEvent();
		return jokesResponse;
	}

	private void logEvent() {
		logger.info("Transform Successfull.");
	}

}
