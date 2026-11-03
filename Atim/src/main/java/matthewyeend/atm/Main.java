/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.atm;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "Error opening accounts file.");
            return;
        }
        
        JFrame frame = new ATMFrame(theATM);
        frame.setTitle("Bank");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
