/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.car;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author matthewyeend
 */
public class CarComponent extends JComponent
{
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        Car car1 = new Car(0, 0);
        
        int x = getWidth() - 60;
        int y = getHeight() - 30;
        
        Car car2 = new Car(x, y);
        
        car1.draw(g2);
        car2.draw(g2);
    }
}
