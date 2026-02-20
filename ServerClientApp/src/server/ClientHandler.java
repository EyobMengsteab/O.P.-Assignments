package server;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Handles communication with a single connected client on its own thread.
 * Reads incoming {@link Message} objects and broadcasts them to all other
 * connected clients via the owning {@link Server}.
 */
public class ClientHandler implements Runnable {

    private final Socket socket;
    private final Server server;
    private final String clientAddress;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.clientAddress = socket.getInetAddress().getHostAddress()
                + ":" + socket.getPort();
    }

    /** Returns a human-readable identifier for this client connection. */
    public String getClientAddress() {
        return clientAddress;
    }

    /**
     * Sends a {@link Message} to this client.
     *
     * @param message the message to send
     */
    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("[Server] Could not send message to " + clientAddress + ": " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            // Notify the client that it has connected successfully
            sendMessage(new Message("SERVER", "Welcome! You are connected as " + clientAddress));
            System.out.println("[Server] Client connected: " + clientAddress);

            Message message;
            while ((message = (Message) in.readObject()) != null) {
                System.out.println("[Server] Received from " + clientAddress + ": " + message.getContent());
                server.broadcastMessage(message, this);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[Server] Client disconnected: " + clientAddress);
        } finally {
            server.removeClient(this);
            closeConnection();
        }
    }

    /** Closes streams and then the socket for this client. */
    private void closeConnection() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.err.println("[Server] Error closing connection for " + clientAddress + ": " + e.getMessage());
        }
    }
}
