package com.pessimisticlocking.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Application {

  public static void main(String [] args) throws InterruptedException {
    Semaphore semaphore = new Semaphore(3,true);
    ExecutorService executorService = Executors.newFixedThreadPool(50);
    IntStream.of(new int[1000]).forEach(i -> executorService.execute(new Task(semaphore)));

    executorService.shutdown();
    executorService.awaitTermination(1, TimeUnit.MINUTES);
  }

}
