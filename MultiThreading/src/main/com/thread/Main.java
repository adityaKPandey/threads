package com.thread;

public class Main {

  public static void main(String [] args){
    System.out.println("Current thread : " + Thread.currentThread().getName());
    System.out.println("No of active threads : " + Thread.activeCount());


  }
}
