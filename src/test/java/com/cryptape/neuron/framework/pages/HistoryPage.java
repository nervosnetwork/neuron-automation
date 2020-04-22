package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HistoryPage extends PageBase {

  @FindBy(xpath = "/html//nav//button[@data-link='/history']")
  public WebElement navigateHistory;
  @FindBy(css = "div[class^='transactionList_summary']")
  public List<WebElement> transactionSummaryList;


  public HistoryPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToHistoryPage() {
    navigateHistory.click();
  }

}
