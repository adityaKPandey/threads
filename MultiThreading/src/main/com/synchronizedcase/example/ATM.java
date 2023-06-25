package com.synchronizedcase.example;

public class ATM {

  static synchronized void withdraw(BankAccount bankAccount, int amount) {
    int balance = bankAccount.getBalance();
    int overDraft = bankAccount.getOverDraft();

    if (balance + overDraft < amount) {
      System.out.println("Transaction denied due to insufficient funds in account !!");
    } else {
      bankAccount.debit(amount);
      System.out.println("Amount debited : " + amount);
    }
    System.out.println("Existing balance : " + bankAccount.getBalance());
  }
}
