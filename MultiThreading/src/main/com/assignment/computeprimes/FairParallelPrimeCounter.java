package com.assignment.computeprimes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
   Till 100 million - 100000000 , 10 concurrency factor
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 26.594
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 23.123



   Till 100 million - 100000000 , 8 concurrency factor ( as 4 cores)
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 28.163

 */
public class FairParallelPrimeCounter {

  private static final Integer MAX_INT = 100000000;
  private static final int CONCURRENCY_FACTOR = 10;

  private static final ExecutorService threadPool = Executors.newFixedThreadPool(CONCURRENCY_FACTOR);

  private static AtomicInteger currentNumber = new AtomicInteger(4);

  public static void main(String [] args) throws InterruptedException {

    long start = System.currentTimeMillis();
    AtomicInteger primeCounter = new AtomicInteger(2);

    batchCountPrime(primeCounter);

    System.out.println("Total prime numbers till " + MAX_INT + " are : "+ primeCounter.get()
        + " . Time taken (in secs) = " + (System.currentTimeMillis() - start)/1000.0);

  }

  private static void batchCountPrime(AtomicInteger primeCounter ) throws InterruptedException {

    for(int i = 1 ; i <= CONCURRENCY_FACTOR ; i++) {
      threadPool.submit(() -> checkPrime(primeCounter));
    }

    threadPool.shutdown();
    threadPool.awaitTermination(2 , TimeUnit.MINUTES);
  }

  private static void checkPrime(AtomicInteger primeCounter){
     long timeStarted = System.currentTimeMillis();

     while (currentNumber.get() < MAX_INT) {
       int x = currentNumber.getAndAdd(1);
       if ((x & 1) == 0)
         continue;

       int limit = (int) Math.sqrt(x);

       boolean prime = true;
       for (int i = 2; i <= limit; i++) {
         if (x % i == 0) {
           prime = false;
           break;
         }
       }

       if(prime)
        primeCounter.getAndAdd(1);
     }

    System.out.println("Time taken for thread "+ Thread.currentThread().getName()  +  " is (in secs):"+(System.currentTimeMillis()- timeStarted)/1000.0);
  }

}
