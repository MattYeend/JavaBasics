/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.account;

/**
 *
 * @author matthewyeend
 */
public class Account {

    public static void main(String[] args) {
        CashRegister register = new CashRegister();
        
        register.recordPurchase(29.50);
        register.recordPurchase(9.25);
        register.enterPayment(50);
        
        double change = register.giveChange();
        
        System.out.println(change);
        System.out.println("Expected: 11.25");
        
        BankAccount testChecking = new BankAccount();
        testChecking.deposit(2000);
        testChecking.withdraw(500);
        System.out.println(testChecking.getBalance());
        System.out.println("Expecting: 1500");
    }
}
