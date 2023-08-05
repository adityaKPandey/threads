package com.threadlocal.usecase;

/*
   Youtube video link : https://www.youtube.com/watch?v=sjMe9aecW_A

   Let's say we have web server , where to service every request , a thread is assigned to process that request.
   And that request goes through 4 services to complete it. And all services need to know which user has made the request.

   Using map of users per thread requires us to handle synchronization which will impact performance.

   So in such cases , using threadlocal holder can be used to store user per thread. All services can use this
   context holder to get user whose request is handled by current thread.

 */
public class ServiceMain {

  public static void main(String[] args) {
    Service1 service1 = new Service1();
    Service2 service2 = new Service2();
    Service3 service3 = new Service3();

    service1.process();
    service2.process();
    service3.process();
  }

}

class Service1 {

  public void process() {
    User user = getUser();
    UserContextHolder.holder.set(user);
    System.out.println("Service1 user: " + user);
  }

  private User getUser() {
    return new User();
  }

}

class Service2 {

  public void process() {
    User user = UserContextHolder.holder.get();
    System.out.println("Service2 user: " + user);
  }
}


class Service3 {

  public void process() {
    User user = UserContextHolder.holder.get();
    System.out.println("Service3 user: " + user);

    //PS: Very important to clear the threadlocal context once all services have processed the request
    UserContextHolder.holder.remove();
    System.out.println("user cleared from context");
  }
}