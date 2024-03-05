package com.producerconsumer.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;

/*
    This implementation of producer consumer problem doesn't use  synchronized or  wait or notify methods.
    It uses a blocking queue which takes care of synchronizing - the methods used are put and take which
    take care of synchronization(waiting if needed)
 */

public class Demo {

  public static void main(String [] args){

    int capacity = 5;
    ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(capacity);
    Thread producer1 = new Thread(new Producer(queue));
    Thread producer2 = new Thread(new Producer(queue));

    Thread consumer1 = new Thread(new Consumer(queue));
    Thread consumer2 = new Thread(new Consumer(queue));
    Thread consumer3 = new Thread(new Consumer(queue));
    Thread consumer4 = new Thread(new Consumer(queue));

    producer1.start();
    producer2.start();

    consumer1.start();
    consumer2.start();
    consumer3.start();
    consumer4.start();


  }
}
