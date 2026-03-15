package ntnu.idata2305;

import ntnu.idata2305.algorithms.Algorithm;
import ntnu.idata2305.algorithms.FCFS;
import ntnu.idata2305.algorithms.SJF;
import ntnu.idata2305.algorithms.SRTF;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {

  private static final String RESET = "\u001B[0m";
  private static final String GREEN = "\u001B[32m";
  private static final String YELLOW = "\u001B[33m";
  private static final String BLUE = "\u001B[34m";

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

    Algorithm fcfs = new FCFS();
    Algorithm sjf = new SJF();
    Algorithm srtf = new SRTF();

    System.out.println("=================================================");
    System.out.println("CPU SCHEDULING COMPARISON TABLE");
    System.out.println("=================================================");
    System.out.printf("%-8s %-10s %-10s %-10s%n", "Case", "Algorithm", "Avg WT", "Avg TAT");
    System.out.println("-------------------------------------------------");

    printRow("Case 1", "FCFS", fcfs, case1Processes);
    printRow("Case 1", "SJF",  sjf,  case1Processes);
    printRow("Case 1", "SRTF", srtf, case1Processes);

    System.out.println("-------------------------------------------------");

    printRow("Case 2", "FCFS", fcfs, case2Processes);
    printRow("Case 2", "SJF",  sjf,  case2Processes);
    printRow("Case 2", "SRTF", srtf, case2Processes);

    System.out.println("-------------------------------------------------");

    printRow("Case 3", "FCFS", fcfs, case3Processes);
    printRow("Case 3", "SJF",  sjf,  case3Processes);
    printRow("Case 3", "SRTF", srtf, case3Processes);

    System.out.println("-------------------------------------------------");
  }

  private static void printRow(String caseName, String algorithmName,
                               Algorithm algorithm, List<CpuProcess> processes) {
    Result result = runAndExtractResults(algorithm, processes);

    String color = switch (caseName) {
      case "Case 1" -> GREEN;
      case "Case 2" -> YELLOW;
      case "Case 3" -> BLUE;
      default -> RESET;
    };


    System.out.printf(color + "%-8s %-10s %-10.2f %-10.2f" + RESET + "%n",
      caseName, algorithmName, result.avgWT(), result.avgTAT());
  }

  private static Result runAndExtractResults(Algorithm algorithm, List<CpuProcess> processes) {
    PrintStream originalOut = System.out;
    ByteArrayOutputStream capturedOutput = new ByteArrayOutputStream();
    PrintStream tempOut = new PrintStream(capturedOutput);

    System.setOut(tempOut);
    algorithm.run(processes);
    System.out.flush();
    System.setOut(originalOut);

    String output = capturedOutput.toString();

    double avgTAT = extractValue(output, "Average Turnaround Time:\\s*([0-9]+\\.?[0-9]*)");
    double avgWT = extractValue(output, "Average Waiting Time:\\s*([0-9]+\\.?[0-9]*)");

    return new Result(avgWT, avgTAT);
  }

  private static double extractValue(String text, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    if (matcher.find()) {
      return Double.parseDouble(matcher.group(1));
    }

    throw new IllegalStateException("Could not find value using pattern: " + regex);
  }

  private record Result(double avgWT, double avgTAT) {}
}