package org.superchargetest.bankaccount;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {


    @BeforeAll
    public static void setup() {
        BankAccount account1 = new BankAccount(10000);
        BankAccount account2 = new BankAccount(10000);
        account1.setID(1l);
        account2.setID(2l);
    }

    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount(10000);
        account.deposit(1000);
        assertEquals(11000, account.getBalance());
    }

    @Test
    public void testDepositThrowsException() {
        BankAccount account = new BankAccount(10000);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1));
    }

    @Test
    public void testWithdrawal() {
        BankAccount account = new BankAccount(10000);
        account.withdrawal(1000);
        assertEquals(9000, account.getBalance());
    }

    @Test
    public void testWithdrawalThrowsExceptionWhenOverBalance() {
        BankAccount account = new BankAccount(10000);
        assertThrows(IllegalArgumentException.class, () -> account.withdrawal(10001));
    }

    @Test
    public void testWithDrawalThrowsExceptionWhenIncorrectAmountEntered() {
        BankAccount account = new BankAccount(10000);
        assertThrows(IllegalArgumentException.class, () -> account.withdrawal(0));
    }

    @Test
    public void testAcountHistory(){
        BankAccount account = new BankAccount(10000);
        for (int i = 0; i < 4; i++) {
            account.deposit(1);
        }
        account.withdrawal(1);
        assertEquals(5, account.getTransactionHistory().size());
    }

}