/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.atm;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Main {

    public static void main(String[] args) {
        ATM theATM;
        
        try {
            Bank theBank = new Bank();
            theBank.readCustomers("customers.txt");
            theATM = new ATM(theBank);
        } catch(IOException e) {
            System.out.println("Error opening accounts file");
            return;
        }
        
        Scanner in = new Scanner(System.in);
        
        while(true) {
            int state = theATM.getState();
            if(state == ATM.START) {
                System.out.print("Enter customer number: ");
                int number = in.nextInt();
                theATM.setCustomerNumber(number);
            } else if(state == ATM.PIN) {
                System.out.print("Enter PIN: ");
                int pin = in.nextInt();
                theATM.selectCustomer(pin);
            } else if(state == ATM.ACCOUNT) {
                System.out.print("A=Checking, B=Savings, C=Quit: ");
                String command = in.next();
                if(command.equalsIgnoreCase("A"))
                    theATM.selectAccount(ATM.CHECKING);
                else if(command.equalsIgnoreCase("B"))
                    theATM.selectAccount(ATM.SAVINGS);
                else if(command.equalsIgnoreCase("C"))
                    theATM.reset();
                else
                    System.out.println("Illegal input!");
            } else if(state == ATM.TRANSACT) {
                System.out.println("Balance = " + theATM.getBalance());
                System.out.print("A=Deposit, B=Withdraw, C=Cancel: ");
                String command = in.next();
                if(command.equalsIgnoreCase("A")) {
                    System.out.print("Amount: ");
                    double amount = in.nextDouble();
                    theATM.deposit(amount);
                    theATM.back();
                } else if(command.equalsIgnoreCase("B")) {
                    System.out.print("Amount: ");
                    double amount = in.nextDouble();
                    theATM.withdraw(amount);
                    theATM.back();
                } else if(command.equalsIgnoreCase("C")) {
                    theATM.back();
                } else {
                    System.out.println("Illegal input!");
                }
            }
        }
    }
}
