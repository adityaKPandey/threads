package com.optimisticlocking;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrementTask implements Runnable{

  private AtomicInteger value;

  public AtomicIncrementTask(AtomicInteger value){
    this.value = value;
  }


  @Override
  public void run() {
    int oldValue = value.get();
    int newValue = oldValue + 1;

    if( !value.compareAndSet(oldValue,newValue))
      System.out.println( "Thread "+ Thread.currentThread().getName() + "failed to set value to " + newValue + " as old value " + oldValue + " did not match");
    else
      System.out.println("Thread " + Thread.currentThread().getName() + " set value to " + newValue + " from old value " + oldValue);

  }
}
