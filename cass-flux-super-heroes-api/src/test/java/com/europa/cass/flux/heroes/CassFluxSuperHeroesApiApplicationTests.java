package com.europa.cass.flux.heroes;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

@SpringBootTest
class CassFluxSuperHeroesApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test_Logger() {
		Mono.just("Started loading data for process: ")
				.doOnEach(new Consumer<Signal<String>>() {
					@Override
					public void accept(Signal<String> stringSignal) {

					}
				});

	}

}
