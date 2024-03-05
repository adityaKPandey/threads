package com.producerconsumer;

import java.util.Queue;

public class Consumer implements Runnable {

  private Queue<Integer> queue;
  private int capacity;

  public Consumer(int capacity, Queue<Integer> queue) {
    this.queue = queue;
    this.capacity = capacity;
  }


  @Override
  public void run() {
    while (true) {
      try {
        synchronized (queue) {
          if (queue.size() == 0) {
            System.out.println("Queue is empty .. wait");
            queue.wait();
          } else {
            int number = queue.poll();
            System.out.println("Consumed " + number);
            queue.notify();
          }
        }

        Thread.sleep((long) (10000 * Math.random()));
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }
}
