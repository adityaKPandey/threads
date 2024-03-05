package com.producerconsumer;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {

  private Queue<Integer> queue;
  private int capacity;
  private Random random;

  public Producer(int capacity, Queue<Integer> queue) {
    this.capacity = capacity;
    this.queue = queue;
    random = new Random();
  }


  @Override
  public void run() {
    while (true) {
      try {
        synchronized (queue) {

          if (queue.size() == capacity) {
            System.out.println("Queue is full .. wait");
            queue.wait();
          } else {
            int number = random.nextInt();
            boolean added = queue.offer(number);
            if (added) {
              System.out.println("produced " + number);
            } else {
              System.out.println("couldn't add " + number);
            }

            queue.notify();
          }
        }
        Thread.sleep((long) (100 * Math.random()));

      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
}
