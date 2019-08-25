package com.async.asyncdemo.controller;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.async.asyncdemo.model.JokesResponse;
import com.async.asyncdemo.service.JokesService;

@RestController
@RequestMapping("/jokes")
public class JokesController {

	private static final Logger logger = LoggerFactory.getLogger(JokesController.class);

	@Autowired
	private JokesService service;

	@GetMapping("/sync")
	public JokesResponse getDataSync() throws ResourceAccessException, Exception {
		long start = System.currentTimeMillis();
		JokesResponse jokesResponse = this.service.getDataSync();
		long end = System.currentTimeMillis();
		long took = end - start;
		logger.info("getDataSync:took:" + took);
		jokesResponse.setTook(Duration.of(took, ChronoUnit.MILLIS));
		return jokesResponse;
	}

	@GetMapping("/async")
	public JokesResponse getDataAsync() throws ResourceAccessException, Exception {
		long start = System.currentTimeMillis();
		JokesResponse jokesResponse = this.service.getDataAsync();
		long end = System.currentTimeMillis();
		long took = end - start;
		logger.info("getDataAsync: took:" + took);
		jokesResponse.setTook(Duration.of(took, ChronoUnit.MILLIS));
		return jokesResponse;
	}

}
