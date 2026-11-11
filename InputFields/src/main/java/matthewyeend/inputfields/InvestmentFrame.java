/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.inputfields;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author matthewyeend
 */
public class InvestmentFrame extends JFrame {
    private static final int FRAME_WIDTH = 450;
    private static final int FRAME_HEIGHT = 100;
    
    private static final double DEFAULT_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;
    
    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button;
    private JLabel resultLabel;
    private JPanel panel;
    private BankAccount account;
    
    public InvestmentFrame() {
        account = new BankAccount(INITIAL_BALANCE);
        
        // Use instance variables for components
        resultLabel = new JLabel("Balance: " + account.getBalance());
        
        // Use helper methods
        createTextField();
        createButton();
        createPanel();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    private void createTextField() {
        rateLabel = new JLabel("Interest Rate: ");
        final int FIELD_WIDTH = 10;
        rateField = new JTextField(FIELD_WIDTH);
        rateField.setText("" + DEFAULT_RATE);
    }
    
    private void createButton() {
        button = new JButton("Add Interest");
        class AddInterestListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                double rate = Double.parseDouble(rateField.getText());
                double interest = account.getBalance() * rate / 100;
                account.deposit(interest);
                resultLabel.setText("Balance: " + account.getBalance());
            }
        }
        
        ActionListener listener = new AddInterestListener();
        button.addActionListener(listener);
    }
    
    private void createPanel() {
        panel = new JPanel();
        panel.add(rateLabel);
        panel.add(rateField);
        panel.add(button);
        panel.add(resultLabel);
        add(panel);
    }
}
