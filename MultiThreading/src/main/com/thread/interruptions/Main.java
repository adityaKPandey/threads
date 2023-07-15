package com.thread.interruptions;

public class Main {

  public static void main(String [] args) throws InterruptedException {
       Thread taskThread = new Thread(new RunnableTask());
       taskThread.start();
       Thread.currentThread().sleep(3000);
       taskThread.interrupt();

       System.out.println("Task thread interrupted flag status:" + taskThread.isInterrupted());

  }

}
