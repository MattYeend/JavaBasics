package matthewyeend.bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Simple client that sends a fixed set of commands to the bank server.
 *
 * @author matthewyeend
 */
public class BankClient {

    private static final String HOST = "localhost";
    private static final int SBAP_PORT = 8888;
    private static final int TIMEOUT_MS = 10_000;

    public static void main(String[] args) throws IOException {
        try (Socket s = new Socket(HOST, SBAP_PORT)) {
            s.setSoTimeout(TIMEOUT_MS);
            Scanner in = new Scanner(s.getInputStream());
            PrintWriter out = new PrintWriter(s.getOutputStream());

            sendAndReceive(out, in, "DEPOSIT 3 1000");
            sendAndReceive(out, in, "WITHDRAW 3 1000");

            System.out.println("Sending: QUIT");
            out.println("QUIT");
            out.flush();
        }
    }

    private static void sendAndReceive(PrintWriter out, Scanner in,
            String command) {
        System.out.println("Sending: " + command);
        out.println(command);
        out.flush();
        System.out.println("Receiving: " + in.nextLine());
    }
}
