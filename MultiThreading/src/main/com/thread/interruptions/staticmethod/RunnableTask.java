package com.thread.interruptions.staticmethod;

public class RunnableTask implements Runnable{

  @Override
  public void run() {
    for(int i = 1 ; i <= 100; i++){
      System.out.println(i);

      //static interrupted method clears the interrupt flag and returns its original value
      if(Thread.interrupted())
        break;
    }
  }

}
