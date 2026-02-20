import server.Server;

/**
 * Entry point for launching the {@link Server}.
 *
 * <p>Usage: {@code java ServerMain [port]}
 *   <ul>
 *     <li>port – TCP port to listen on (default: 5000)</li>
 *   </ul>
 */
public class ServerMain {

    private static final int DEFAULT_PORT = 5000;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port '" + args[0] + "'. Using default port " + DEFAULT_PORT + ".");
            }
        }

        Server server = new Server(port);
        // Register a JVM shutdown hook to gracefully stop the server
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop, "ShutdownHook"));
        server.start();
    }
}
