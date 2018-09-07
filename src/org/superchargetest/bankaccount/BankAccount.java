package org.superchargetest.bankaccount;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BankAccount {

    public Long ID;

    private float balance;

    private List<String[]> transactionHistory = new ArrayList<>();

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<String[]> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String[]> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void withdrawal(int amount) {
        if (amount > this.balance) {
            throw new IllegalArgumentException("Your balance is lower than the requested amount.\nYour current balnce is: " + this.getBalance());
        } else if (amount <= 0) {
            throw new IllegalArgumentException("Please enter a positive number, greater than 0 for withdrawal.");
        }
        this.balance -= amount;
        System.out.println("You have withdrawed " + amount + ".\nYour new balance is: " + this.getBalance());
        addToTransactionHistory("withdrawal", amount, this.getID());
    }

    public void deposit(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Deposit has to be greater than 0");
        }
        this.balance += amount;
        System.out.println("You have deposited " + amount + ".\nYour new balance is: " + this.getBalance());
        addToTransactionHistory("deposit", amount, this.getID());
    }

    private void addToTransactionHistory(String type, int amount, Long by) {
        String timestamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String[] withdrawalHistory = {type, timestamp,
                String.valueOf(amount),
                String.valueOf(this.balance),
                String.valueOf(by)
        };
        this.transactionHistory.add(withdrawalHistory);
    }

    public void printTransactions() {
        System.out.println("Type, Date, Amount, Balance, From(ID)");
        for (String[] history : transactionHistory) {
            for (String field : history) {
                System.out.print(field + ", ");
            }
            System.out.println();
        }
    }

    public void printTransactionsByType(String type) {
        System.out.println("Type, Date, Amount, Balance");
        for (String[] history : transactionHistory) {
            if (history[0].equals(type)) {
                for (String field : history) {
                    System.out.print(field + ", ");
                }
                System.out.println();
            }
        }
    }

    public void sendTransaction(int amount, BankAccount to) {
        if (amount > this.getBalance()) {
            throw new IllegalArgumentException("You can't send more than your balance.\nYour balance is: " + this.getBalance());
        }
        to.receiveTransaction(amount, this);
        this.balance -= amount;
        System.out.println("You have sent " + amount + " to " + to.getID() + ". \nYour new balance is: " + this.getBalance());
    }

    public void receiveTransaction(int amount, BankAccount from) {
        this.balance += amount;
        addToTransactionHistory("recievedTransaction", amount, from.getID());
    }

}

