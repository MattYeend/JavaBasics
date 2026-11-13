package matthewyeend.bankserver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Starts the bank server and dispatches each client to a bounded thread pool.
 *
 * @author matthewyeend
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final int ACCOUNTS_LENGTH = 10;
    private static final int SBAP_PORT = 8888;
    private static final int MAX_CLIENTS = 20;
    private static final int BACKLOG = 50;

    public static void main(String[] args) throws IOException {
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        ExecutorService pool = Executors.newFixedThreadPool(MAX_CLIENTS);

        try (ServerSocket server = new ServerSocket(SBAP_PORT, BACKLOG,
                InetAddress.getLoopbackAddress())) {
            LOGGER.info("Waiting for clients to connect...");
            while (true) {
                Socket s = server.accept();
                LOGGER.info("Client connected.");
                pool.execute(new BankService(s, bank));
            }
        } finally {
            pool.shutdown();
        }
    }
}
