package com.example;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestTask {

    @Scheduled(cron = "0/15 * * * * ?")
    @SchedulerLock(name = "TestTask_task", lockAtMostFor = "15s", lockAtLeastFor = "5s")
    public void task() {
        log.info("Running...");
    }
}
