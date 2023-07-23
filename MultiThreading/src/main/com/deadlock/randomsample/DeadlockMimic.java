package com.deadlock.randomsample;

/*
   We simulate a scenario of datavase operation with records - 6 threads trying to access 3 records
 */
public class DeadlockMimic {

  private LockHelper lockHelper = new DatabaseLockHelper();
  private Database database = new Database();

  public static void main(String[] args) throws InterruptedException {
    DeadlockMimic deadlockMimic = new DeadlockMimic();

      Thread[] threads = new Thread[Constants.NUM_CONNECTIONS];

      for (int i = 0; i < Constants.NUM_CONNECTIONS; i++) {
        String transactionId = "Txn-" + (i + 1);
        threads[i] = new Thread(() -> {

          try {
            deadlockMimic.mimicLoad(transactionId);
          } catch (InterruptedException e) {
             e.printStackTrace();
          }

        });
        threads[i].start();

      }

      for(int i = 0 ; i < Constants.NUM_CONNECTIONS; i++){
         threads[i].join();
      }


  }

  private void mimicLoad(String transactionId) throws InterruptedException {

    while (true) {

      //lock 2 records randomly
      int record1 = (int) (Math.random()*1000 % Constants.NUM_RECORDS);
      int record2 = (int) (Math.random()*1000 % Constants.NUM_RECORDS);

      if (record1 == record2) {
        continue;
      }else if(record1 > record2){
          //TO FIX deadlock , we swap numbers if record2 is smaller->to ensure lock is taken in same order as numbers (small first)
          int temp = record1;
          record1 = record2;
          record2 = temp;
      }

      lockHelper.acquireLock(transactionId, record1);
      lockHelper.acquireLock(transactionId, record2);

      Thread.sleep(2000);

      lockHelper.releaseLock(transactionId, record2);
      lockHelper.releaseLock(transactionId, record1);

      Thread.sleep(1000);

    }
  }

}
