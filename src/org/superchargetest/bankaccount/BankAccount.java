package org.superchargetest.bankaccount;

import java.util.List;

public class BankAccount {

    public Long ID;

    private float balance;

    private List<String> transactionHistory;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public Long getID() {
        return ID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
