/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.sync;

import java.util.Objects;

/**
 *
 * @author matthewyeend
 */
public final class DepositRunnable implements Runnable {
    private final BankAccount account;
    private final double amount;
    private final int count;

    /**
     * Constructs a deposit runnable.
     *
     * @param account the account into which to deposit money
     * @param amount  the amount to deposit in each repetition
     * @param count   the number of repetitions
     */
    public DepositRunnable(BankAccount account, double amount, int count) {
        this.account = Objects.requireNonNull(account, "account must not be null");
        if (Double.isNaN(amount) || Double.isInfinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("amount must be a positive, finite number");
        }
        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than zero");
        }
        this.amount = amount;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 1; i <= count; i++) {
            account.deposit(amount);
        }
    }
}
