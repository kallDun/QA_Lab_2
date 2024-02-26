import org.example.domain.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BankAccountTest {

    @Test
    public void testCreateAccount() {
        BankAccount account = new BankAccount("John Doe", 100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    public void testWithdraw() {
        BankAccount account = new BankAccount("John Doe", 100.0);
        account.withdraw(50.0);
        assertEquals(50.0, account.getBalance());
    }

    @Test
    public void testDeposit() {
        BankAccount account = new BankAccount("John Doe", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance());
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        BankAccount account = new BankAccount("John Doe", 100.0);
        try {
            account.withdraw(150.0);
            fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Not enough money to withdraw", e.getMessage());
        }
    }

    @Test
    public void testInvalidAmount() {
        try {
            new BankAccount("John Doe", -100.0);
            fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("The initial balance cannot be negative", e.getMessage());
        }

        BankAccount account = new BankAccount("John Doe", 100.0);
        try {
            account.withdraw(-50.0);
            fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Withdraw amount cannot be negative", e.getMessage());
        }

        try {
            account.deposit(-50.0);
            fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("Deposit amount cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testNegativeInitialBalance() {
        try {
            new BankAccount("John Doe", -100.0);
            fail("Expected an exception");
        } catch (IllegalArgumentException e) {
            assertEquals("The initial balance cannot be negative", e.getMessage());
        }
    }

    @Test
    public void testLargeNumberOfTransactions() {
        BankAccount account = new BankAccount("John Doe", 1000.0);
        for (int i = 0; i < 1000; i++) {
            account.withdraw(1.0);
            account.deposit(1.0);
        }
        assertEquals(1000.0, account.getBalance());
    }

    @Test
    public void testEmptyAccount() {
        BankAccount account = new BankAccount("John Doe", 0.0);
        assertEquals(0.0, account.getBalance());

        account.withdraw(0.0);
        assertEquals(0.0, account.getBalance());

        account.deposit(0.0);
        assertEquals(0.0, account.getBalance());
    }
}
