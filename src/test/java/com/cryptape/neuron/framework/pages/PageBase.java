package com.cryptape.neuron.framework.pages;

import com.cryptape.neuron.framework.CommonUtil;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class PageBase {

  @FindBy(xpath = "//button[contains(@class, 'navbar_name')]")
  public WebElement navigateWalletName;

  // Password request window
  @FindBy(id = "password")
  public WebElement inputPWD;
  @FindBy(css = "div[class^='passwordRequest'] button[data-type='cancel']")
  public WebElement pwdCancelBtn;
  @FindBy(css = "div[class^='passwordRequest'] button[data-type='submit']")
  public WebElement pwdSubmitBtn;
  @FindBy(css = "span[class^='networkStatus_name']")
  public WebElement networkStatus;
  @FindBy(css = "span[class^='networkStatus_blockNumber']")
  public WebElement networkStatusBlockNum;
  String blockNum;

  public ChromeDriver driver;
  public CommonUtil util = new CommonUtil();
  Robot robot = new Robot();

  public PageBase(ChromeDriver driver) throws AWTException {
    this.driver = driver;
  }

  public String getNetStatusTipBlockNum() {
    blockNum = networkStatusBlockNum.getText();
    return blockNum.substring(blockNum.indexOf("/") + 1);
  }

  public String getNetStatusSyncedBlockNum() {
    blockNum = networkStatusBlockNum.getText();
    return blockNum.substring(0, blockNum.indexOf("/"));
  }

  // click cancel button on password request window
  public void clickPWDCancel() {
    pwdCancelBtn.click();
  }

  // click submit button on password request window
  public void clickPWDSubmit() {
    try {
      pwdSubmitBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, pwdSubmitBtn).click();
    }
  }

  public void keyDown() {
    robot.delay(200);
    robot.keyPress(KeyEvent.VK_DOWN);
    robot.keyRelease(KeyEvent.VK_DOWN);
  }

  public void keyEnter() {
    robot.delay(200);
    robot.keyPress(KeyEvent.VK_ENTER);
    robot.keyRelease(KeyEvent.VK_ENTER);
  }

  public void keyAlt() {
    robot.delay(200);
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_ALT);
  }

  public void keyCtrlV() {
    robot.delay(500);
    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.delay(500);
    robot.keyPress(KeyEvent.VK_V);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_V);
  }

  public void clickMenuImportMnemonic() {
    keyAlt();
    keyEnter();
    keyDown();
    keyDown();
    keyDown();
    robot.keyPress(KeyEvent.VK_RIGHT);
    keyEnter();
  }

  public void clickMenuImportKeystore() {
    keyAlt();
    keyEnter();
    keyDown();
    keyDown();
    keyDown();
    robot.keyPress(KeyEvent.VK_RIGHT);
    keyDown();
    keyEnter();
  }

  public void clickMenuCreateWallet() {
    keyAlt();
    keyEnter();
    keyDown();
    keyDown();
    keyEnter();
  }

  public void rightClick(ChromeDriver driver, WebElement webElement) {
    Actions actions = new Actions(driver);
    actions.contextClick(webElement).perform();
  }

  public void doubleClick(ChromeDriver driver, WebElement webElement) {
    Actions actions = new Actions(driver);
    actions.doubleClick(webElement).build().perform();
  }

  public void mouseover(ChromeDriver driver, WebElement webElement) {
    Actions action = new Actions(driver);
    action.moveToElement(webElement).perform();
  }

}
