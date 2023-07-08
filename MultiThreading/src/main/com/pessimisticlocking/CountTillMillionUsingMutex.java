package com.pessimisticlocking;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    Results without mutex locking:
    Count = 997995 , time taken = 1.864 secs
    Count = 997682 , time taken = 1.596 secs
    Count = 997587 , time taken = 1.683 secs
    Count = 997631 , time taken = 1.659 secs


    With mutex:
    Count = 1000000 , time taken = 119.105 secs
    Count = 1000000 , time taken = 47.175 secs
    Count = 1000000 , time taken = 46.251 secs

 */
public class CountTillMillionUsingMutex {

  private static int value = 0;
  private static Mutex mutex = new Mutex();

  private static final ExecutorService threadPool = Executors.newCachedThreadPool();

  public static void main(String [] args) throws InterruptedException {
     long start = System.currentTimeMillis();
     doCount();

     System.out.println("Count = "+ value + " , time taken = "+ (System.currentTimeMillis() - start)/1000.0 + " secs");
  }

  private static void doCount() throws InterruptedException {
    for(int i = 1 ; i <= 1000000 ; i++) {
      Thread thread = new Thread(() -> {

        try {
          incrementValue();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      });

      threadPool.submit(thread);

      //thread.start();
      /*
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

       */
    }

    threadPool.shutdown();
    threadPool.awaitTermination(120 , TimeUnit.HOURS);

  }

  private static void incrementValue() throws InterruptedException {
    mutex.acquire();
    value++;
    mutex.release();
  }


}
