package com.thread.interruptions.staticmethod;

public class Main {

  public static void main(String [] args) throws InterruptedException {
       Thread taskThread = new Thread(new RunnableTask());
       taskThread.start();

       taskThread.interrupt();
       Thread.currentThread().sleep(3000);
       System.out.println("Task thread interrupted flag status:" + taskThread.isInterrupted());

  }

}
