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
public final class WithdrawRunnable implements Runnable {
    private final BankAccount account;
    private final double amount;
    private final int count;

    /**
     * Constructs a withdraw runnable.
     *
     * @param account the account from which to withdraw money
     * @param amount  the amount to withdraw in each repetition
     * @param count   the number of repetitions
     */
    public WithdrawRunnable(BankAccount account, double amount, int count) {
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
        try {
            for (int i = 1; i <= count; i++) {
                account.withdraw(amount);
            }
        } catch (InterruptedException e) {
            // Restore the interrupt flag so callers can react to it
            Thread.currentThread().interrupt();
        }
    }
}
