package com.synchronizedcase.example;

public class BankAccount {

  private int balance;
  private final int overDraft;

  public BankAccount(int overDraft) {
    this.overDraft = overDraft;
  }

  public void debit(int amount){
    this.balance -= amount;
  }

  public void topUp(int amount){
    this.balance += amount;
  }

  public int getBalance() {
    return balance;
  }

  public int getOverDraft() {
    return overDraft;
  }
}
