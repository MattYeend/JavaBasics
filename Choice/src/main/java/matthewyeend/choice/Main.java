/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.choice;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author matthewyeend
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new FontViewerFrame();
            frame.setTitle("FontViewer");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocation(50, 50);
            frame.setVisible(true);

            JFrame frameTwo = new FontViewerTwoFrame();
            frameTwo.setTitle("FontViewer Two");
            frameTwo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameTwo.setLocation(400, 50);
            frameTwo.setVisible(true);
        });
    }
}
