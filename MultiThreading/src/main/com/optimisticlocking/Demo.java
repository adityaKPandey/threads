package com.optimisticlocking;

import java.util.concurrent.atomic.AtomicInteger;

public class Demo {

   public static void main(String [] args){
      AtomicInteger atomicInteger = new AtomicInteger(0);

      Thread thread1 = new Thread(new AtomicIncrementTask(atomicInteger));
      Thread thread2 = new Thread(new AtomicIncrementTask(atomicInteger));

      thread1.start();
      thread2.start();
   }

}
