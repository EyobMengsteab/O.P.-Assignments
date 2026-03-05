package ntnu.idata2305;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The MultiThreadServer class implements a multithreaded server that accepts multiple client connections.
 * Each client is handled in a separate thread, and the server processes a configurable number of clients.
 * The server reads configuration from a properties file, including port, maximum clients, and processing delay.
 */
public class MultiThreadServer {
  /**
   * Indicates whether the server has started processing clients.
   */
  public static boolean started = false;

  /**
   * The main entry point for the server application.
   * Initializes the server socket, accepts client connections, and starts a handler thread for each client.
   * Waits for all client threads to finish before shutting down.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    Config config = new Config("project.properties");

    int port = config.getInt("port", 5000);
    int maxClients = config.getInt("maxClients", 10);
    int delay = config.getInt("processingDelay", 1000);

    try {

      ServerSocket serverSocket = new ServerSocket(port);

      System.out.println("Multithreaded Server is running on port " + port);
      System.out.println("Max clients: " + maxClients);

      long startTime = 0;
      int clientCount = 0;

      List<Thread> activeThreads = new ArrayList<>();

      while (clientCount < maxClients) {
        Socket socket = serverSocket.accept();

        // Start timer when first packet has reached the server
        if(!started){
          startTime = System.currentTimeMillis();
          started = true;
        }

        clientCount++;
        System.out.println("Server accepted connection for Client " + clientCount);

        // Create a new thread for each client
        ClientHandler handler = new ClientHandler(socket, delay);
        activeThreads.add(handler);
        handler.start();
      }

      for (Thread thread : activeThreads) {
        thread.join();
      }

      long endTime = System.currentTimeMillis();
      System.out.println("Server processed " + maxClients + " clients in:"
        + (endTime - startTime) + " ms");

      serverSocket.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
 * The ClientHandler class handles communication with a single client in a separate thread.
 * It receives two numbers and an operator, performs the calculation, simulates a processing delay,
 * and sends the result back to the client.
 */
class ClientHandler extends Thread {
  private Socket socket;
  private int delay;

  /**
   * Constructs a new ClientHandler for the given client socket and processing delay.
   *
   * @param socket the client socket
   * @param delay  the processing delay in milliseconds
   */
  public ClientHandler(Socket socket, int delay) {
    this.socket = socket;
    this.delay = delay;
  }

  /**
   * Handles the client request by reading input, performing calculation, and sending the result.
   * Simulates a processing delay before responding.
   */
  public void run() {
    try {
      BufferedReader input = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(
        socket.getOutputStream(), true);

      double num1 = Double.parseDouble(input.readLine());
      double num2 = Double.parseDouble(input.readLine());
      char operator = input.readLine().charAt(0);

      double result = calculate(num1, num2, operator);

      // Simulate processing delay
      Thread.sleep(delay);

      output.println(result);

      socket.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Performs the calculation based on the provided operator.
   *
   * @param a  the first operand
   * @param b  the second operand
   * @param op the operator ('A' for addition, 'S' for subtraction, 'M' for multiplication, 'D' for division)
   * @return the result of the calculation
   */
  private double calculate(double a, double b, char op) {
    switch (op) {
      case 'A': return a + b;
      case 'S': return a - b;
      case 'M': return a * b;
      case 'D': return b != 0 ? a / b : 0;
      default: return 0;
    }
  }
}