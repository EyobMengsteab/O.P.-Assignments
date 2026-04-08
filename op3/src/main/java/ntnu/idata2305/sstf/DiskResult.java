package ntnu.idata2305.sstf;

import java.util.List;

/**
 * Stores the result of a disk scheduling algorithm.
 */
public class DiskResult {
  private final List<Integer> serviceOrder;
  private final List<String> steps;
  private final int totalHeadMovement;
  private final double averageSeekDistance;

  public DiskResult(List<Integer> serviceOrder,
                    List<String> steps,
                    int totalHeadMovement,
                    double averageSeekDistance) {
    this.serviceOrder = serviceOrder;
    this.steps = steps;
    this.totalHeadMovement = totalHeadMovement;
    this.averageSeekDistance = averageSeekDistance;
  }

  public List<Integer> getServiceOrder() {
    return serviceOrder;
  }

  public List<String> getSteps() {
    return steps;
  }

  public int getTotalHeadMovement() {
    return totalHeadMovement;
  }

  public double getAverageSeekDistance() {
    return averageSeekDistance;
  }
}