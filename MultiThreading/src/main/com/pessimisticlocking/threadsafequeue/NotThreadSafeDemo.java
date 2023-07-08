package com.pessimisticlocking.threadsafequeue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class NotThreadSafeDemo {

  private static ExecutorService threadPool = Executors.newCachedThreadPool();

  public static void main(String [] args) throws InterruptedException {

     CustomQueue<Integer> queue = new NotAThreadSafeQueue<>();

     for(int i = 0 ; i < 1000000; i++) {
       int finalI = i;
       threadPool.submit( () -> queue.enqueue(finalI + 1));
     }

     threadPool.shutdown();
     threadPool.awaitTermination(5, TimeUnit.MINUTES);

     System.out.println("Queue size : "+ queue.size());



  }

}
