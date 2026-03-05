package main.java.ntnu.idata2305;

public record CpuProcess(String processId, int arrivalTime, int burstTime) {

  @Override
  public String toString() {
    return String.format("%s[arrival=%d, burst=%d]", processId, arrivalTime, burstTime);
  }
}
