package com.threadlocal;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/*
  Problem with this code is we are creating a SimpleDateFormat object for every task(1000 times) which is not memory efficient.
  Using global object of static SimpleDateFormat object will create problem of thread-safety.
  Using synchronization to solve for this thread-safety issue will slow down the performance.

  What we want is a middle ground- each thread(10) having its own date format object(memory efficiency and thread-safety)

 */
public class UserService {

  private ExecutorService threadPool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws InterruptedException {

    UserService userService = new UserService();

    for (int i = 1; i <= 1000; i++) {
      final int id = i;
      userService.threadPool.submit(() -> {
        String birthDate = userService.birthDate(id);
        System.out.println("id : " + id + " , birth date:" + birthDate);
      });

    }

    userService.threadPool.shutdown();
    userService.threadPool.awaitTermination(1, TimeUnit.NANOSECONDS);
    Thread.sleep(1000);
  }

  public String birthDate(int userId) {
    int hundredYears = userId * 365;
    LocalDate localDate = LocalDate.ofEpochDay(ThreadLocalRandom
        .current().nextInt(-hundredYears, hundredYears));

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //System.out.println("New simpledateformat object created : " + simpleDateFormat);
    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return simpleDateFormat.format(date);
  }
}
