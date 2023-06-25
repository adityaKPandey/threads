package com.synchronizedcase.example;

public class Users {

  private static BankAccount bankAccount = new BankAccount(50);

  public static void main(String [] args){

    bankAccount.topUp(100);

    Thread txn1 = new Thread(() -> ATM.withdraw(bankAccount,100));
    Thread txn2 = new Thread(() -> ATM.withdraw(bankAccount,100));

    txn1.start();
    txn2.start();

  }

}
