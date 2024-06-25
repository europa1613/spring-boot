package com.example;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "1m")
public class ShedlockDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShedlockDemoApplication.class, args);
	}

}
