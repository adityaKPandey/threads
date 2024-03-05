package com.producerconsumer;

import java.util.LinkedList;
import java.util.Queue;

/*
    This implementation of producer consumer problem uses simple queue with synchronized , wait notify methods
 */
public class Demo {

  public static void main(String [] args){

    int capacity = 5;
    Queue<Integer> queue = new LinkedList<Integer>();
    Thread producer1 = new Thread(new Producer(capacity, queue));
    Thread producer2 = new Thread(new Producer(capacity, queue));

    Thread consumer1 = new Thread(new Consumer(capacity, queue));
    Thread consumer2 = new Thread(new Consumer(capacity, queue));
    Thread consumer3 = new Thread(new Consumer(capacity, queue));
    Thread consumer4 = new Thread(new Consumer(capacity, queue));

    producer1.start();
    producer2.start();

    consumer1.start();
    consumer2.start();
    consumer3.start();
    consumer4.start();


  }
}
