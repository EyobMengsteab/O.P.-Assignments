package ntnu.idata2305.algorithms;

import ntnu.idata2305.types.RequestInput;
import ntnu.idata2305.types.RequestResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Shortest Seek Time First (SSTF) disk scheduling algorithm.
 * The disk head always services the request closest to its current position.
 */
public class SSTF implements Algorithm {

  /**
   * Executes the SSTF scheduling algorithm.
   *
   * @param input Contains the initial head position and the list of disk requests.
   * @return A RequestResult object containing:
   *         - the order in which requests were serviced,
   *         - the total head movement,
   *         - the average seek distance.
   */
  @Override
  public RequestResult run(RequestInput input) {
    RequestResult result = new RequestResult();
    int currentHead = input.initHeadPosition();
    result.algorithmName = "SSTF";

    // Add initial head position to the service order
    result.serviceOrder.add(currentHead);

    // Copy all requests into a mutable list of remaining requests
    List<Integer> remainingRequests = new ArrayList<>(input.diskRequests());

    // Continue until all requests have been serviced
    while (!remainingRequests.isEmpty()) {
      int closestRequest = remainingRequests.getFirst();
      int shortestDistance = Math.abs(closestRequest - currentHead);

      // Find the request with the shortest distance from current head
      for (int request : remainingRequests) {
        int distance = Math.abs(request - currentHead);

        if (distance < shortestDistance) {
          shortestDistance = distance;
          closestRequest = request;
        }
      }

      // Add the movement to total head movement
      result.totalHeadMovement += shortestDistance;

      // Move the head to the closest request
      currentHead = closestRequest;

      // Record the new head position in the service order
      result.serviceOrder.add(currentHead);

      // Remove the serviced request from the remaining list
      remainingRequests.remove(Integer.valueOf(closestRequest));
    }

    // Compute average seek distance if there are any requests
    if (!input.diskRequests().isEmpty()) {
      result.averageSeekDistance =
        (double) result.totalHeadMovement / input.diskRequests().size();
    }
    return result;
  }
}