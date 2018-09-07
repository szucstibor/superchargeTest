package org.superchargetest.bankaccount;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void setID(Long ID){
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

    public void withdrawal(int amount){
        if (amount > this.balance){
            throw new IllegalArgumentException("Your balance is lower than the requested amount.\nYour current balnce is: " + this.balance);
        } else if (amount <= 0){
            throw new IllegalArgumentException("Please enter a positive number, greater than 0 for withdrawal.");
        }
        this.balance -= amount;
        System.out.println("You have withdrawed " + amount + ".\nYour new balance is: " + this.balance);
        addToTransactionHistory("withdrawal", amount);
    }

    public void deposit(int amount){
        if (amount < 1){
            throw new IllegalArgumentException("Deposit has to be greater than 0");
        }
        this.balance += amount;
        System.out.println("You have deposited " + amount + ".\nYour new balance is: "+ this.balance);
        addToTransactionHistory("deposit", amount);
    }

    private void addToTransactionHistory(String type, int amount) {
        String[] withdrawalHistory = {type, LocalDateTime.now().toString(), String.valueOf(amount), String.valueOf(this.balance)};
        this.transactionHistory.add(withdrawalHistory);
    }

    public void printTransacrions(){
        System.out.println("Type, Date, Amount, Balance");
        for (String[] history: transactionHistory) {
            for (String field: history) {
                System.out.print(field + ", ");
            }
            System.out.println();
        }
    }

    public void printTransacrions(String type){
        System.out.println("Type, Date, Amount, Balance");
        for (String[] history: transactionHistory) {
            if (history[0].equals(type)){
                for (String field: history) {
                    System.out.print(field + ", ");
                }
                System.out.println();
            }
        }
    }
}
