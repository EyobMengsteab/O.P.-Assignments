package ntnu.idata2305;

import java.io.*;
import java.net.*;
import java.util.Random;

/**
 * The CalculatorClient class simulates multiple clients connecting to a calculator server.
 * Each client sends two random numbers and a random operator to the server, then receives and prints the result.
 * The number of clients and server port are configurable via a properties file.
 */
public class CalculatorClient {

  /**
   * Simulates a single client packet by connecting to the server, sending two random numbers and an operator,
   * and printing the result received from the server.
   *
   * @param port     the port number to connect to the server
   * @param clientId the unique identifier for the client instance
   */
  private static void simulatePacket(int port, int clientId){
    try {
      Socket socket = new Socket("localhost", port);

      BufferedReader input = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
      PrintWriter output = new PrintWriter(
        socket.getOutputStream(), true);


      Random rand = new Random();

      // Generate random numbers (1–100)
      double num1 = rand.nextInt(100) + 1;
      double num2 = rand.nextInt(100) + 1;

      // Random operator selection
      char[] ops = {'A', 'S', 'M', 'D'};
      char operator = ops[rand.nextInt(ops.length)];

      // Send to server
      output.println(num1);
      output.println(num2);
      output.println(operator);

      // Receive result
      String result = input.readLine();
      System.out.println("Client " + clientId + " recieved result from server: " + result);

      socket.close();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  /**
   * The main entry point of the client application.
   * Reads configuration, starts multiple client threads, and waits for their completion.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    Config config = new Config("project.properties");

    int port = config.getInt("port", 5000);
    int sends = config.getInt("maxClients", 10);

    Thread[] threads = new Thread[sends];

    try {
      for(int i = 0; i < sends; i++){
        final int clientId = i + 1;
        threads[i] = new Thread(() -> simulatePacket(port, clientId));
        threads[i].start();
      }
      for(int i = 0; i < sends; i++) {
        threads[i].join();
      }

      System.out.println("All client requests completed.");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}