/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import matthewyeend.phonedirectory.db.ContactDao;
import matthewyeend.phonedirectory.db.DuplicateContactException;
import matthewyeend.phonedirectory.model.Contact;

/**
 * Main window of the phone directory, offering search, add, edit and delete.
 *
 * @author matthewyeend
 */
public final class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private final transient ContactDao dao;
    private final ContactTableModel tableModel = new ContactTableModel();
    private final JTable table = new JTable(tableModel);
    private final JTextField searchField = new JTextField(25);

    /**
     * Constructs the main window and loads the initial contact list.
     *
     * @param dao the data access object used for all database work
     */
    public MainFrame(ContactDao dao) {
        super("Phone Directory");
        this.dao = dao;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoCreateRowSorter(true);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> refresh());
        searchField.addActionListener(e -> refresh());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Search"));
        top.add(searchField);
        top.add(searchButton);

        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton delete = new JButton("Delete");
        add.addActionListener(e -> onAdd());
        edit.addActionListener(e -> onEdit());
        delete.addActionListener(e -> onDelete());

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.add(add);
        bottom.add(edit);
        bottom.add(delete);

        JPanel content = new JPanel(new BorderLayout(8, 8));
        content.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        content.add(top, BorderLayout.NORTH);
        content.add(new JScrollPane(table), BorderLayout.CENTER);
        content.add(bottom, BorderLayout.SOUTH);

        setContentPane(content);
        setSize(760, 480);
        setLocationRelativeTo(null);
        refresh();
    }

    /**
     * Reloads the table using the current search text.
     */
    private void refresh() {
        runAsync(() -> dao.search(searchField.getText()), tableModel::setContacts);
    }

    /**
     * Opens the add dialog and saves the new contact.
     */
    private void onAdd() {
        new ContactDialog(this, null).showDialog().ifPresent(contact ->
                runAsync(() -> {
                    dao.insert(contact);
                    return Boolean.TRUE;
                }, done -> refresh()));
    }

    /**
     * Opens the edit dialog for the selected contact and saves the changes.
     */
    private void onEdit() {
        selectedContact().flatMap(existing -> new ContactDialog(this, existing).showDialog())
                .ifPresent(contact -> runAsync(() -> {
                    dao.update(contact);
                    return Boolean.TRUE;
                }, done -> refresh()));
    }

    /**
     * Deletes the selected contact after the user confirms.
     */
    private void onDelete() {
        selectedContact().ifPresent(contact -> {
            int choice = JOptionPane.showConfirmDialog(this,
                    "Delete " + contact.firstName() + " " + contact.lastName() + "?",
                    "Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                runAsync(() -> {
                    dao.delete(contact.id());
                    return Boolean.TRUE;
                }, done -> refresh());
            }
        });
    }

    /**
     * Gets the contact currently selected in the table.
     *
     * @return the selected contact, or empty if nothing is selected
     */
    private Optional<Contact> selectedContact() {
        int viewRow = table.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a contact first.");
            return Optional.empty();
        }
        return Optional.of(tableModel.getContact(table.convertRowIndexToModel(viewRow)));
    }

    /**
     * Runs database work off the Event Dispatch Thread so the GUI never freezes.
     *
     * @param <T>       the type returned by the background task
     * @param task      the work to run in the background
     * @param onSuccess the action to run on the Event Dispatch Thread with the result
     */
    private <T> void runAsync(Callable<T> task, Consumer<T> onSuccess) {
        new SwingWorker<T, Void>() {
            @Override
            protected T doInBackground() throws Exception {
                return task.call();
            }

            @Override
            protected void done() {
                try {
                    onSuccess.accept(get());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                } catch (ExecutionException ex) {
                    showError(ex.getCause());
                }
            }
        }.execute();
    }

    /**
     * Shows a generic error message. No stack traces or SQL details are shown to the user.
     *
     * @param cause the failure that occurred
     */
    private void showError(Throwable cause) {
        String message = cause instanceof DuplicateContactException
                ? "A contact with that phone number already exists."
                : "The operation could not be completed. Please try again or contact support.";
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
