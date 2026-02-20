import client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Entry point for launching the {@link Client} and sending messages
 * interactively from the console.
 *
 * <p>Usage: {@code java ClientMain [host] [port] [username]}
 *   <ul>
 *     <li>host     – server hostname or IP (default: localhost)</li>
 *     <li>port     – server port (default: 5000)</li>
 *     <li>username – display name shown with each message (default: Client)</li>
 *   </ul>
 *
 * <p>Type a message and press Enter to send it. Type {@code /quit} to exit.
 */
public class ClientMain {

    private static final String DEFAULT_HOST = "localhost";
    private static final int    DEFAULT_PORT = 5000;
    private static final String DEFAULT_USER = "Client";

    public static void main(String[] args) throws IOException {
        String host     = args.length > 0 ? args[0] : DEFAULT_HOST;
        int    port     = DEFAULT_PORT;
        String username = args.length > 2 ? args[2] : DEFAULT_USER;

        if (args.length > 1) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port '" + args[1] + "'. Using default port " + DEFAULT_PORT + ".");
            }
        }

        Client client = new Client(host, port, username);

        // Print every message received from the server to stdout
        client.setMessageListener(msg -> System.out.println("[Received] " + msg));

        client.connect();

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Connected. Type a message and press Enter. Type /quit to exit.");

        String line;
        while ((line = console.readLine()) != null) {
            if ("/quit".equalsIgnoreCase(line.trim())) {
                break;
            }
            client.sendMessage(line);
        }

        client.disconnect();
    }
}
