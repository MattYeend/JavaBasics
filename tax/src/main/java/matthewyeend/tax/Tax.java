/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.tax;

import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Tax {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        System.out.print("Please enter your income: ");
        double income = in.nextDouble();
        
        System.out.print("Are you married? (Y/N): ");
        String input = in.next();
        int status;
        if(input.equalsIgnoreCase("Y"))
            status = TaxReturn.MARRIED;
        else
            status = TaxReturn.SINGLE;
        TaxReturn aTaxReturn = new TaxReturn(income, status);
        System.out.println("Tax: " + aTaxReturn.getTax());
    }
}
