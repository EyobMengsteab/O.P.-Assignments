package ntnu.idata2305.algorithms;

import ntnu.idata2305.CpuProcess;

public class ProcessState {
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
