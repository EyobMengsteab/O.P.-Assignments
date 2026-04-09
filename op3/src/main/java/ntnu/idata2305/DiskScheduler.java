package ntnu.idata2305;

import ntnu.idata2305.algorithms.Algorithm;
import ntnu.idata2305.algorithms.FCFS;
import ntnu.idata2305.algorithms.SCAN;
import ntnu.idata2305.algorithms.SSTF;
import ntnu.idata2305.types.HeadDirection;
import ntnu.idata2305.types.RequestInput;
import ntnu.idata2305.types.RequestResult;

import java.util.ArrayList;
import java.util.List;

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
        // Test cases
        // ==========================================

        // #1 Test Case
        RequestInput inputStandard = new RequestInput(
          53,
          List.of(98, 183, 37, 122, 14, 124, 65, 67),
          HeadDirection.RIGHT
        );

        // #2 Test Case
        RequestInput inputClustered= new RequestInput(
          50,
          List.of(45, 48, 52, 90, 150, 160),
          HeadDirection.RIGHT
        );

        // #3 Test Case
        RequestInput inputFairness = new RequestInput(
          15,
          List.of(10, 12, 14, 16, 100, 102),
          HeadDirection.RIGHT
        );

        // Algorithms
        Algorithm fcfsAlgorithm = new FCFS();
        Algorithm sstfAlgorithm = new SSTF();
        Algorithm scanAlgorithm = new SCAN();

        // ==========================================
        // Running test cases
        // ==========================================

        // Test Case #1
        System.out.println("\n--- Case 1: Standard Request Queue ---\n");
        fcfsAlgorithm.run(inputStandard).printResult();
        sstfAlgorithm.run(inputStandard).printResult();
        scanAlgorithm.run(inputStandard).printResult();

        // Test Case #2
        System.out.println("\n--- Case 2: Clustered Requests ---\n");
        fcfsAlgorithm.run(inputClustered).printResult();
        sstfAlgorithm.run(inputClustered).printResult();
        scanAlgorithm.run(inputClustered).printResult();

        // Test Case #3
        System.out.println("\n--- Case 3: Fairness Challenge ---\n");
        fcfsAlgorithm.run(inputFairness).printResult();
        sstfAlgorithm.run(inputFairness).printResult();
        scanAlgorithm.run(inputFairness).printResult();

    }
}