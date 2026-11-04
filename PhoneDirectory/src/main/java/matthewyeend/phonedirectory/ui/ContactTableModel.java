/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.ui;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import matthewyeend.phonedirectory.model.Contact;

/**
 * Table model that presents a list of contacts in a {@link javax.swing.JTable}.
 *
 * @author matthewyeend
 */
final class ContactTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private static final String[] COLUMNS = {"First name", "Last name", "Phone number", "Email"};

    private transient List<Contact> contacts = List.of();

    /**
     * Replaces the displayed contacts and refreshes the table.
     *
     * @param newContacts the contacts to display
     */
    void setContacts(List<Contact> newContacts) {
        this.contacts = List.copyOf(newContacts);
        fireTableDataChanged();
    }

    /**
     * Gets the contact at a model row.
     *
     * @param modelRow the row index in model coordinates
     * @return the contact on that row
     */
    Contact getContact(int modelRow) {
        return contacts.get(modelRow);
    }

    /**
     * Gets the number of rows.
     *
     * @return the number of contacts
     */
    @Override
    public int getRowCount() {
        return contacts.size();
    }

    /**
     * Gets the number of columns.
     *
     * @return the number of columns
     */
    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    /**
     * Gets a column heading.
     *
     * @param column the column index
     * @return the heading text
     */
    @Override
    public String getColumnName(int column) {
        return COLUMNS[column];
    }

    /**
     * Gets the value shown in a cell.
     *
     * @param row    the row index
     * @param column the column index
     * @return the cell value, with a missing email shown as an empty string
     */
    @Override
    public Object getValueAt(int row, int column) {
        Contact c = contacts.get(row);
        return switch (column) {
            case 0 -> c.firstName();
            case 1 -> c.lastName();
            case 2 -> c.phoneNumber();
            default -> c.email() == null ? "" : c.email();
        };
    }
}
