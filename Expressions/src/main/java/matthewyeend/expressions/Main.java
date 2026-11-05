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

    /**
     * Reads an expression from standard input, evaluates it and prints the result.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        int status = run();
        if (status != 0) {
            System.exit(status); // Scanner is already closed at this point
        }
    }

    /**
     * Runs the program and reports the outcome.
     *
     * @return 0 on success, or 1 if the input was missing, too long or invalid
     */
    private static int run() {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter an expression: ");
            if (!in.hasNextLine()) {
                System.err.println("No input received.");
                return 1;
            }
            String input = in.nextLine();
            if (input.length() > MAX_INPUT_LENGTH) {
                System.err.println("Expression must be at most " + MAX_INPUT_LENGTH + " characters.");
                return 1;
            }
            try {
                int value = new Evaluator(input).evaluate();
                System.out.println(input + " = " + value);
                return 0;
            } catch (ExpressionException ex) {
                System.err.println("Invalid expression: " + ex.getMessage());
                return 1;
            }
        }
    }
}
