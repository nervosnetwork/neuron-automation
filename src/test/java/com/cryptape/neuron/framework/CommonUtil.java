package com.cryptape.neuron.framework;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtil {

  /**
   * wait for Element Located in seconds
   *
   * @param timeOut time Out In Seconds
   * @param by object By
   */
  public WebElement waitForElementLocated(WebDriver driver, int timeOut, WebElement webElement) {
    return new WebDriverWait(driver, timeOut).until(
        ExpectedConditions.visibilityOf(webElement));
  }

  public boolean isElementPresent(WebElement webElement) {
    try {
      webElement.isDisplayed();
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
