package com.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.blockhound.BlockingOperationError;
import reactor.core.scheduler.Schedulers;

public class BlockHoundTest {

  @BeforeAll
  public static void beforeAll() {
    BlockHound.install();
  }

  @Test
  public void blockHoundWorks() {
    try {
      FutureTask<?> task = new FutureTask<>(() -> {
        Thread.sleep(0);
        return "";
      });
      Schedulers.parallel().schedule(task);

      task.get(10, TimeUnit.SECONDS);
      Assertions.fail("should fail");
    } catch (ExecutionException e) {
      Assertions.assertTrue(e.getCause() instanceof BlockingOperationError, "detected");
    } catch (InterruptedException | TimeoutException e) {
      e.printStackTrace();
    }
  }

}
