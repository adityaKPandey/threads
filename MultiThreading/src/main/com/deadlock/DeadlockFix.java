package com.deadlock;

public class DeadlockFix {

  private Object objectA = new Object();
  private Object objectB = new Object();

  //Fix deadlock :  accessing both lock objects in same order
  void a() {
    synchronized (objectA) {
      System.out.println("a : Print A");
      synchronized (objectB) {
        System.out.println("a : Print B");
      }
    }
  }

  void b() {
    synchronized (objectA) {
      System.out.println(" b : Print B");
      synchronized (objectB) {
        System.out.println("b : Print A");
      }
    }
  }


  public static void main(String[] args) {

    DeadlockFix a = new DeadlockFix();

    Thread t1 = new Thread(() -> a.b(), "Thread A");
    Thread t2 = new Thread( () -> a.a(), "Thread B");

    t1.start();
    t2.start();

  }

}
