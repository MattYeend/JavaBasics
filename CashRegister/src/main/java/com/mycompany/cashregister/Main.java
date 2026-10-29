/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cashregister;

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
    }
}
