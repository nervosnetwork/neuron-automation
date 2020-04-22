package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NetworkPage extends PageBase {

  @FindBy(id = "url")
  public WebElement inputRPCURL;
  @FindBy(id = "name")
  public WebElement inputNetworkName;


  public NetworkPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }


}
