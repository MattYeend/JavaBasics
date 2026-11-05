/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.expressions;

/**
 * Thrown when an expression is malformed or cannot be evaluated safely.
 *
 * @author matthewyeend
 */
public class ExpressionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs an exception with a detail message.
     *
     * @param message a description of why the expression is invalid
     */
    public ExpressionException(String message) {
        super(message);
    }

    /**
     * Constructs an exception with a detail message and an underlying cause.
     *
     * @param message a description of why the expression is invalid
     * @param cause the exception that triggered this one
     */
    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
