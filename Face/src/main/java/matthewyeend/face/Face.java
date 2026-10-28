/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.face;

import javax.swing.JFrame;

/**
 *
 * @author matthewyeend
 */
public class Face 
{
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(150, 250);
        frame.setTitle("Alien Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        FaceComponent component = new FaceComponent();
        frame.add(component);
        
        frame.setVisible(true);
    }
}
