/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.inputfields;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame textFieldFrame = new InvestmentFrameTextField();
            textFieldFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textFieldFrame.setLocation(100, 100);
            textFieldFrame.setVisible(true);

            JFrame textAreaFrame = new InvestmentFrameTextArea();
            textAreaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            textAreaFrame.setLocation(100, 260);
            textAreaFrame.setVisible(true);
        });
    }
}
