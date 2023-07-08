package com.assignment.computeprimes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/*
   Till 100 million - 100000000 , 10 concurrency factor
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 24.665
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 24.596
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 24.049

   Till 100 million - 100000000 , 4 concurrency factor ( as 4 cores)
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 37.877

 */
public class UnfairParallelPrimeCounter {

  private static final Integer MAX_INT = 100000000;
  private static final int CONCURRENCY_FACTOR = 10;

  private static final ExecutorService threadPool = Executors.newFixedThreadPool(CONCURRENCY_FACTOR);

  public static void main(String [] args) throws InterruptedException {

    long start = System.currentTimeMillis();
    AtomicInteger primeCounter = new AtomicInteger(2);

    batchCountPrime(primeCounter);

    System.out.println("Total prime numbers till " + MAX_INT + " are : "+ primeCounter.get()
        + " . Time taken (in secs) = " + (System.currentTimeMillis() - start)/1000.0);

  }

  private static void batchCountPrime(AtomicInteger primeCounter ) throws InterruptedException {
    int batchSize = MAX_INT/CONCURRENCY_FACTOR;

    int startNum = 4 , endNum = 4+batchSize ;
    for(int i = 1 ; i <= CONCURRENCY_FACTOR ; i++) {
      int finalStartNum = startNum;
      int finalEndNum = endNum;
      int batchId = i;
      threadPool.submit(() -> executeThisBatch(batchId, finalStartNum, finalEndNum, primeCounter));
      startNum = endNum + 1;
      int end = startNum + batchSize;
      endNum =  end > MAX_INT ? MAX_INT : end;
    }

    threadPool.shutdown();
    threadPool.awaitTermination(2 , TimeUnit.MINUTES);
  }

  private static void executeThisBatch(Integer batchId , Integer start , Integer end , AtomicInteger primeCount){
    long startTime = System.currentTimeMillis();

      for(int i = start ; i <= end; i++)
           checkPrime(i,primeCount);

      System.out.println("Time taken for batch "+ batchId  + " of numbers from " + start + " till " + end + " is (in secs):"+(System.currentTimeMillis()- startTime)/1000.0);
  }

  private static void checkPrime(int x , AtomicInteger primeCounter){
     if( (x&1) == 0)
       return ;

     int limit = (int) Math.sqrt(x);

     for(int i = 2 ; i <= limit ; i++ ) {
       if (x % i == 0)
         return ;
     }

     primeCounter.getAndAdd(1);

  }

}
