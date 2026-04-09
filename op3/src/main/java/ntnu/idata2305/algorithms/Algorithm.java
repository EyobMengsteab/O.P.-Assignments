package ntnu.idata2305.algorithms;

import ntnu.idata2305.types.RequestInput;
import ntnu.idata2305.types.RequestResult;

public interface Algorithm {
  RequestResult run(RequestInput input);
}

