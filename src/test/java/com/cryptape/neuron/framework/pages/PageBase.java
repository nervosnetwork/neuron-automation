package com.cryptape.neuron.framework.pages;

import com.cryptape.neuron.framework.TestBase;
import com.cryptape.neuron.framework.CommonUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PageBase extends TestBase {

  @FindBy(xpath = "//button[contains(@class, 'navbar_name')]")
  public WebElement navigateWalletName;

  // Password request window
  @FindBy(id = "password")
  public WebElement inputPWD;
  @FindBy(css = "div[class^='passwordRequest'] button[data-type='cancel']")
  public WebElement pwdCancelBtn;
  @FindBy(css = "div[class^='passwordRequest'] button[data-type='submit']")
  public WebElement pwdSubmitBtn;

  public ChromeDriver driver;
  public CommonUtil util = new CommonUtil();
  Robot robot = new Robot();

  public PageBase(ChromeDriver driver) throws AWTException {
    this.driver = driver;
  }

  // click cancel button on password request window
  public void clickPWDCancel() {
    pwdCancelBtn.click();
  }

  // click submit button on password request window
  public void clickPWDSubmit() {
    pwdSubmitBtn.click();
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

  public void keySlash() {
    robot.delay(200);
    robot.keyPress(KeyEvent.VK_SLASH);
    robot.keyRelease(KeyEvent.VK_SLASH);
  }

  public void keyAlt() {
    robot.delay(200);
    robot.keyPress(KeyEvent.VK_ALT);
    robot.keyRelease(KeyEvent.VK_ALT);
  }

    public void keyCommandV() {
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_META);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);
    }

    public void keyCommandA() {
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_META);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_A);
    }

    public void keyCommandW() {
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_META);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_W);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_W);
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


    public void clickButtonCreateWallet() {
        if (app.createPage.util.isElementPresent(app.settingPage.createWalletBtn)) {
            app.settingPage.navigateToSettingPage1();
            app.settingPage.clickWalletsTab();
            app.settingPage.clickCreateButton();
        }else if (app.createPage.util.isElementPresent(app.settingPage.navigateWalletName)) {
            app.settingPage.navigateWalletName.click();
            app.settingPage.navigateToSettingPage1();
            app.settingPage.clickWalletsTab();
            app.settingPage.clickCreateButton();
        } else if (app.createPage.util.isElementPresent(app.createPage.createBtn)) {
            app.createPage.clickCreateBtn();
        }
    }


  public void rightClick(ChromeDriver driver, WebElement webElement) {
    Actions actions = new Actions(driver);
    actions.contextClick(webElement).perform();
  }

  public void doubleClick(ChromeDriver driver, WebElement webElement) {
    Actions actions = new Actions(driver);
    actions.doubleClick(webElement).build().perform();
  }

    public void mouseover(ChromeDriver driver, WebElement webElement){
        Actions action = new Actions(driver);
        action.moveToElement(webElement).perform();
    }
}
