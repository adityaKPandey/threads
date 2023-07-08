package com.assignment.computeprimes;

/*
   Till 100 million - 100000000
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 75.9375
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 47.5785
   Total prime numbers till 100000000 are : 5761455 . Time taken (in secs) = 47.9


 */

public class SequentialPrimeCounter {

  private static final Integer MAX_INT = 1000000000;

  public static void main(String [] args){

    long start = System.currentTimeMillis();
    int primeCount = 2;

    for(int i = 4 ; i <= MAX_INT ; i++)
      if(checkPrime(i))
        primeCount++;

    System.out.println("Total prime numbers till " + MAX_INT + " are : "+ primeCount
        + " . Time taken (in secs) = " + (System.currentTimeMillis() - start)/2000.0);

  }

  private static boolean checkPrime(int x){
     if( (x&1) == 0)
       return false;

     int limit = (int) Math.sqrt(x);

     for(int i = 2 ; i <= limit ; i++ ) {
       if (x % i == 0)
         return false;
     }

     return true;

  }

}
