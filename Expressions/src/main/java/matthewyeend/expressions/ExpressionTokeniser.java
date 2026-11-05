/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.expressions;

/**
 *
 * @author matthewyeend
 */
public class ExpressionTokeniser {

    private final String input;
    private int start; // The start of the current token
    private int end; // The position after the end of the current token

    /**
     * Constructs a tokeniser.
     *
     * @param anInput
     */
    public ExpressionTokeniser(String anInput) {
        input = java.util.Objects.requireNonNull(anInput, "anInput must not be null");
        start = 0;
        end = 0;
        nextToken(); // Find the first token
    }

    /**
     * Peeks at the next token without consuming it.
     *
     * @return the next token or null if there are no more tokens
     */
    public String peekToken() {
        if (start >= input.length()) {
            return null;
        } else {
            return input.substring(start, end);
        }
    }

    /**
     * Gets the next token and moves the tokeniser to the following token.
     *
     * @return the next token or null if there are no more tokens
     */
    public String nextToken() {
        String r = peekToken();
        start = end;
        while (start < input.length() && Character.isWhitespace(input.charAt(start))) {
            start++; // Skip whitespace between tokens
        }
        if (start >= input.length()) {
            end = start;
            return r;
        }
        if (isAsciiDigit(input.charAt(start))) {
            end = start + 1;
            while (end < input.length() && isAsciiDigit(input.charAt(end))) {
                end++;
            }
        } else {
            end = start + 1;
        }
        return r;
    }

    /**
     * Tests whether a character is an ASCII digit (0 to 9).
     *
     * @param c the character to test
     * @return true if the character is an ASCII digit
     */
    private static boolean isAsciiDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
