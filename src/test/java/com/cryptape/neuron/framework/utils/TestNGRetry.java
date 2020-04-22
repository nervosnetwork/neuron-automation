package com.cryptape.neuron.framework.utils;

import java.util.Arrays;
import java.util.List;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.collections.Lists;

public class TestNGRetry implements IRetryAnalyzer {

  private static final int MAX_RETRY_COUNT = 3;
  public static List<String> logs = Lists.newArrayList();
  private int retryCount = 1;

  private static String prettyMsg(ITestResult result) {
    return "Test method : " + result.getTestClass().getRealClass().getName() + "." +
        result.getMethod().getMethodName() + "()" +
        ", Parameters : " + Arrays.toString(result.getParameters());
  }

  @Override
  public boolean retry(ITestResult iTestResult) {
    String prefix = "Attempt #" + retryCount;
    if (retryCount <= MAX_RETRY_COUNT) {
      if (iTestResult.getParameters().length > 0) {
        logs.add(prefix + ". Retry :true  " + prettyMsg(iTestResult));
      }

      System.out.println(
          "it's the " + retryCount + " times retry. And the current case is " + iTestResult
              .getName());
      retryCount++;
      return true;
    }
    return false;
  }

  public void reSetCount() {
    retryCount = 1;
    logs.clear();
  }
}
