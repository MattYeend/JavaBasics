/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.bankserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matthewyeend
 */
public class BankService implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(BankService.class.getName());
    private static final int SOCKET_TIMEOUT_MS = 30_000;
    private static final int MAX_TOKEN_LENGTH = 32;

    private final Socket s;
    private final Bank bank;
    private Scanner in;
    private PrintWriter out;
    
    /**
     * Constructs a service object that processes commands from a socket for a
     * bank.
     * @param aSocket the socket
     * @param aBank the bank
     */
    public BankService(Socket aSocket, Bank aBank) {
        s = aSocket;
        bank = aBank;
    }

    /**
     * Executes the service by initialising the input and output streams,
     * processing the requests, and closing the socket when finished.
     */
    @Override
    public void run() {
        try (Socket socket = s) {
            socket.setSoTimeout(SOCKET_TIMEOUT_MS);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
            doService();
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, "Client connection error", exception);
        }
    }

    /**
     * Executes all commands until the QUIT command or end of input.
     * @throws IOException if the connection fails
     */
    public void doService() throws IOException {
        while (in.hasNext()) {
            String command = in.next();
            if ("QUIT".equals(command)) {
                return;
            }
            executeCommand(command);
        }
    }

    /**
     * Executes a single command. Malformed input produces a generic error
     * response and never exposes internal detail to the client.
     * @param command the command to execute
     */
    public void executeCommand(String command) {
        try {
            int account = Integer.parseInt(readToken());
            if ("DEPOSIT".equals(command)) {
                bank.deposit(account, new BigDecimal(readToken()));
            } else if ("WITHDRAW".equals(command)) {
                bank.withdraw(account, new BigDecimal(readToken()));
            } else if (!"BALANCE".equals(command)) {
                respond("Invalid command");
                return;
            }
            respond(account + " " + bank.getBalance(account));
        } catch (IllegalArgumentException | IllegalStateException
                | NoSuchElementException ex) {
            LOGGER.log(Level.FINE, "Rejected client request", ex);
            respond("Invalid request");
        }
    }

    /**
     * Reads the next whitespace-delimited token from the client input.
     *
     * Client input is entrusted, so the token length is capped at
     * {@value #MAX_TOKEN_LENGTH} characters. This prevents oversized values
     * from exhausting memory during later parsing, for example by
     * {@link java.math.BigDecimal}. The exception message is deliberately
     * generic to avoid disclosing internal detail.
     *
     * @return the next token, never {@code null} and never longer than
     *         {@value #MAX_TOKEN_LENGTH} characters
     * @throws IllegalArgumentException if the token exceeds the maximum length
     * @throws java.util.NoSuchElementException if the input is exhausted
     */
    private String readToken() {
        String token = in.next();
        if (token.length() > MAX_TOKEN_LENGTH) {
            throw new IllegalArgumentException("Token too long");
        }
        return token;
    }

    /**
     * Sends a single line of text to the client and flushes the stream so that
     * the response is delivered immediately.
     * 
     * Callers must pass only generic or already sanitised messages. Internal
     * error detail, stack traces and sensitive values must never be sent to
     * the client.
     *
     * @param message the line to send, without a trailing line separator
     */
    private void respond(String message) {
        out.println(message);
        out.flush();
    }
}
