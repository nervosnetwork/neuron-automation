package com.cryptape.neuron.framework.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitInfoReporter implements ISuiteListener {

  public void reportGitInfo() {
    reportLog("git rev-parse --short HEAD", "The current git commit id is: ");
  }

  /**
   * Report the info
   */
  public void reportLog(String cmd, String logTitle) {
    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    try {
      process = runtime.exec(cmd);
    } catch (IOException e) {
      e.printStackTrace();
    }
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())
    )) {
      String result = reader.readLine();
      Reporter.log(logTitle + result, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    @Override
    public void onStart(ISuite suite) {

    }

    @Override
    public void onFinish(ISuite suite) {

    }
}
