/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.investment;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        final double INITIAL_BALANCE = 10000;
        final double RATE = 5;
        final int YEARS = 20;
        Investment invest = new Investment(INITIAL_BALANCE, RATE);
        invest.waitYears(YEARS);
        double balance = invest.getBalance();
        System.out.printf("The balance after %d years is %.2f\n", YEARS, balance);
    }
}
