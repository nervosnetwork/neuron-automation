package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DAOPage extends PageBase {

  @FindBy(xpath = "/html//navbar//button[@aria-label='Nervos DAO']")
  public WebElement navigateDAO;

  public DAOPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToDAOPage() {
    navigateDAO.click();
    try {
      navigateDAO.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, navigateDAO).click();
    }
  }

}
