package com.threadlocal;

import java.text.SimpleDateFormat;

public class ThreadSafeFormatter {

  public static ThreadLocal<SimpleDateFormat> dateFormatter = new ThreadLocal<SimpleDateFormat>(){

    //called once for each thread to initialize if it doesn't exist
    protected SimpleDateFormat initialValue(){
       System.out.println("Initial value called by thread: "+ Thread.currentThread().getName());
       return new SimpleDateFormat("yyyy-MM-dd");
    }

    //once initialized , subsequent calls will return same initialized value from thread local map
    public SimpleDateFormat get(){
        System.out.println("get called by thread: "+ Thread.currentThread().getName());
        return super.get();
    }
  };
}
