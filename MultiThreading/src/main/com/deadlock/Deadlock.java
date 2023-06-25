package com.deadlock;

public class Deadlock {

  private Object objectA = new Object();
  private Object objectB = new Object();

  void a() {
    synchronized (objectA) {
      System.out.println("a : Print A");
      synchronized (objectB) {
        System.out.println("a : Print B");
      }
    }
  }

  void b() {
    synchronized (objectB) {
      System.out.println(" b : Print B");
      synchronized (objectA) {
        System.out.println("b : Print A");
      }
    }
  }


  public static void main(String[] args) {
    Deadlock a = new Deadlock();
    Thread t1 = new Thread(new Runnable() {
      public void run() {
        a.b();
      }
    }, "Thread A");
    Thread t2 = new Thread(new Runnable() {
      public void run() {
        a.a();
      }
    }, "Thread B");

    t1.start();
    t1.start();
    t2.start();


  }

}
