package com.producerconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

public class Consumer implements Runnable {

  private ArrayBlockingQueue<Integer> queue;


  public Consumer(ArrayBlockingQueue<Integer> queue) {
    this.queue = queue;
  }


  @Override
  public void run() {
    while (true) {
      try {
        int number = queue.take();
        System.out.println("Consumed " + number);

        Thread.sleep((long) (10000 * Math.random()));
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
}
