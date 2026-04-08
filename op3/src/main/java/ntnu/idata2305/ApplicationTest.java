package ntnu.idata2305;

import ntnu.idata2305.sstf.DiskResult;
import ntnu.idata2305.sstf.Sstf;


import java.util.Arrays;
import java.util.List;

public class ApplicationTest {
  public static void main(String[] args) {
    Sstf sstf = new Sstf();

    int initialHead = 53;
    List<Integer> requests = Arrays.asList(98, 183, 37, 122, 14, 124, 65, 67);

    DiskResult result = sstf.run(initialHead, requests);

    System.out.println("Algorithm: SSTF");
    System.out.println("Service Order: " + formatServiceOrder(result.getServiceOrder()));

    System.out.println("\nStep-by-step head movement:");
    for (String step : result.getSteps()) {
      System.out.println(step);
    }

    System.out.println("\nTotal Head Movement: " + result.getTotalHeadMovement());
    System.out.printf("Average Seek Distance: %.2f%n", result.getAverageSeekDistance());
  }

  private static String formatServiceOrder(List<Integer> order) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < order.size(); i++) {
      sb.append(order.get(i));
      if (i < order.size() - 1) {
        sb.append(" -> ");
      }
    }

    return sb.toString();
  }
}