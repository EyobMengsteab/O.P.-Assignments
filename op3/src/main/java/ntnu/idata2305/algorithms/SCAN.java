package ntnu.idata2305.algorithms;

import ntnu.idata2305.types.HeadDirection;
import ntnu.idata2305.types.RequestInput;
import ntnu.idata2305.types.RequestResult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementation of the SCAN (Elevator) disk scheduling algorithm.
 * The disk head moves in one direction (left or right), servicing all requests
 * along the way, then reverses direction and continues servicing remaining requests.
 */
public class SCAN implements Algorithm {
  /**
   * Executes the SCAN scheduling algorithm.
   *
   * @param input Contains the initial head position, request list, and direction.
   * @return A RequestResult object containing:
   *         - the order in which requests were serviced,
   *         - the total head movement,
   *         - the average seek distance.
   */
  @Override
  public RequestResult run(RequestInput input) {
    RequestResult result = new RequestResult();
    int currentHead = input.initHeadPosition();
    result.algorithmName = "SCAN";

    // Get all cylinders left of head
    List<Integer> left = input.diskRequests().stream()
      .filter(c -> c < currentHead)
      .sorted(Comparator.reverseOrder())
      .toList();

    // Get all cylinders right of head
    List<Integer> right = input.diskRequests().stream()
      .filter(c -> c >= currentHead)
      .sorted()
      .toList();

    // Add initial head position into service order
    result.serviceOrder.add(currentHead);

    // Calculate total head movement + service order
    if(input.direction() == HeadDirection.LEFT ){
      result.totalHeadMovement = currentHead + right.getLast();

      result.serviceOrder.addAll(left);
      result.serviceOrder.addAll(right);
    } else{

      result.totalHeadMovement = (199 - currentHead) + (199 - left.getLast()); // 199 because (0-199) is the available numbers

      result.serviceOrder.addAll(right);
      result.serviceOrder.addAll(left);
    }

    if (!input.diskRequests().isEmpty()) {
      result.averageSeekDistance =
        (double) result.totalHeadMovement / result.serviceOrder.size();
    }
    return result;
  }
}
