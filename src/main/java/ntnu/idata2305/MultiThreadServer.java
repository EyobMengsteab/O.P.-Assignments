package ntnu.idata2305;

import java.io.*;
import java.net.*;

public class MultiThreadServer {
  public static boolean started = false;

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

      while (clientCount < maxClients) {
        Socket socket = serverSocket.accept();

        // Start timer when first packet has reached the server
        if(!started){
          startTime = System.currentTimeMillis();
          started = true;
        }

        clientCount++;
        System.out.println("Client " + clientCount + " connected");

        // Create a new thread for each client
        ClientHandler handler = new ClientHandler(socket, delay);
        handler.start();
      }

      long endTime = System.currentTimeMillis();
      System.out.println("Server accepted 10 clients in: "
        + (endTime - startTime) + " ms");

      serverSocket.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

class ClientHandler extends Thread {
  private Socket socket;
  private int delay;

  public ClientHandler(Socket socket, int delay) {
    this.socket = socket;
    this.delay = delay;
  }

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