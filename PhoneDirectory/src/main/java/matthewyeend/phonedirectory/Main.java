/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.phonedirectory;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import matthewyeend.phonedirectory.db.ContactDao;
import matthewyeend.phonedirectory.db.Database;
import matthewyeend.phonedirectory.ui.MainFrame;

/**
 *
 * @author matthewyeend
 */
public class Main {

    /**
     * Prevents instantiation, as this class only holds the entry point.
     */
    private Main() {
    }

    /**
     * Starts the application on the Event Dispatch Thread.
     *
     * @param args the command line arguments, which are not used
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Database database;
            try {
                database = new Database();
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Startup error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MainFrame frame = new MainFrame(new ContactDao(database.dataSource()));
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    database.close();
                }
            });
            frame.setVisible(true);
        });
    }
}
