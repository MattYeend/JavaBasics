/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.choice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author matthewyeend
 */
public final class ColourViewerFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    
    private final JPanel colourPanel;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    
    public ColourViewerFrame() {
        colourPanel = new JPanel();
        
        add(colourPanel, BorderLayout.CENTER);
        createControlPanel();
        setSampleColour();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    
    public void createControlPanel() {
        class ColourListener implements ChangeListener {
            @Override
            public void stateChanged(ChangeEvent event) {
                setSampleColour();
            }
        }
        
        ChangeListener listener = new ColourListener();
        
        redSlider = new JSlider(0, 255, 255);
        redSlider.addChangeListener(listener);
        
        greenSlider = new JSlider(0, 255, 175);
        greenSlider.addChangeListener(listener);
        
        blueSlider = new JSlider(0, 255, 175);
        blueSlider.addChangeListener(listener);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2));
        
        controlPanel.add(new JLabel("Red"));
        controlPanel.add(redSlider);
        
        controlPanel.add(new JLabel("Green"));
        controlPanel.add(greenSlider);
        
        controlPanel.add(new JLabel("Blue"));
        controlPanel.add(blueSlider);
        
        add(controlPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Reads the slider values and sets the panel to the selected colour.
     */
    public void setSampleColour() {
        //Read slider values
        int red = redSlider.getValue();
        int green = greenSlider.getValue();
        int blue = blueSlider.getValue();
        
        // Set panel background to selected colour
        colourPanel.setBackground(new Color(red, green, blue));
    }
}
