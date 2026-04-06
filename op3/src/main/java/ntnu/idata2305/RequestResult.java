package ntnu.idata2305;


import java.util.ArrayList;
import java.util.List;

/**
 * A data class used to store and format the results of a disk scheduling algorithm simulation.
 * It holds the sequence of serviced requests, the total head movement, and the average seek distance.
 */
public class RequestResult {
    public List<Integer> serviceOrder;
    public int totalHeadMovement;
    public double averageSeekDistance;

    /**
     * Constructs a new RequestResult with an empty service order and zeroed metrics.
     */
    public RequestResult() {
        this.serviceOrder = new ArrayList<>();
        this.totalHeadMovement = 0;
        this.averageSeekDistance = 0.0;
    }

    /**
     * Prints the algorithm's results to the console in a formatted manner.
     * @param algorithmName The name of the algorithm that produced these results (e.g., "FCFS").
     */
    public void printResult(String algorithmName) {
        System.out.println("Algorithm: " + algorithmName);

        System.out.print("Service Order: ");
        for (int i = 0; i < serviceOrder.size(); i++) {
            System.out.print(serviceOrder.get(i));
            if (i < serviceOrder.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nTotal Head Movement: " + totalHeadMovement);
        System.out.printf("Average Seek Distance: %.2f\n\n", averageSeekDistance);
    }
}