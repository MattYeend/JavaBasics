/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.atm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author matthewyeend
 */
public final class ATMFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 300;
    
    private final JButton aButton;
    private final JButton bButton;
    private final JButton cButton;
    
    private KeyPad pad;
    private final JTextArea display;
    
    private final ATM theATM;
    
    /**
     * Constructs the user interface of the ATM frame.
     * @param anATM
     */
    public ATMFrame(ATM anATM) {
        theATM = anATM;
        
        pad = new KeyPad();
        
        display = new JTextArea(4, 20);
        
        aButton = new JButton(" A ");
        aButton.addActionListener(new AButtonListener());
        
        bButton = new JButton(" B ");
        bButton.addActionListener(new BButtonListener());
        
        cButton = new JButton(" C ");
        cButton.addActionListener(new CButtonListener());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(aButton);
        buttonPanel.add(bButton);
        buttonPanel.add(cButton);
        
        setLayout(new FlowLayout());
        add(pad);
        add(display);
        add(buttonPanel);
        showState();
        
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    /**
     * Updates display message.
     */
    public void showState() {
        int state = theATM.getState();
        pad.clear();
        switch (state) {
            case ATM.START -> display.setText("Enter customer numner\nA = OK");
            case ATM.PIN -> display.setText("Enter PIN\nA = OK");
            case ATM.ACCOUNT -> display.setText("""
                    Select Account
                    A = Checking
                    B = Savings
                    C = Exit""");
            case ATM.TRANSACT -> display.setText("Balance = "
                        + theATM.getBalance()
                        + "\nEnter amount and select transaction\n"
                                + "A = Withdraw\nB = Deposit\nC = Candel");
            default -> {
            }
        }
    }
    
    class AButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int state = theATM.getState();
            switch (state) {
                case ATM.START -> theATM.setCustomerNumber((int) pad.getValue());
                case ATM.PIN -> theATM.selectCustomer((int) pad.getValue());
                case ATM.ACCOUNT -> theATM.selectAccount(ATM.ACCOUNT);
                case ATM.TRANSACT -> {
                    theATM.withdraw(pad.getValue());
                    theATM.back();
                }
                default -> {
                }
            }
            showState();
        }
    }
    
    class BButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int state = theATM.getState();
            if(state == ATM.ACCOUNT)
                theATM.selectAccount(ATM.SAVINGS);
            else if(state == ATM.TRANSACT) {
                theATM.deposit(pad.getValue());
                theATM.back();
            }
            showState();
        }
    }
    
    class CButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            int state = theATM.getState();
            if(state == ATM.ACCOUNT)
                theATM.reset();
            else if(state == ATM.TRANSACT)
                theATM.back();
            showState();
        }
}
}
