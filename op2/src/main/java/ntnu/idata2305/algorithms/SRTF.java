package ntnu.idata2305.algorithms;

import ntnu.idata2305.CpuProcess;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SRTF implements Algorithm {

  private class ProcessState {

    CpuProcess process;
    int remainingTime;
    int completionTime;
    int turnaroundTime;
    int waitingTime;

    public ProcessState(CpuProcess process) {
      this.process = process;
      this.remainingTime = process.burstTime();
    }

    public String id() {
      return process.processId();
    }

    public int arrival() {
      return process.arrivalTime();
    }

    public int burst() {
      return process.burstTime();
    }
  }

  @Override
  public void run(List<CpuProcess> processes) {

    List<ProcessState> states = processes.stream()
      .map(ProcessState::new)
      .sorted(Comparator.comparingInt(ProcessState::arrival))
      .toList();
    List<ProcessState> working = new ArrayList<>(states);

    int time = 0;
    int completed = 0;

    List<String> gantt = new ArrayList<>();

    while (completed < working.size()) {
      ProcessState shortest = null;

      // Get process with shortest remaining time
      for (ProcessState p : working) {
        if (p.arrival() <= time && p.remainingTime > 0) {

          if (shortest == null ||
            p.remainingTime < shortest.remainingTime) {
            shortest = p;
          }
        }
      }

      // Skip if no process with a shortest time is found
      if (shortest == null) {
        time++;
        continue;
      }
      gantt.add(shortest.id());
      shortest.remainingTime--;

      // Calculations for a completed process
      if (shortest.remainingTime == 0) {
        completed++;

        shortest.completionTime = time + 1;
        shortest.turnaroundTime =
          shortest.completionTime - shortest.arrival();
        shortest.waitingTime =
          shortest.turnaroundTime - shortest.burst();
      }
      time++;
    }

    printGantt(gantt);
    printResults(states);
  }

  private void printResults(List<ProcessState> processes) {

    System.out.println("\nProcess ID | Arrival | Burst | CT | TAT | WT");

    double totalTAT = 0;
    double totalWT = 0;

    for (ProcessState p : processes) {

      System.out.printf(
        "%-10s | %-7d | %-5d | %-2d | %-3d | %-3d%n",
        p.id(),
        p.arrival(),
        p.burst(),
        p.completionTime,
        p.turnaroundTime,
        p.waitingTime);

      totalTAT += p.turnaroundTime;
      totalWT += p.waitingTime;
    }

    System.out.printf("\nAverage Turnaround Time: %.2f\n",
      totalTAT / processes.size());

    System.out.printf("Average Waiting Time: %.2f\n",
      totalWT / processes.size());
  }

  private void printGantt(List<String> gantt) {

    System.out.println("\nGantt Chart:");

    if (gantt.isEmpty()) return;

    List<String> segments = new ArrayList<>();
    List<Integer> times = new ArrayList<>();

    times.add(0);

    String prev = gantt.get(0);
    segments.add(prev);

    for (int i = 1; i < gantt.size(); i++) {
      String current = gantt.get(i);

      if (!current.equals(prev)) {
        segments.add(current);
        times.add(i);
        prev = current;
      }
    }

    times.add(gantt.size());

    // Process row
    for (String p : segments) {
      System.out.print("| " + p + " ");
    }
    System.out.println("|");

    // Time row
    for (Integer t : times) {
      System.out.printf("%-5d", t);
    }

    System.out.println();
  }
}