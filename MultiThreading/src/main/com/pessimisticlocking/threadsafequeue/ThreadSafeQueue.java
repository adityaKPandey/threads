package com.pessimisticlocking.threadsafequeue;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.util.ArrayDeque;
import java.util.Queue;

public class ThreadSafeQueue<T> implements CustomQueue<T> {

  private Queue<T> queue = new ArrayDeque<>();
  private Mutex mutex = new Mutex();

  @Override
  public void enqueue(T item) {
    try {
      mutex.acquire();
      queue.offer(item);
      mutex.release();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public T dequeue() throws InterruptedException {
    try {
      mutex.acquire();
      if (queue.size() == 0) {
        throw new IllegalStateException("Queue is empty .. can't deque");
      }

      return queue.poll();
    }finally {
      mutex.release();
    }
  }

  @Override
  public int size() throws InterruptedException {
    mutex.acquire();
    int size = queue.size();
    mutex.release();
    return size;
  }

}
