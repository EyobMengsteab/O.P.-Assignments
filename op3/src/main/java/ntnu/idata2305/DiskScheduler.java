package ntnu.idata2305;

/**
 * The main driver class for the Disk Scheduling simulation.
 * This class sets up the required test cases and executes the scheduling algorithms
 * to compare their performance based on total head movement and average seek distance.
 */
public class DiskScheduler {

    /**
     * The main entry point for the application.
     * Sets up the initial parameters for Case 1, Case 2, and Case 3, and runs the algorithms.
     */
    public static void main(String[] args) {

        // ==========================================
        // CASE 1: Standard Request Queue
        // ==========================================
        System.out.println("--- Case 1: Standard Request Queue ---");
        int initialHead1 = 53;
        int[] requests1 = {98, 183, 37, 122, 14, 124, 65, 67};

        RequestResult fcfsResult1 = Algorithms.runFCFS(initialHead1, requests1);
        fcfsResult1.printResult("FCFS");

        RequestResult sstfResult1 = Algorithms.runSSTF(initialHead1, requests1);
        sstfResult1.printResult("SSTF");

        // ==========================================
        // CASE 2: Clustered Requests
        // ==========================================
        System.out.println("--- Case 2: Clustered Requests ---");
        int initialHead2 = 50;
        int[] requests2 = {45, 48, 52, 90, 150, 160};

        RequestResult fcfsResult2 = Algorithms.runFCFS(initialHead2, requests2);
        fcfsResult2.printResult("FCFS");

        RequestResult sstfResult2 = Algorithms.runSSTF(initialHead2, requests2);
        sstfResult2.printResult("SSTF");

        // ==========================================
        // CASE 3: Fairness Challenge
        // ==========================================
        System.out.println("--- Case 3: Fairness Challenge ---");
        int initialHead3 = 15;
        int[] requests3 = {10, 12, 14, 16, 100, 102};

        RequestResult fcfsResult3 = Algorithms.runFCFS(initialHead3, requests3);
        fcfsResult3.printResult("FCFS");

        RequestResult sstfResult3 = Algorithms.runSSTF(initialHead3, requests3);
        sstfResult3.printResult("SSTF");

    }
}