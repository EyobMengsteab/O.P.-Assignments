package ntnu.idata2305;

import java.io.*;
import java.net.*;

public class SingleThreadServer {
  public static boolean started = false;
  public static void main(String[] args) {
    Config config = new Config("project.properties");

    int port = config.getInt("port", 5000);
    int maxClients = config.getInt("maxClients", 10);
    int delay = config.getInt("processingDelay", 1000);

    try {
      ServerSocket serverSocket = new ServerSocket(port);

      System.out.println("Single-threaded Server is running on port " + port);
      System.out.println("Max clients: " + maxClients);

      long startTime = 0;
      int clientCount = 0;

      while (clientCount < maxClients) { // Handle 10 clients
        Socket socket = serverSocket.accept(); // Blocking call

        // Start timer when first packet has reached the server
        if(!started){
          startTime = System.currentTimeMillis();
          started = true;
        }

        clientCount++;

        System.out.println("Client " + clientCount + " connected");

        BufferedReader input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(
          socket.getOutputStream(), true);

        double num1 = Double.parseDouble(input.readLine());
        double num2 = Double.parseDouble(input.readLine());
        char operator = input.readLine().charAt(0);

        System.out.println(num1 + " | "+ num2 + " | " + operator);

        double result = calculate(num1, num2, Character.toUpperCase(operator));

        // Simulate processing delay (to show OS effect)
        Thread.sleep(delay);

        output.println(result);

        socket.close();
      }

      long endTime = System.currentTimeMillis();
      System.out.println("Total execution time (Single-threaded): "
        + (endTime - startTime) + " ms");

      serverSocket.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static double calculate(double a, double b, char op) {
    switch (op) {
      case 'A': return a + b;
      case 'S': return a - b;
      case 'M': return a * b;
      case 'D': return b != 0 ? a / b : 0;
      default: return 0;
    }
  }
}