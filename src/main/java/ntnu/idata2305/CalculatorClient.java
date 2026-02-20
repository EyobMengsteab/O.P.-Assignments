package ntnu.idata2305;

import java.io.*;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class CalculatorClient {

  private static void simulatePacket(int port){
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
      System.out.println("Result from server: " + result);

      socket.close();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    Config config = new Config("project.properties");

    int port = config.getInt("port", 5000);
    int sends = config.getInt("maxClients", 10);

    try {
      for(int i = 0; i < sends; i++){
        simulatePacket(port);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}