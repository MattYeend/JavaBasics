/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.db;

/**
 * Thrown when a contact would break the unique phone number constraint.
 *
 * @author matthewyeend
 */
public final class DuplicateContactException extends DataAccessException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs the exception with a fixed user safe message.
     */
    public DuplicateContactException() {
        super("A contact with that phone number already exists.");
    }
}
