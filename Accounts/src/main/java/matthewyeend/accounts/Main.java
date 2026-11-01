/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.accounts;

/**
 *
 * @author matthewyeend
 */
public class Main {

    public static void main(String[] args) {
        SavingsAccount testSavings = new SavingsAccount(0.5);
        
        CheckingAccount testChecking = new CheckingAccount(100);
        
        testSavings.deposit(10000);
        
        testSavings.transfer(2000, testChecking);
        testChecking.withdraw(1500);
        testChecking.withdraw(80);
        
        testSavings.transfer(1000, testChecking);
        testChecking.withdraw(400);
        
        // Simulate month end
        testSavings.addInterest();
        testChecking.deductFees();
        
        System.out.println("Test savings balance: " + testSavings.getBalance());
        System.out.println("Expected: 7035");
        
        System.out.println("Test checking balance: " + testChecking.getBalance());
        System.out.println("Expected: 1116");
    }
}
