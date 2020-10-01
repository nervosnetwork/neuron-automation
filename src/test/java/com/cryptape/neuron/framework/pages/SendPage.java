package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendPage extends PageBase {


  @FindBy(xpath = "/html//navbar//button[@data-link='/send']")
  public WebElement navigateSend;

  @FindBy(css = "div[class^='send_balance'] span[class^='balance_balanceValue']")
  public WebElement balance;

  @FindBy(css = "#address")
  public WebElement inputAddress;
  @FindBy(css = "#amount")
  public WebElement inputAmount;
  @FindBy(css = "button[title='Max']")
  public WebElement maxBtn;
  @FindBy(css = "img[alt='Add Output']")
  public List<WebElement> addBtnList;
  @FindBy(css = "img[data-type='remove']")
  public List<WebElement> removeBtnList;
  @FindBy(css = "button[data-type='set']")
  public List<WebElement> setLockTimeList;
  @FindBy(css = "#description")
  public WebElement inputDescription;
  @FindBy(css = "button[role='switch']")
  public WebElement switchAdvancedFeeSetting; //Advanced fee settings
  @FindBy(css = "#price")
  public WebElement inputPrice;
  @FindBy(css = "span[id^='Dropdown'][id$='option']") // Expected speed item, clickable
  public WebElement expectedSpeed;
  @FindBy(css = "button[role='option']")
  public List<WebElement> speedDropdownList; // Expected speed List
  @FindBy(css = "button[type='reset']")
  public WebElement clearBtn;
  @FindBy(css = "div[class^='send_actions'] button[type='submit']")
  public WebElement submitBtn;

  @FindBy(css = "div[class*='sendFieldset_addressField'] span[class^='textField_errorMessage']")
  public WebElement addressErrorMsg;
  @FindBy(css = "div[class^='sendFieldset_fullAddrInfo'] span")
  public WebElement fullAddressErrorMsg;

  public SendPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToSendPage() {
    navigateSend.click();
  }

  public void clickMaxButton() {
    try {
      maxBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 10, maxBtn).click();
    }
  }

  // add output(s)
  public void clickAddButton(int index) {
    try {
      addBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 10, addBtnList.get(index)).click();
    }
  }

  // remove output(s)
  public void clickRemoveButton(int index) {
    try {
      removeBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 10, removeBtnList.get(index)).click();
    }
  }

  public void clickSetLockTime(int index) {
    try {
      setLockTimeList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 10, setLockTimeList.get(index)).click();
    }
  }

  public void switchAdvancedTXfee() {
    try {
      switchAdvancedFeeSetting.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, switchAdvancedFeeSetting).click();
    }
  }

  public void clickClearButton() {
    clearBtn.click();
  }

  public void clickSubmitButton() {
    try {
      submitBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, submitBtn).click();
    }
  }

}
