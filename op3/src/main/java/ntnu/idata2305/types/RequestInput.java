package ntnu.idata2305.types;

import java.util.List;

public record RequestInput(
  int initHeadPosition,
  List<Integer>  diskRequests,
  HeadDirection direction
) {
  // Constructor for when direction is not needed
  public RequestInput(int initHeadPosition, List<Integer> diskRequests) {
    this(initHeadPosition, diskRequests, null);
  }

  // Validate that the Disk cylinders are from 0 to 199
  public RequestInput {
    // Validate initHeadPosition
    if (initHeadPosition < 0 || initHeadPosition > 199) {
      throw new IllegalArgumentException(
        "initHeadPosition must be between 0 and 199: " + initHeadPosition
      );
    }

    // Validate diskRequests
    if (diskRequests == null) {
      throw new IllegalArgumentException("diskRequests cannot be null");
    }

    // Create an immutable copy which cannot be changed from outside the record
    diskRequests = List.copyOf(diskRequests);

    for (int req : diskRequests) {
      if (req < 0 || req > 199) {
        throw new IllegalArgumentException(
          "diskRequests must be between 0 and 199: " + req
        );
      }
    }
  }


}
