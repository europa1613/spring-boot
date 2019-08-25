package com.async.asyncdemo.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.async.asyncdemo.async.operation.HundredJokesSupplier;
import com.async.asyncdemo.async.operation.ThousandJokesSupplier;
import com.async.asyncdemo.async.operation.Transformer;
import com.async.asyncdemo.async.operation.JokesReducer;
import com.async.asyncdemo.converter.JokesDomainToModelConverter;
import com.async.asyncdemo.domain.HundredJokesService;
import com.async.asyncdemo.domain.JokesDomainResponse;
import com.async.asyncdemo.domain.ThousandJokesService;
import com.async.asyncdemo.model.JokesResponse;

@Service
public class JokesService {

	private static final Logger logger = LoggerFactory.getLogger(JokesService.class);

	@Autowired
	private HundredJokesService service100;

	@Autowired
	private ThousandJokesService service1000;

	@Autowired
	private JokesDomainToModelConverter converter;

	@Autowired
	private HundredJokesSupplier async100;

	@Autowired
	private ThousandJokesSupplier async1000;
	
	@Autowired
	private Transformer transformer;

	@Autowired
	private JokesReducer reducer;

	public JokesResponse getDataSync() throws ResourceAccessException, Exception {
		JokesDomainResponse hundredJokes = service100.getHundredJokes();
		JokesDomainResponse thousandJokes = service1000.getThousandJokes();

		/*logger.info("hundredJokes: " + hundredJokes	.getValue()
													.size());
		logger.info("thousandJokes: " + thousandJokes	.getValue()
														.size());*/

		JokesResponse jokesResponse100 = converter.convert(hundredJokes);
		JokesResponse jokesResponse1000 = converter.convert(thousandJokes);

		JokesResponse response = new JokesResponse();
		response.setStatus(jokesResponse100.getStatus());
		response.getJokes()
				.addAll(jokesResponse1000.getJokes());
		response.getJokes()
				.addAll(jokesResponse100.getJokes());

		return response;
	}

	public JokesResponse getDataAsync() throws InterruptedException, ExecutionException, TimeoutException {

		CompletableFuture<JokesResponse> hundred = CompletableFuture.supplyAsync(async100::get)
																	.thenApply(transformer::apply);
		
		CompletableFuture<JokesResponse> thousand = CompletableFuture	.supplyAsync(async1000::get)
																		.thenApply(transformer::apply);

		CompletableFuture<JokesResponse> result = thousand.thenCombine(hundred, reducer::apply);

		return result.get(60000, TimeUnit.MILLISECONDS);
	}

}
