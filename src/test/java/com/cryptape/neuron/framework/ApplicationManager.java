package com.cryptape.neuron.framework;

import com.cryptape.neuron.framework.pages.CreatePage;
import com.cryptape.neuron.framework.pages.DAOPage;
import com.cryptape.neuron.framework.pages.HistoryPage;
import com.cryptape.neuron.framework.pages.NetworkPage;
import com.cryptape.neuron.framework.pages.ReceivePage;
import com.cryptape.neuron.framework.pages.SendPage;
import com.cryptape.neuron.framework.pages.SettingPage;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.yaml.snakeyaml.Yaml;

public class ApplicationManager {

  public static ChromeOptions options = new ChromeOptions();
  public static ChromeDriver driver;
  public String OS = System.getProperty("os.name").toLowerCase();
  public CreatePage createPage;
  public DAOPage daoPage;
  public ReceivePage receivePage;
  public SettingPage settingPage;
  public NetworkPage networkPage;
  public SendPage sendPage;
  public HistoryPage historyPage;
  private String YAML_FILE = "./neuronConfig.yml";


  public void init() throws AWTException, InterruptedException {

    // sleep before start up for CI
    Thread.sleep(30000);

    options.setBinary(System.getenv("LOCALAPPDATA") + "/Programs/Neuron/Neuron.exe".replace("/", "\\"));
    System.setProperty("webdriver.chrome.driver", getYMLValue("chromedriverPath"));
    driver = new ChromeDriver(options);
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    createPage = new CreatePage(driver);
    daoPage = new DAOPage(driver);
    receivePage = new ReceivePage(driver);
    settingPage = new SettingPage(driver);
    networkPage = new NetworkPage(driver);
    sendPage = new SendPage(driver);
    historyPage = new HistoryPage(driver);
  }

  public void stop() {
    driver.quit();
  }


  public String getYMLValue(String key) {
    Map<String, Object> map = getYMLMap();
    String value = map.get(key).toString();
    return value;
  }

  public Map<String, Object> getYMLMap() {
    InputStream input;
    try {
      input = new FileInputStream(YAML_FILE);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return null;
    }
    Yaml yaml = new Yaml();
    Map<String, Object> object = yaml.load(input);
    return object;
  }
}
