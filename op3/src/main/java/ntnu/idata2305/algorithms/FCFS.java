package ntnu.idata2305.algorithms;

import ntnu.idata2305.types.RequestInput;
import ntnu.idata2305.types.RequestResult;

/**
 * Implementation of the First-Come, First-Served (FCFS) disk scheduling algorithm.
 * The disk head services requests strictly in the order they are received.
 */
public class FCFS implements Algorithm {

  /**
   * Executes the FCFS scheduling algorithm.
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
    result.algorithmName = "FCFS";

    // Add initial head position to the service order
    result.serviceOrder.add(currentHead);

    for (int request : input.diskRequests()) {
      // Calculate the distance the head must move to reach the next request
      int distance = Math.abs(request - currentHead);

      result.totalHeadMovement += distance;
      currentHead = request;

      result.serviceOrder.add(currentHead);
    }

    // Compute average seek distance if there are any requests
    if (!input.diskRequests().isEmpty()) {
      result.averageSeekDistance =
        (double) result.totalHeadMovement / input.diskRequests().size();
    }
    return result;
  }
}
