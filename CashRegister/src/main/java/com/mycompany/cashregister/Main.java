/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cashregister;

import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args)
    {
        CashRegister register = new CashRegister();
        
        register.reccordPurchase(0.75);
        register.reccordPurchase(1.50);
        register.enterPayment(2, 0, 5, 0, 0);
        
        System.out.println("Change: ");
        System.out.println(register.giveChange());
        System.out.println("Expected: 0.25");
        
        register.reccordPurchase(2.25);
        register.reccordPurchase(19.25);
        register.enterPayment(23, 2, 0, 0, 0);
        System.out.println("Change: ");
        System.out.println(register.giveChange());
        System.out.println("Expected: 2.0");
        
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter Price: ");
        double price = in.nextDouble();
        register.reccordPurchase(price);
        
        System.out.println("Enter dollars: ");
        int dollars = in.nextInt();
        System.out.println("Enter quarters: ");
        int quaters = in.nextInt();
        System.out.println("Enter dimes: ");
        int dimes = in.nextInt();
        System.out.println("Enter nickels: ");
        int nickels = in.nextInt();
        System.out.println("Enter pennies: ");
        int pennies = in.nextInt();
        register.enterPayment(dollars, quaters, dimes, nickels, pennies);
        
        System.out.println("Your change: ");
        System.out.println(register.giveChange());
    }
}
