package com.threadlocal.usecase;

public class UserContextHolder {
  public static ThreadLocal<User> holder = new ThreadLocal<>();
}
