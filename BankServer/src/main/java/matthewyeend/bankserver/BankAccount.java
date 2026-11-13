/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.bankserver;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author matthewyeend
 */
public final class BankAccount {

    private static final int MONEY_SCALE = 2;

    private BigDecimal balance = BigDecimal.ZERO.setScale(MONEY_SCALE);

    /**
     * Deposits money into the account.
     * @param amount the amount to deposit, which must be positive
     */
    public synchronized void deposit(BigDecimal amount) {
        validateAmount(amount);
        balance = balance.add(amount);
    }

    /**
     * Withdraws money from the account.
     * @param amount the amount to withdraw, which must be positive
     * @throws IllegalStateException if the funds are insufficient
     */
    public synchronized void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance = balance.subtract(amount);
    }

    /**
     * Gets the current balance.
     * @return the account balance
     */
    public synchronized BigDecimal getBalance() {
        return balance;
    }

    private static void validateAmount(BigDecimal amount) {
        Objects.requireNonNull(amount, "amount");
        if (amount.signum() <= 0 || amount.scale() > MONEY_SCALE) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
}
