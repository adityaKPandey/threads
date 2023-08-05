package com.threadlocal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ThreadSafeUserService {

  private ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws InterruptedException {

    ThreadSafeUserService threadSafeUserService = new ThreadSafeUserService();

    for (int i = 1; i <= 1000; i++) {
      final int id = i;
      threadSafeUserService.threadPool.submit(() -> {
        String birthDate = threadSafeUserService.birthDate(id);
        //System.out.println("id : " + id + " , birth date:" + birthDate);
      });

    }

    threadSafeUserService.threadPool.shutdown();
    threadSafeUserService.threadPool.awaitTermination(1, TimeUnit.NANOSECONDS);
    Thread.sleep(1000);
  }


  public String birthDate(int userId) {
    int hundredYears = userId * 365;
    LocalDate localDate = LocalDate.ofEpochDay(ThreadLocalRandom
        .current().nextInt(-hundredYears, hundredYears));

    SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatter.get();//each thread will get its own copy
    //System.out.println("simpledateformat object created per thread : " + simpleDateFormat.hashCode());
    //System.out.println("New simpledateformat object created : " + simpleDateFormat);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return simpleDateFormat.format(date);
  }
}
