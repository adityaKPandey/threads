package com.thread.interruptions;

public class RunnableTask implements Runnable{

  @Override
  public void run() {
    for(int i = 1 ; i <= 100; i++){
      System.out.println(i);
      try {
        Thread.sleep(100000);
      } catch (InterruptedException e) {
        //Thread.currentThread().isInterrupted() doesn't clear the interrupt flag
        System.out.println("Thread " + Thread.currentThread().getName() + " is interrupted : " + Thread.currentThread().isInterrupted());
        //System.out.println("Thread " + Thread.currentThread().getName() + " is interrupted ");
        //e.printStackTrace();
        break;
      }
    }
  }

}
