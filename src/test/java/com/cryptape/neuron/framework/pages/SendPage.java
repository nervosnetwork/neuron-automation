package com.cryptape.neuron.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.List;

public class SendPage extends PageBase {


  @FindBy(xpath = "/html//navbar//button[@data-link='/send']")
  public WebElement navigateSend;

  @FindBy(css = "div[class^='send_balance'] span[class^='send_balanceValue']")
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
      util.waitForElementLocated(this.driver, 30, maxBtn).click();
    }
  }

  // add output(s)
  public void clickAddButton(int index) {
    try {
      addBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, addBtnList.get(index)).click();
    }
  }

  // remove output(s)
  public void clickRemoveButton(int index) {
    try {
      removeBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, removeBtnList.get(index)).click();
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
    submitBtn.click();
  }

}
