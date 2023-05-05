package com.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@SpringBootApplication
public class ReactiveParallelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveParallelDemoApplication.class, args);
    }

    @Bean
    public Scheduler esScheduler() {
        return Schedulers.newBoundedElastic(10, 10000, "my-elastic");
    }

}


@RestController
@RequiredArgsConstructor
class AdvisorController {

    private final Scheduler esScheduler;

    private final AdvisorrRepository repository;

    @GetMapping("/advisors")
    public Flux<Advisorr> advisors() {
        return Flux.just(1, 2, 3)
                   .publishOn(esScheduler)
                   .flatMap(repository::construct)
                   .publishOn(esScheduler)
                   .flatMap(repository::getFirstName)
                   .publishOn(esScheduler)
                   .flatMap(repository::getLastName)
                   .publishOn(esScheduler)
                   .flatMap(repository::getDateOfBirth)
                   .publishOn(esScheduler)
                   .flatMap(repository::log);
        //.subscribeOn(Schedulers.parallel());
    }

}


@Slf4j
@Repository
class AdvisorrRepository {
    private static final ConcurrentMap<String, String> FIRST = new ConcurrentHashMap<>() {{
        put("1", "John");
        put("2", "Jane");
        put("3", "Mary");
    }};
    private static final ConcurrentMap<String, String> LAST = new ConcurrentHashMap<>() {{
        put("1", "Doe");
        put("2", "Smith");
        put("3", "Louise");
    }};
    private static final ConcurrentMap<String, String> DOB = new ConcurrentHashMap<>() {{
        put("1", "1970-05-12");
        put("2", "1975-10-13");
        put("3", "1980-12-14");
    }};

    public Flux<Advisorr> construct(final Integer id) {
        Advisorr advisorr = new Advisorr();
        log.info("Thread: {}; construct(): advisorr {}", Thread.currentThread()
                                                                  .getName(), advisorr);
        advisorr.setId(String.valueOf(id));
        return Flux.just(advisorr);
    }

    public Flux<Advisorr> getFirstName(final Advisorr advisorr) {
        final String id = advisorr.getId();
        log.info("Thread: {}; getFirstName(): advisorr {}", Thread.currentThread()
                                                  .getName(), advisorr);
        String first = FIRST.get(id);
        advisorr.setFirstname(first);

        return Flux.just(advisorr);
    }

    public Flux<Advisorr> getLastName(final Advisorr advisorr) {
        final String id = advisorr.getId();
        log.info("Thread: {}; getLastName(): advisorr {}", Thread.currentThread()
                                                  .getName(), advisorr);
        String last = LAST.get(id);
        advisorr.setLastname(last);

        return Flux.just(advisorr);
    }

    public Flux<Advisorr> getDateOfBirth(final Advisorr advisorr) {
        final String id = advisorr.getId();
        log.info("Thread: {}; getDateOfBirth(): advisorr {}", Thread.currentThread()
                                                  .getName(), advisorr);
        String dob = DOB.get(id);
        advisorr.setDateOfBirth(dob);

        return Flux.just(advisorr);
    }

    public Flux<Advisorr> log(final Advisorr advisorr) {
        log.info("Thread: {}; log(): advisorr {}", Thread.currentThread()
                                                  .getName(), advisorr);
        return Flux.just(advisorr);
    }
}

