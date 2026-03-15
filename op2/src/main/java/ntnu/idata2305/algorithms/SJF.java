package ntnu.idata2305.algorithms;

import ntnu.idata2305.CpuProcess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF implements Algorithm {

  public void run(List<CpuProcess> processes){
    List<ProcessState> states = processes.stream()
      .map(ProcessState::new)
      .sorted(Comparator.comparingInt(ProcessState::arrival)
        .thenComparingInt(ProcessState::burst)
      ).toList();

    List<ProcessState> working = new ArrayList<>(states);

    System.out.println("--- Shortest Job First (SJF) ---");
    System.out.println("Process ID | Arrival | Burst | CT | TAT | WT");

    int time = 0;
    int totalWT = 0;
    int totalTAT = 0;

    ProcessState currentState = working.getFirst();
    while (currentState != null){
      //Calculating parameters
      time = Math.max(time, currentState.arrival()); // Edge case for when process may not have arrived yet
      currentState.completionTime = time + currentState.burst();
      currentState.turnaroundTime = currentState.completionTime - currentState.arrival();
      currentState.waitingTime = currentState.turnaroundTime - currentState.burst();
      totalWT += currentState.waitingTime;
      totalTAT += currentState.turnaroundTime;

      // Displays the results for this specific process
      System.out.printf("%-10s | %-7d | %-5d | %-2d | %-3d | %-2d\n",
        currentState.process.processId(), currentState.arrival(), currentState.burst(),
        currentState.completionTime, currentState.turnaroundTime, currentState.waitingTime);

      //Updating params
      time = currentState.completionTime;
      working.remove(currentState);

      //Finding new current state
      int finalTime = time;
      currentState = working.stream()
        .filter(p -> p.arrival() <= finalTime)
        .min(Comparator.comparingInt(ProcessState::burst))
        .orElseGet(() ->
          working.stream()
            .min(Comparator.comparingInt(ProcessState::arrival))
            .orElse(null)
        );

      if (currentState != null && currentState.arrival() > time) {
        time = currentState.arrival();
    }
  }
    int n = processes.size();
    double avgWT = (double) totalWT / n;
    double avgTAT = (double) totalTAT / n;

    System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT);
    System.out.printf("Average Waiting Time: %.2f\n", avgWT);
}
}
