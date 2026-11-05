/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package matthewyeend.expressions;

import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class Main {
    private static final int MAX_INPUT_LENGTH = 256;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter an expression: ");
            if (!in.hasNextLine()) {
                System.err.println("No input received.");
                System.exit(1);
            }
            String input = in.nextLine();
            if (input.length() > MAX_INPUT_LENGTH) {
                System.err.println("Expression must be at most " + MAX_INPUT_LENGTH + " characters.");
                System.exit(1);
            }
            try {
                int value = new Evaluator(input).evaluate();
                System.out.println(input + " = " + value);
            } catch (ExpressionException ex) {
                System.err.println("Invalid expression: " + ex.getMessage());
                System.exit(1);
            }
        }
    }
}
