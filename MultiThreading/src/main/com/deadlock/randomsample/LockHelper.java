package com.deadlock.randomsample;

public interface LockHelper {
  void acquireLock(String transactionId , Integer recordId) throws InterruptedException;
  void releaseLock(String transactionId , Integer recordId);
}
