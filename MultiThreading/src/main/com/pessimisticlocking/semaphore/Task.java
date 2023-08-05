package com.pessimisticlocking.semaphore;

import java.util.concurrent.Semaphore;

public class Task implements Runnable{

  private Semaphore semaphore;

  public Task(Semaphore semaphore){
    this.semaphore = semaphore;
  }

  @Override
  public void run() {
    try {
      System.out.println(Thread.currentThread().getName() + " is trying to get permit");
      semaphore.acquire();
      System.out.println(Thread.currentThread().getName()+ " got permit... executing");
      Thread.sleep(1000);
      semaphore.release();
      System.out.println(Thread.currentThread().getName() + " gave up permit.. Bye Bye !!");
    }catch (InterruptedException exception){
      exception.printStackTrace();
    }
  }
}
