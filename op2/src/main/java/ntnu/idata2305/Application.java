package ntnu.idata2305;

import ntnu.idata2305.algorithms.Algorithm;
import ntnu.idata2305.algorithms.FCFS;

import java.util.List;

public class Application {
  public static void main(String[] args) {

    // Case 1: All Processes Arrive at Time 0
    List<CpuProcess> case1Processes = List.of(
            new CpuProcess("P1", 0, 8),
            new CpuProcess("P2", 0, 4),
            new CpuProcess("P3", 0, 2),
            new CpuProcess("P4", 0, 6),
            new CpuProcess("P5", 0, 3)
    );

    // Case 2: One Long Process Followed by Short Processes
    List<CpuProcess> case2Processes = List.of(
            new CpuProcess("P1", 0, 20),
            new CpuProcess("P2", 1, 2),
            new CpuProcess("P3", 2, 2),
            new CpuProcess("P4", 3, 1),
            new CpuProcess("P5", 4, 3)
    );

    // Case 3: Continuous Arrival of Short Processes
    List<CpuProcess> case3Processes = List.of(
            new CpuProcess("P1", 0, 20),
            new CpuProcess("P2", 1, 2),
            new CpuProcess("P3", 2, 2),
            new CpuProcess("P4", 3, 2),
            new CpuProcess("P5", 4, 2),
            new CpuProcess("P6", 5, 2)
    );

    // --- 2. Instantiate our FCFS algorithm ---
    Algorithm fcfs = new FCFS();

    // --- 3. Run all test cases for FCFS ---
    System.out.println("========== TEST CASE 1 ==========\n");
    fcfs.run(case1Processes);

    System.out.println("========== TEST CASE 2 ==========\n");
    fcfs.run(case2Processes);

    System.out.println("========== TEST CASE 3 ==========\n");
    fcfs.run(case3Processes);
  }
}
