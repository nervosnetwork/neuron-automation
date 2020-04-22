package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceivePage extends PageBase {

  @FindBy(xpath = "/html//navbar//button[@data-link='/receive']")
  public WebElement navigateReceive;
  // two buttons for qrcode
  @FindBy(css = "main#root div[class^='qrcode_actions'] button")
  public List<WebElement> qrcodeActionsButtons;
//  ChromeDriver driver;

  public ReceivePage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToReceivePage() {
    navigateReceive.click();
  }

  public void clickCopyImageButton() {
    qrcodeActionsButtons.get(0).click();
  }

  public void clickSaveImageButton() {
    qrcodeActionsButtons.get(1).click();
  }

}
