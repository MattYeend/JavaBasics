/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.choice;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author matthewyeend
 */
public class FontViewerFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    
    private JLabel sampleField;
    private JCheckBox italichCheckBox;
    private JCheckBox boldCheckBox;
    private JRadioButton smallButton;
    private JRadioButton mediumButton;
    private JRadioButton largeButton;
    private JComboBox facenameCombo;
    private ActionListener listener;
    
    /**
     * Constructs the frame.
     */
    public FontViewerFrame() {
        // Constructs text sample
        sampleField = new JLabel("Big Java");
        add(sampleField, BorderLayout.CENTER);
        
        // This listener is shared among all components
        class ChoiceListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent event) {
                setSampleFont();
            }
        }
        
        listener = new ChoiceListener();
        
        createControlPanel();
        setSampleFont();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
}
