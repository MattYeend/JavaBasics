/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.car;

import javax.swing.JFrame;

/**
 *
 * @author matthewyeend
 */
public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        
        frame.setSize(300, 400);
        frame.setTitle("Two Cars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CarComponent component = new CarComponent();
        frame.add(component);
        
        frame.setVisible(true);
    }
}
