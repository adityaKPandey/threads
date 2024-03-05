package com.producerconsumer.blockingqueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer implements Runnable {

  private ArrayBlockingQueue<Integer> queue;
  private Random random;

  public Producer(ArrayBlockingQueue<Integer> queue) {
    this.queue = queue;
    random = new Random();
  }


  @Override
  public void run() {
    while (true) {
      try {

        int number = random.nextInt();
        System.out.println("produced " + number);
        queue.put(number);

        Thread.sleep((long) (100 * Math.random()));

      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
}
