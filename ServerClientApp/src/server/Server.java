package server;

import common.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Multi-threaded TCP server that accepts client connections and relays
 * {@link Message} objects between all connected clients.
 *
 * <p>Usage: {@code new Server(port).start()}
 */
public class Server {

    private final int port;
    private ServerSocket serverSocket;
    private volatile boolean running;

    /** Thread-safe list of active client handlers. */
    private final List<ClientHandler> clients =
            Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        this.port = port;
    }

    /**
     * Starts the server and blocks, accepting new client connections until
     * {@link #stop()} is called.
     */
    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("[Server] Listening on port " + port + " ...");

            while (running) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket, this);
                clients.add(handler);
                Thread thread = new Thread(handler, "ClientHandler-" + clientSocket.getRemoteSocketAddress());
                thread.setDaemon(true);
                thread.start();
            }
        } catch (IOException e) {
            if (running) {
                System.err.println("[Server] Error: " + e.getMessage());
            }
        } finally {
            stop();
        }
    }

    /**
     * Broadcasts a {@link Message} to every connected client except the
     * original sender.
     *
     * @param message the message to broadcast
     * @param sender  the handler that sent the message (excluded from broadcast)
     */
    public void broadcastMessage(Message message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != sender) {
                    client.sendMessage(message);
                }
            }
        }
    }

    /**
     * Removes a client handler from the active list (called when a client
     * disconnects).
     *
     * @param handler the handler to remove
     */
    public void removeClient(ClientHandler handler) {
        clients.remove(handler);
        System.out.println("[Server] Active clients: " + clients.size());
    }

    /** Returns the number of currently connected clients. */
    public int getClientCount() {
        return clients.size();
    }

    /** Stops the server and closes the listening socket. */
    public void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println("[Server] Error stopping server: " + e.getMessage());
        }
        System.out.println("[Server] Server stopped.");
    }
}
