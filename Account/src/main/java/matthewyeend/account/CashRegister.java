/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.account;

/**
 *
 * @author matthewyeend
 */
public class CashRegister {
    private double purchase;
    private double payment;
    /**
     * Constructs a cash register with no money in it.
     */
    public CashRegister()
    {}
    
    /**
     * Records the sale of an item.
     * @param amount the price of the item
     */
    public void recordPurchase(double amount)
    {
        purchase = purchase + amount;
    }
    
    /**
     * Enters the payment received from the customer.
     * @param amount the amount of the payment
     */
    public void enterPayment(double amount)
    {
        payment = payment + amount;
    }
    
    /**
     * Computes the change due and resets the machine for the next customer.
     * @return the change due to the customer
     */
    public double giveChange()
    {
        double change = payment - purchase;
        purchase = 0;
        payment = 0;
        return change;
    }
}
