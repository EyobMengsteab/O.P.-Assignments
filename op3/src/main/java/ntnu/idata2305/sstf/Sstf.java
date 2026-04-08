package ntnu.idata2305.sstf;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Shortest Seek Time First (SSTF) disk scheduling algorithm.
 * SSTF always selects the pending request closest to the current head position.
 */
public class Sstf {

  /**
   * Runs the SSTF algorithm.
   *
   * @param initialHead the starting disk head position
   * @param requests the list of disk requests
   * @return result containing service order, movement steps, total movement,
   *         and average seek distance
   */
  public DiskResult run(int initialHead, List<Integer> requests) {
    List<Integer> remaining = new ArrayList<>(requests);
    List<Integer> serviceOrder = new ArrayList<>();
    List<String> steps = new ArrayList<>();

    int currentHead = initialHead;
    int totalHeadMovement = 0;

    serviceOrder.add(currentHead);

    while (!remaining.isEmpty()) {
      int closestRequest = remaining.get(0);
      int smallestDistance = Math.abs(currentHead - closestRequest);

      for (int request : remaining) {
        int distance = Math.abs(currentHead - request);

        if (distance < smallestDistance) {
          smallestDistance = distance;
          closestRequest = request;
        }
      }

      steps.add(currentHead + " -> " + closestRequest
        + " (distance: " + smallestDistance + ")");

      totalHeadMovement += smallestDistance;
      currentHead = closestRequest;
      serviceOrder.add(currentHead);
      remaining.remove(Integer.valueOf(closestRequest));
    }

    double averageSeekDistance = requests.isEmpty()
      ? 0.0
      : (double) totalHeadMovement / requests.size();

    return new DiskResult(serviceOrder, steps, totalHeadMovement, averageSeekDistance);
  }
}