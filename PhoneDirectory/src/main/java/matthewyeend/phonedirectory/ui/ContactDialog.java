/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.Optional;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import matthewyeend.phonedirectory.model.Contact;
import matthewyeend.phonedirectory.validation.ContactValidator;
import matthewyeend.phonedirectory.validation.ValidationException;

/**
 * Modal dialog used to add a new contact or edit an existing one.
 *
 * @author matthewyeend
 */
final class ContactDialog extends JDialog {

    private static final long serialVersionUID = 1L;

    private final JTextField firstName = new JTextField(20);
    private final JTextField lastName = new JTextField(20);
    private final JTextField phone = new JTextField(20);
    private final JTextField email = new JTextField(20);
    private final JLabel errorLabel = new JLabel(" ");
    private final Long contactId;
    private transient Contact result;

    /**
     * Constructs the dialog.
     *
     * @param owner    the parent frame
     * @param existing the contact to edit, or {@code null} to add a new one
     */
    ContactDialog(Frame owner, Contact existing) {
        super(owner, existing == null ? "Add contact" : "Edit contact", true);
        this.contactId = existing == null ? null : existing.id();

        if (existing != null) {
            firstName.setText(existing.firstName());
            lastName.setText(existing.lastName());
            phone.setText(existing.phoneNumber());
            email.setText(existing.email() == null ? "" : existing.email());
        }

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));
        form.add(new JLabel("First name"));
        form.add(firstName);
        form.add(new JLabel("Last name"));
        form.add(lastName);
        form.add(new JLabel("Phone number"));
        form.add(phone);
        form.add(new JLabel("Email (optional)"));
        form.add(email);

        errorLabel.setForeground(new Color(0xB00020));
        errorLabel.setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 12));

        JButton ok = new JButton("Save");
        JButton cancel = new JButton("Cancel");
        ok.addActionListener(e -> onSave());
        cancel.addActionListener(e -> dispose());
        getRootPane().setDefaultButton(ok);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(cancel);
        buttons.add(ok);

        JPanel south = new JPanel(new BorderLayout());
        south.add(errorLabel, BorderLayout.NORTH);
        south.add(buttons, BorderLayout.SOUTH);

        add(form, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    /**
     * Shows the dialog and blocks until it is closed.
     *
     * @return the validated contact, or empty if the user cancelled
     */
    Optional<Contact> showDialog() {
        setVisible(true);
        return Optional.ofNullable(result);
    }

    /**
     * Validates the form and closes the dialog on success, otherwise shows the error.
     */
    private void onSave() {
        try {
            result = ContactValidator.validate(contactId, firstName.getText(), lastName.getText(),
                    phone.getText(), email.getText());
            dispose();
        } catch (ValidationException ex) {
            errorLabel.setText(ex.getMessage());
            pack();
        }
    }
}
