/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.phonedirectory.validation;

/**
 * Thrown when user supplied contact details fail validation.
 *
 * @author matthewyeend
 */
public final class ValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs the exception with a message that is safe to show to the user.
     *
     * @param message the description of the validation failure
     */
    public ValidationException(String message) {
        super(message);
    }
}