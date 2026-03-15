package ntnu.idata2305;

import ntnu.idata2305.algorithms.Algorithm;
import ntnu.idata2305.algorithms.FCFS;
import ntnu.idata2305.algorithms.SJF;
import ntnu.idata2305.algorithms.SRTF;

import java.util.List;

public class Application {

  // ANSI Colors
  public static final String RESET = "\u001B[0m";
  public static final String RED = "\u001B[31m";
  public static final String GREEN = "\u001B[32m";
  public static final String YELLOW = "\u001B[33m";
  public static final String BLUE = "\u001B[34m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";

  public static void main(String[] args) {

    List<CpuProcess> case1Processes = List.of(
      new CpuProcess("P1", 0, 8),
      new CpuProcess("P2", 0, 4),
      new CpuProcess("P3", 0, 2),
      new CpuProcess("P4", 0, 6),
      new CpuProcess("P5", 0, 3)
    );

    List<CpuProcess> case2Processes = List.of(
      new CpuProcess("P1", 0, 20),
      new CpuProcess("P2", 1, 2),
      new CpuProcess("P3", 2, 2),
      new CpuProcess("P4", 3, 1),
      new CpuProcess("P5", 4, 3)
    );

    List<CpuProcess> case3Processes = List.of(
      new CpuProcess("P1", 0, 20),
      new CpuProcess("P2", 1, 2),
      new CpuProcess("P3", 2, 2),
      new CpuProcess("P4", 3, 2),
      new CpuProcess("P5", 4, 2),
      new CpuProcess("P6", 5, 2)
    );

    Algorithm fcfs = new FCFS();
    Algorithm sjf = new SJF();
    Algorithm srtf = new SRTF();

    runCase("CASE 1 - All Processes Arrive at Time 0",
      "Compare burst ordering behavior",
      case1Processes, fcfs, sjf, srtf);

    runCase("CASE 2 - One Long Process Followed by Short Processes",
      "Demonstrates convoy effect",
      case2Processes, fcfs, sjf, srtf);

    runCase("CASE 3 - Continuous Arrival of Short Processes",
      "Shows starvation risk",
      case3Processes, fcfs, sjf, srtf);
  }

  private static void runCase(String title, String purpose,
                              List<CpuProcess> processes,
                              Algorithm fcfs, Algorithm sjf, Algorithm srtf) {

    printDivider();

    System.out.println(PURPLE + title + RESET);
    System.out.println(YELLOW + "Purpose: " + purpose + RESET);

    printDivider();

    printInputTable(processes);

    System.out.println("\n" + GREEN + ">>> Running FCFS" + RESET);
    fcfs.run(processes);

    System.out.println("\n" + GREEN + ">>> Running SJF (Non-preemptive)" + RESET);
    sjf.run(processes);

    System.out.println("\n" + GREEN + ">>> Running SRTF (Preemptive)" + RESET);
    srtf.run(processes);

    System.out.println(BLUE + "\nFinished " + title + RESET);
  }

  private static void printInputTable(List<CpuProcess> processes) {
    System.out.println(BLUE + "\nInput Processes:" + RESET);
    System.out.println("Process ID | Arrival | Burst");

    for (CpuProcess p : processes) {
      System.out.printf("%-10s | %-7d | %-5d%n",
        p.processId(), p.arrivalTime(), p.burstTime());
    }
  }

  private static void printDivider() {
    System.out.println(BLUE + "============================================================" + RESET);
  }
}