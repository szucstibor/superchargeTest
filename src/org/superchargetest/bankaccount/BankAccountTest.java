package org.superchargetest.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private static BankAccount account1 = new BankAccount(10000, 1l);
    private static BankAccount account2 = new BankAccount(10000, 2l);

    @BeforeEach
    public void reset() {
        account1.setBalance(10000);
        account1.setTransactionHistory(new ArrayList<>());

        account2.setBalance(10000);
        account2.setTransactionHistory(new ArrayList<>());
    }


    @Test
    public void testDeposit() {
        account1.deposit(1000);
        assertEquals(11000, account1.getBalance());
    }

    @Test
    public void testDepositThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account1.deposit(-1));
    }

    @Test
    public void testWithdrawal() {
        account1.withdrawal(1000);
        assertEquals(9000, account1.getBalance());
    }

    @Test
    public void testWithdrawalThrowsExceptionWhenOverBalance() {
        assertThrows(IllegalArgumentException.class, () -> account1.withdrawal(10001));
    }

    @Test
    public void testWithDrawalThrowsExceptionWhenIncorrectAmountEntered() {
        assertThrows(IllegalArgumentException.class, () -> account1.withdrawal(0));
    }

    @Test
    public void testAcountHistory() {
        for (int i = 0; i < 5; i++) {
            account1.deposit(1);
        }
        assertEquals(5, account1.getTransactionHistory().size());
    }

    @Test
    public void testTransaction() {
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        account1.sendTransaction(5000, account2);
        assertEquals(5000, account1.getBalance());
    }

    @Test
    public void testTransactionThrowsExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> account1.sendTransaction(-1, account2));
    }

    @Test
    public void testTransactionForThrowingExceptionForMoreThanBalance() {
        assertThrows(IllegalArgumentException.class, () -> account1.sendTransaction(10001, account2));
    }

    @Test
    public void testForSelfTransactionThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> account1.sendTransaction(5000, account1));
    }
}