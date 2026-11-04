/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.db;

/**
 * Unchecked exception signalling a failed database operation. The message is
 * deliberately generic so no SQL or personal data leaks to the user interface.
 *
 * @author matthewyeend
 */
public class DataAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs the exception with a safe, generic message.
     *
     * @param message the description of the failure
     */
    public DataAccessException(String message) {
        super(message);
    }
}