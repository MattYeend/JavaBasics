/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.expressions;

/**
 *
 * @author matthewyeend
 */
public class Evaluator {
    private static final int MAX_DEPTH = 100;

    private final ExpressionTokeniser tokeniser;
    private int depth;
    
    /**
     * Constructs an evaluator.
     * @param anExpression a string containing the expression to be evaluated
     */
    public Evaluator(String anExpression) {
        tokeniser = new ExpressionTokeniser(anExpression);
    }
    
       /**
     * Evaluates the whole expression and rejects any trailing input.
     *
     * @return the value of the expression
     * @throws ExpressionException if the expression is invalid or overflows
     */
    public int evaluate() {
        try {
            int value = getExpressionValue();
            String leftover = tokeniser.peekToken();
            if (leftover != null) {
                throw new ExpressionException("Unexpected token '" + leftover + "'.");
            }
            return value;
        } catch (ArithmeticException ex) {
            throw new ExpressionException("Arithmetic error: " + ex.getMessage(), ex);
        }
    }
    
    /**
     * Evaluates the expression.
     * @return the value of the expression
     */
    public int getExpressionValue() {
        int value = getTermValue();
        boolean done = false;
        while (!done) {
            String next = tokeniser.peekToken();
            if("+".equals(next) || "-".equals(next)) {
                tokeniser.nextToken(); // Discard "+" or "-"
                int value2 = getTermValue();
                if ("+".equals(next)) { value = Math.addExact(value, value2); }
                else { value = Math.subtractExact(value, value2); }
            } else {
                done = true;
            }
        }
        return value;
    }
    
    /**
     * Evaluates the next term found in the expression.
     * @return the value of the term
     */
    public int getTermValue() {
        int value = getFactorValue();
        boolean done = false;
        while (!done) {
            String next = tokeniser.peekToken();
            if("*".equals(next) || "/".equals(next)) {
                tokeniser.nextToken();
                int value2 = getFactorValue();
                if ("*".equals(next)) { value = Math.multiplyExact(value, value2); }
                else { value = Math.divideExact(value, value2); }
            } else {
                done = true;
            }
        }
        return value;
    }
    
    /**
     * Evaluates the next factor found in the expression.
     * @return the value of the factor
     */
    public int getFactorValue() {
        int value;
        String next = tokeniser.peekToken();
        if (next == null) {
            throw new ExpressionException("Unexpected end of expression.");
        }
        if ("(".equals(next)) {
            tokeniser.nextToken(); // Discard "("
            depth++;
            if (depth > MAX_DEPTH) {
                throw new ExpressionException("Expression is nested too deeply.");
            }
            value = getExpressionValue();
            depth--;
            if (!")".equals(tokeniser.nextToken())) {
                throw new ExpressionException("Expected ')'.");
            }
        } else {
            tokeniser.nextToken();
            value = parseNumber(next);
        }
        return value;
    }
    
    /**
     * Converts a token into an integer.
     *
     * @param token the token to convert
     * @return the integer value of the token
     * @throws ExpressionException if the token is not a valid integer
     */
    private static int parseNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException ex) {
            throw new ExpressionException("Expected a number but found '" + token + "'.", ex);
        }
    }
}
