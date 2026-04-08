package ntnu.idata2305;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class containing implementations of various disk scheduling algorithms.
 */
public class Algorithms {

    /**
     * Simulates the First-Come, First-Served (FCFS) disk scheduling algorithm.
     * The disk head services requests in the exact order they arrive in the queue.
     * @param initialHead The starting cylinder position of the disk head.
     * @param requests An array containing the queue of requested cylinder numbers.
     * @return A RequestResult object containing the service order, total movement, and average seek distance.
     */
    public static RequestResult runFCFS(int initialHead, int[] requests) {
        RequestResult result = new RequestResult();
        int currentHead = initialHead;

        result.serviceOrder.add(currentHead);

        for (int request : requests) {
            int distance = Math.abs(request - currentHead);
            result.totalHeadMovement += distance;

            currentHead = request;
            result.serviceOrder.add(currentHead);
        }

        if (requests.length > 0) {
            result.averageSeekDistance = (double) result.totalHeadMovement / requests.length;
        }
        return result;
    }




    /**
     * Simulates the Shortest Seek Time First (SSTF) disk scheduling algorithm.
     * The disk head always services the closest request from its current position.
     *
     * @param initialHead The starting cylinder position of the disk head.
     * @param requests An array containing the queue of requested cylinder numbers.
     * @return A RequestResult object containing the service order, total movement, and average seek distance.
     */
    public static RequestResult runSSTF(int initialHead, int[] requests) {
        RequestResult result = new RequestResult();
        int currentHead = initialHead;

        result.serviceOrder.add(currentHead);

        List<Integer> remainingRequests = new ArrayList<>();
        for (int request : requests) {
            remainingRequests.add(request);
        }

        while (!remainingRequests.isEmpty()) {
            int closestRequest = remainingRequests.get(0);
            int shortestDistance = Math.abs(closestRequest - currentHead);

            for (int request : remainingRequests) {
                int distance = Math.abs(request - currentHead);
                if (distance < shortestDistance) {
                    shortestDistance = distance;
                    closestRequest = request;
                }
            }

            result.totalHeadMovement += shortestDistance;
            currentHead = closestRequest;
            result.serviceOrder.add(currentHead);

            remainingRequests.remove(Integer.valueOf(closestRequest));
        }

        if (requests.length > 0) {
            result.averageSeekDistance = (double) result.totalHeadMovement / requests.length;
        }

        return result;
    }


    //TODO: Implementing SCAN Algorithm
}