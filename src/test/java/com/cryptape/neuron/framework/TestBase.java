package com.cryptape.neuron.framework;

import com.cryptape.neuron.framework.utils.GitInfoReporter;
import com.cryptape.neuron.framework.utils.WaitUntil;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class TestBase {

  public static final ApplicationManager app = new ApplicationManager();
  public String ckbPath = app.getYMLValue("ckbPath");
  public String nodePath = app.getYMLValue("nodePath");

  @BeforeSuite
  public void globalSetup() throws AWTException {

    reporterLog();

//    // kill existed ckb process before suite
//    int findResult = runCommand("tasklist | findstr ckb");
//    if (findResult == 0) {
//      runCommand("taskkill /im ckb.exe -F");
//    }
//
//    // init ckb -f
//    String cmdInit = ckbPath + "ckb.exe init -c dev -C " + nodePath + " -f";
//    runCommand("\"" + cmdInit + "\"");
//    // delete data folder
//    String cmdDel = "rd/s/q " + (nodePath + "/data").replace("/", "\\");
//    runCommand("\"" + cmdDel + "\"");
//
//    String cmdCopyCKB = "copy " + ("resource/ckb.toml " + nodePath).replace("/", "\\") + " /Y";
//    runCommand("\"" + cmdCopyCKB + "\"");
//    String cmdCopyMiner = "copy " + ("resource/ckb-miner.toml " + nodePath).replace("/", "\\") + " /Y";
//    runCommand("\"" + cmdCopyMiner + "\"");
//    String cmdCopyDev = "copy " + ("resource/dev.toml " + nodePath + "/specs").replace("/", "\\") + " /Y";
//    runCommand("\"" + cmdCopyDev + "\"");
//
//    // run ckb node
//    String cmd2 = ckbPath + "ckb.exe run -C " + nodePath;
//    runCommand("start cmd.exe /K " + "\"" + cmd2 + "\"");
//
//    // miner for 12 blocks firstly
//    String cmdMiner = ckbPath + "ckb.exe miner -C " + nodePath + " --limit 12";
//    int minerResult = runCommand("\"" + cmdMiner + "\"");
//    if (minerResult != 0) {
//      try {
//        Thread.sleep(2000);
//        runCommand("start cmd.exe /K " + "\"" + cmdMiner + "\"");
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }

    app.init();
  }

  @AfterSuite
  public void teardown() throws IOException {
//    Runtime.getRuntime().exec("cmd /c taskkill /im ckb.exe -F");
    app.stop();
  }


  public int runCommand(String command) {
    Runtime run = Runtime.getRuntime();
    Process p = null;
    try {
//      p = run.exec("cmd /c " + command);
        p = run.exec(command);
      p.waitFor();
        InputStreamReader ir = new InputStreamReader(p.getInputStream());
        LineNumberReader input = new LineNumberReader(ir);      //创建IO管道，准备输出命令执行后的显示内容
        String line;
        while ((line = input.readLine ()) != null)
        {     //按行打印输出内容
            System.out.println(line);
        }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return p.exitValue(); // p.exitValue()==0 normal exit, >0 un-normal exit
  }

  public void reporterLog() {
    // display the log in the reporter
    Reporter.log("OS is: " + app.OS, true);
    GitInfoReporter gitInfoListener = new GitInfoReporter();
    gitInfoListener.reportGitInfo();
  }


  /**
   * recursive call WaitUnitl method in timeout
   * <i>waitFor(IntelligentWait wait, long timeOutInSecond, int pollingTimesInSecond)</i>
   *
   * @param timeOutInSecond Maximum waiting time in second
   * @param pollingTimesInSecond polling times in second
   */
  public boolean waitFor(WaitUntil wait, int timeOutInSecond, int pollingTimesInSecond) {
    int currentTime = 0;
    while (currentTime < timeOutInSecond) {
      if (wait.waitUntil()) {
        return true;
      }
      currentTime += pollingTimesInSecond;
      try {
        Thread.sleep(pollingTimesInSecond * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    return false;
  }

}
