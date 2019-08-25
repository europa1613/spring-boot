package com.async.asyncdemo.async.operation;

import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.async.asyncdemo.model.JokesResponse;

@Component
public class JokesReducer implements BiFunction<JokesResponse, JokesResponse, JokesResponse> {

	private static final Logger logger = LoggerFactory.getLogger(JokesReducer.class);

	@Override
	public JokesResponse apply(JokesResponse thousand, JokesResponse hundred) {
		logger.info("JokesReducer");
		JokesResponse response = new JokesResponse();
		response.setStatus(thousand.getStatus());
		response.getJokes()
				.addAll(thousand.getJokes());
		response.getJokes()
				.addAll(hundred.getJokes());
		logEvent();
		return response;
	}

	private void logEvent() {
		logger.info("Reduced data");
	}

}
