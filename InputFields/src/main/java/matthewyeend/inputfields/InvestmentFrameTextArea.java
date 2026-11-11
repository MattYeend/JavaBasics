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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author matthewyeend
 */
public class InvestmentFrameTextArea extends JFrame {
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 250;
    
    private static final int AREA_ROWS = 10;
    private static final int AREA_COLUMNS = 30;
    private static final double DEFAULT_RATE = 5;
    private static final double INITIAL_BALANCE = 1000;
    
    private JLabel rateLabel;
    private JTextField rateField;
    private JButton button;
    private final JTextArea resultArea;
    private JPanel panel;
    private final BankAccount account;
    
    /**
     * Constructs the frame, creating the bank account with its initial balance
     * and building the user interface components.
     *
     * <p>The frame is sized but not made visible. Call
     * {@link #setVisible(boolean)} to show it.</p>
     */
    public InvestmentFrameTextArea() {
        account = new BankAccount(INITIAL_BALANCE);
        resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
        resultArea.setEditable(false);
        
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
                resultArea.append(account.getBalance() + "\n");
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
        JScrollPane scrollPane = new JScrollPane(resultArea);
        panel.add(scrollPane);
        add(panel);
    }
}
