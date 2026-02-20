package client;

import common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * TCP client that connects to the {@link server.Server} and exchanges
 * {@link Message} objects.
 *
 * <p>Incoming messages are delivered to the {@link MessageListener} callback
 * registered with {@link #setMessageListener(MessageListener)}.
 *
 * <p>Usage:
 * <pre>
 *   Client client = new Client("localhost", 5000, "Alice");
 *   client.setMessageListener(msg -> System.out.println(msg));
 *   client.connect();
 *   client.sendMessage("Hello, everyone!");
 *   client.disconnect();
 * </pre>
 */
public class Client {

    /** Callback interface for incoming messages. */
    public interface MessageListener {
        void onMessageReceived(Message message);
    }

    private final String host;
    private final int port;
    private final String username;

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private volatile boolean connected;

    private MessageListener messageListener;

    public Client(String host, int port, String username) {
        this.host = host;
        this.port = port;
        this.username = username;
    }

    /** Registers a listener that is called whenever a message arrives. */
    public void setMessageListener(MessageListener listener) {
        this.messageListener = listener;
    }

    /**
     * Opens the connection to the server and starts a background thread that
     * listens for incoming {@link Message} objects.
     *
     * @throws IOException if the connection cannot be established
     */
    public void connect() throws IOException {
        socket = new Socket(host, port);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
        connected = true;
        System.out.println("[Client] Connected to " + host + ":" + port + " as " + username);

        // Start background listener thread
        Thread listenerThread = new Thread(this::listenForMessages, "ClientListener");
        listenerThread.setDaemon(true);
        listenerThread.start();
    }

    /**
     * Sends a text message to the server.
     *
     * @param text the message content
     */
    public void sendMessage(String text) {
        if (!connected) {
            System.err.println("[Client] Not connected.");
            return;
        }
        try {
            out.writeObject(new Message(username, text));
            out.flush();
        } catch (IOException e) {
            System.err.println("[Client] Failed to send message: " + e.getMessage());
        }
    }

    /** Disconnects from the server. */
    public void disconnect() {
        connected = false;
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null && !socket.isClosed()) socket.close();
        } catch (IOException e) {
            System.err.println("[Client] Error while disconnecting: " + e.getMessage());
        }
        System.out.println("[Client] Disconnected.");
    }

    /** Returns {@code true} if the client is currently connected. */
    public boolean isConnected() {
        return connected;
    }

    /** Background loop that reads messages from the server. */
    private void listenForMessages() {
        try {
            Message message;
            while (connected && (message = (Message) in.readObject()) != null) {
                if (messageListener != null) {
                    messageListener.onMessageReceived(message);
                } else {
                    System.out.println("[Client] " + message);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            if (connected) {
                System.err.println("[Client] Connection lost: " + e.getMessage());
                connected = false;
            }
        }
    }
}
