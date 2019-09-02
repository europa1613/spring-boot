package com.spring.async.test.springasyncdemo;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GithubLookupService {

	private static final Logger logger = LoggerFactory.getLogger(GithubLookupService.class);

	private final RestTemplate restTemplate;

	public GithubLookupService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException {
		logger.info("Looking up: " + user);
		String url = String.format("https://api.github.com/users/%s", user);
		User result = restTemplate.getForObject(url, User.class);
		// artificial delay of 2s for demonstration purposes
		Thread.sleep(2_000L);
		return CompletableFuture.completedFuture(result);
	}

}
