package com.deadlock.randomsample;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

public class DatabaseLockHelper implements LockHelper {

  public Database db = new Database();

  @Override
  public void acquireLock(String transactionId , Integer recordId) throws InterruptedException {
     System.out.printf("txn %s wants to acquire lock on record %s \n",transactionId,recordId);
     db.records[recordId].mutex.acquire();
     System.out.printf("txn %s acquired lock on record %s \n",transactionId,recordId);
  }

  @Override
  public void releaseLock(String transactionId , Integer recordId){
     db.records[recordId].mutex.release();
     System.out.printf("txn %s released lock on record %s \n",transactionId,recordId);
  }

}

