package com.pessimisticlocking.threadsafequeue;

public interface CustomQueue<T> {

   void enqueue(T item);
   T dequeue() throws InterruptedException;
   int size() throws InterruptedException;

}
