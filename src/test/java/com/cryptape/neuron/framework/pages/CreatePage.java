package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatePage extends PageBase {

  @FindBy(css = "span[class^='walletWizard_slogan']")
  public WebElement welcomeSlogan;

  @FindBy(css = "button[data-type='primary']")
  public WebElement dismissBtn;

  @FindBy(css = "div[class^='walletWizard_actions']>button:nth-of-type(1)")
  public WebElement createBtn;

  @FindBy(css = "div[class^='walletWizard_actions']>button:nth-of-type(2)")
  public WebElement importMnemonicBtn;
  @FindBy(css = "div[class^='walletWizard_actions']>button:nth-of-type(3)")
  public WebElement importKeystoreBtn;
  @FindBy(css = "div[class^='walletWizard_actions']>button:nth-of-type(4)")
  public WebElement importHardwareWalletBtn;

  // ImportKeystore page
  @FindBy(css = "#path")
  public WebElement inputPathOfKeystore;
  @FindBy(css = "#password")
  public WebElement inputPasswordForImportKeystore;

  // Import or Generate Mnemonic page
  @FindBy(xpath = "//textarea")
  public WebElement generateMnemonic;
  @FindBy(xpath = "//button[@data-type='submit']")
  public WebElement nextBtn;
  @FindBy(xpath = "//button[@data-type='cancel']")
  public WebElement backBtn;
  @FindBy(xpath = "//textarea")
  public WebElement inputMnemonic;
  @FindBy(id = "name")
  public WebElement inputWalletName;
  @FindBy(id = "password")
  public WebElement inputPassword;
  @FindBy(id = "confirmPassword")
  public WebElement inputConfirmPassword;

  @FindBy(xpath = "//button[@data-type='submit']")
  public WebElement submitBtn;


  public CreatePage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }


  public void clickCreateBtn() {
    createBtn.click();
  }

  public void clickSubmitBtn() {
    try {
      submitBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, submitBtn).click();
    }
  }

  public void clickDismissBtn() {
    try {
      dismissBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, dismissBtn).click();
    }
  }


}
