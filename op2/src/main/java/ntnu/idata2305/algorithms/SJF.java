package main.java.ntnu.idata2305.algorithms;

import main.java.ntnu.idata2305.CpuProcess;

import java.util.List;

public class SJF implements Algorithm {

  public void run(List<CpuProcess> processes){
    System.out.println("Metrics to compute:");
    System.out.println("- Completion Time (CT)");
    System.out.println("- Waiting Time (WT)");
    System.out.println("- Turnaround Time (TAT)");
    System.out.println("- Average Waiting Time");
    System.out.println("- Average Turnaround Time");
  }
}
