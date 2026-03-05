package main.java.ntnu.idata2305;

import main.java.ntnu.idata2305.algorithms.FCFS;

import java.util.List;

public class Application {
  public static void main(String[] args) {
    // Process example
    CpuProcess p1 = new CpuProcess("p1",0, 8);
    System.out.println(p1.toString());

    //
    FCFS algo = new FCFS();
    algo.run(List.of(
        new CpuProcess("P1", 0, 5),
        new CpuProcess("P2", 1, 3),
        new CpuProcess("P3", 2, 8)
    ));
  }
}
