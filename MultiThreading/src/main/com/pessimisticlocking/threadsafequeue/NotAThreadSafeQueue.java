package com.pessimisticlocking.threadsafequeue;

import java.util.ArrayDeque;
import java.util.Queue;

public class NotAThreadSafeQueue<T> implements CustomQueue<T>{

  private Queue <T> queue = new ArrayDeque<>();

  @Override
  public void enqueue(T item) {
     queue.offer(item);
  }

  @Override
  public T dequeue() {
    if(queue.size() == 0)
      throw new IllegalStateException("Queue is empty .. can't deque");

    return queue.poll();
  }

  @Override
  public int size() {
    return queue.size();
  }

}
