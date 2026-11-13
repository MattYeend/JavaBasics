/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matthewyeend.bankserver;

import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author matthewyeend
 */
public class BankService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Bank bank;
    
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
     * processing the request, and closing the socket when finished.
     *
     * @throws none
     */
    @Override
    public void run() {
        try {
            try {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            } finally {
                s.close();
            }
        } catch (IOException exception) {
        }
    }
    
    /**
     * Executes all commands until the QUIT command or end of input.
     * @throws java.io.IOException
     */
    public void doService() throws IOException {
        while (true) {
            if (!in.hasNext()) return;
            String command = in.next();
            if (command.equals("QUIT")) return;
            else executeCommand(command);
        }
    }
    
    /**
     * Executes a single command.
     * @param command the command to execute
     */
    public void executeCommand(String command) {
        int account = in.nextInt();
        if (command.equals("DEPOSIT")) {
            double amount = in.nextDouble();
            bank.deposit(account, amount);
        } else if (command.equals("WITHDRAW")) {
            double amount = in.nextDouble();
            bank.withdraw(account, amount);
        } else if (!command.equals("BALANCE")) {
            out.println("Invalid command");
            out.flush();
            return;
        }
        out.println(account + " " + bank.getBalance(account));
        out.flush();
    }
}
