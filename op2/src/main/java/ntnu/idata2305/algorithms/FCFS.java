package ntnu.idata2305.algorithms;

import ntnu.idata2305.CpuProcess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implements the First Come First Serve (FCFS) scheduling algorithm.
 * FCFS is a non-preemptive algorithm that processes jobs strictly in
 * the chronological order of their arrival.
 */
public class FCFS implements Algorithm {

  /**
   * Runs the FCFS scheduling logic on the provided list of processes.
   * Calculates and displays CT, TAT, WT, and their averages.
   *
   * @param process The list of CPU processes to be scheduled.
   */
  @Override
  public void run(List<CpuProcess> process) {
    List<CpuProcess> sortedProcesses = new ArrayList<>(process);
    sortedProcesses.sort(Comparator.comparingInt(CpuProcess::arrivalTime));

    int currentTime = 0;
    double totalWT = 0;
    double totalTAT = 0;

    System.out.println("--- First Come First Serve (FCFS) ---");
    System.out.println("Process ID | Arrival | Burst | CT | TAT | WT");

    for (CpuProcess p : sortedProcesses) {

      if (currentTime < p.arrivalTime()) {
        currentTime = p.arrivalTime();
      }

      // Executes the process: add its burst time to the current time
      currentTime += p.burstTime();

      // Calculates metrics using the formulas
      int completionTime = currentTime;
      int turnaroundTime = completionTime - p.arrivalTime(); // TAT = CT - Arrival Time
      int waitingTime = turnaroundTime - p.burstTime();      // WT = TAT - Burst Time

      // Adds to our running totals for the averages
      totalTAT += turnaroundTime;
      totalWT += waitingTime;

      // Displays the results for this specific process
      System.out.printf("%-10s | %-7d | %-5d | %-2d | %-3d | %-2d\n",
              p.processId(), p.arrivalTime(), p.burstTime(),
              completionTime, turnaroundTime, waitingTime);
    }

    // Calculates and print the overall averages
    int n = sortedProcesses.size();
    System.out.printf("\nAverage Turnaround Time: %.2f\n", (totalTAT / n));
    System.out.printf("Average Waiting Time: %.2f\n\n", (totalWT / n));
  }
}