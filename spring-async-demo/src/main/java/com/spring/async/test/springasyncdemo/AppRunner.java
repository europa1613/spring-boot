package com.spring.async.test.springasyncdemo;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final GithubLookupService githubLookupService;

	public AppRunner(GithubLookupService githubLookupService) {
		this.githubLookupService = githubLookupService;
	}

	@Override
	public void run(String... args) throws Exception {

		// Start the clock
		long start = System.currentTimeMillis();

		// Kick off multiple asynchronous lookups
		CompletableFuture<User> pivotal = githubLookupService.findUser("PivotalSoftware");
		CompletableFuture<User> cf = githubLookupService.findUser("CloudFoundry");
		CompletableFuture<User> spring = githubLookupService.findUser("Spring-Projects");

		// Wait until they are all done
		CompletableFuture	.allOf(pivotal, cf, spring)
							.join();

		// Print results, including elapsed time
		logger.info("Elapsed time: " + (System.currentTimeMillis() - start));

		logger.info("--> " + pivotal.get());
		logger.info("--> " + cf.get());
		logger.info("--> " + spring.get());
	}

}
