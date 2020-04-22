package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingPage extends PageBase {

  @FindBy(xpath = "/html//navbar//button[@data-link='/settings/general']")
  public WebElement navigateSetting;
  @FindBy(css = "button[id$='Tab0'")
  public WebElement generalTab;
  @FindBy(css = "button[id$='Tab1'") // match by end of name
  public WebElement walletsTab;
  @FindBy(css = "button[id$='Tab2'")
  public WebElement networkTab;
  @FindBy(css = ".ms-ChoiceFieldLabel")
  public List<WebElement> walletList;
  @FindBy(css = "span[class='ms-ChoiceFieldLabel']")
  public List<WebElement> networkNameList;
  @FindBy(css = "div[class^='networkSetting_network']>span:nth-of-type(2)")
  public List<WebElement> networkURLList;
  @FindBy(css = "span[class='label third']")
  public List<WebElement> networkTagList;

  // WalletsTab
  @FindBy(css = "button[arial-label='create a wallet']")
  public WebElement createWalletBtn;
  @FindBy(css = "button[arial-label='import wallet seed']")
  public WebElement importMnemonicBtn;
  @FindBy(css = "button[arial-label='import from keystore']")
  public WebElement importKeystoreBtn;

  // EditWalletPage
  @FindBy(css = "main#root input")
  public WebElement editWalletName;

  @FindBy(css = "div[class^='networkSetting_actions']>button")
  public WebElement addNetworkBtn;

  // ImportMemonic page
  @FindBy(xpath = "//textarea")
  public WebElement generateMnemonic;
  @FindBy(xpath = "//button[@data-type='submit']")
  public WebElement nextBtn;
  @FindBy(xpath = "//textarea")
  public WebElement inputMnemonic;
  @FindBy(id = "name")
  public WebElement inputWalletName;
  @FindBy(id = "password")
  public WebElement inputPassword;
  @FindBy(id = "confirmPassword")
  public WebElement inputConfirmPassword;

  @FindBy(xpath = "//button[@data-type='submit']")
  public WebElement saveBtn;
  @FindBy(xpath = "//button[@data-type='cancel']")
  public WebElement cancelBtn;

  @FindBy(id = "password")
  public WebElement inputPasswordForDeleteWallet;

  @FindBy(css = "#password")
  public WebElement inputPasswordForBackup;


  public SettingPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToSettingPage() {
    try {
      navigateSetting.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 30, navigateSetting).click();
    }
  }

  public void clickWalletsTab() {
    walletsTab.click();
  }

  public void clickNetworkTab() {
    networkTab.click();
  }

  public void clickSaveButton() {
    saveBtn.click();
  }

  public void clickCancelButton() {
    cancelBtn.click();
  }

  public void clickCreateButton() {
    createWalletBtn.click();
  }

  public void clickImportMnemonicButton() {
    importMnemonicBtn.click();
  }

  public void clickImportMnemonicbUTTON() {
    importMnemonicBtn.click();
  }

  public void clickImportKeystoreButton() {
    importKeystoreBtn.click();
  }

  public void clickAddNetworkButton() {
    addNetworkBtn.click();
  }

  public String getNetworkNameText(int index) {
    try {
      return networkNameList.get(index).getText();
    } catch (Exception e) {
      return util.waitForElementLocated(this.driver, 30, networkNameList.get(index)).getText();
    }
  }

  public String getNetworkURLText(int index) {
    try {
      return networkURLList.get(index).getText();
    } catch (Exception e) {
      return util.waitForElementLocated(this.driver, 30, networkURLList.get(index)).getText();
    }
  }

//  public void clickSubmitBtn(){
//    nextBtn.click();
//  }

  public void clickBackupWalletFromContext() {
    keyDown();
    keyDown();
    keyEnter();
  }

  public void clickEditWalletFromContext() {
    keyDown();
    keyDown();
    keyDown();
    keyEnter();
  }

  public void clickDeleteWalletFromContext() {
    keyDown();
    keyDown();
    keyDown();
    keyDown();
    keyEnter();
  }

  public void clickEditNetworkFromContext() {
    keyDown();
    keyDown();
    keyEnter();
  }

}
