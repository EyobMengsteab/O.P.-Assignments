package ntnu.idata2305.algorithms;

import ntnu.idata2305.CpuProcess;

import java.util.List;

public interface Algorithm {
  void run(List<CpuProcess> process);
}
