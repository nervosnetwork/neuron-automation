package com.cryptape.neuron.framework.pages;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingPage extends PageBase {

  @FindBy(css = "button[id$='Tab0'")
  public WebElement generalTab;
  @FindBy(css = "button[id$='Tab1'") // match by end of name
  public WebElement walletsTab;
  @FindBy(css = "button[id$='Tab2'")
  public WebElement networkTab;
  @FindBy(css = ".ms-ChoiceFieldLabel")
  public List<WebElement> walletList;
  @FindBy(css = "span[class^='networkSetting_networkLabel']")
  public List<WebElement> networkNameList;
  @FindBy(css = "span[class^='networkSetting_networkLabel']>span:nth-of-type(1)")
  public List<WebElement> networkURLList;
  @FindBy(css = "span[class='label third']")
  public List<WebElement> networkTagList;

  // GeneralTab
  @FindBy(css = "span[class^='ms-Dropdown-title']")
  public WebElement language;

  // WalletsTab
  @FindBy(css = "button[arial-label='create a wallet']")
  public WebElement createWalletBtn;
  @FindBy(css = "button[arial-label='import wallet seed']")
  public WebElement importMnemonicBtn;
  @FindBy(css = "button[arial-label='import from keystore']")
  public WebElement importKeystoreBtn;

  // edit/delete/backup wallet buttons
  @FindBy(css = "button[data-action='edit']")
  public List<WebElement> editWalletBtnList;
  @FindBy(css = "button[data-action='delete']")
  public List<WebElement> deleteWalletBtnList;
  @FindBy(css = "button[data-action='backup']")
  public List<WebElement> backupWalletBtnList;


  // edit/delete/backup network buttons
  @FindBy(css = "button[data-action='edit']")
  public List<WebElement> editNetworkBtnList;
  @FindBy(css = "button[data-action='delete']")
  public List<WebElement> deleteNetworkBtnList;

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
  @FindBy(css = "span[class^='textField_errorMessage']")
  public WebElement msgForWrongPassword;

  @FindBy(css = "#password")
  public WebElement inputPasswordForBackup;


  public SettingPage(ChromeDriver driver) throws AWTException {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void navigateToSettingPage() {
    try {
      backToMainWindow();
      navigateWalletName.click();
      Set winHandles = driver.getWindowHandles();
      int size = winHandles.size();
      ArrayList windows = new ArrayList(winHandles);
      for (int i = 0; i < size; i++) {
        driver.switchTo().window((String) windows.get(i));
        if (!driver.getTitle().equals("Neuron") && !driver.getTitle().isEmpty()) break;
      }
      Thread.sleep(1000);
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, navigateWalletName).click();
    }
  }

  public void backToMainWindow() {
    try {
      Set winHandles = driver.getWindowHandles();
      if (winHandles.size() >= 1) {
        driver.getWindowHandle();
        if (!driver.getTitle().equals("Neuron") && !driver.getTitle().isEmpty()) {
          driver.close();
        }

        ArrayList windows = new ArrayList(winHandles);
        driver.switchTo().window((String) windows.get(0));
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void clickGeneralTab() {
    try {
      generalTab.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, generalTab).click();
    }
  }

  public void clickWalletsTab() {
    try {
      walletsTab.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, walletsTab).click();
    }
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
    try {
      createWalletBtn.click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, createWalletBtn).click();
    }
  }

  public void clickImportMnemonicButton() {
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
      return util.waitForElementLocated(this.driver, 15, networkNameList.get(index)).getText();
    }
  }

  public String getNetworkURLText(int index) {
    try {
      return networkURLList.get(index).getText();
    } catch (Exception e) {
      return util.waitForElementLocated(this.driver, 15, networkURLList.get(index)).getText();
    }
  }

//  public void clickSubmitBtn(){
//    nextBtn.click();
//  }

  public void clickEditWallet(int index) {
    try {
      editWalletBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, editWalletBtnList.get(index)).click();
    }
  }

  public void clickDeleteWallet(int index) {
    try {
      deleteWalletBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, deleteWalletBtnList.get(index)).click();
    }
  }

  public void clickBackupWallet(int index) {
    try {
      backupWalletBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, backupWalletBtnList.get(index)).click();
    }
  }

  public void clickEditNetwork(int index) {
    try {
      editNetworkBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 15, editNetworkBtnList.get(index)).click();
    }
  }

  public void clickDeleteNetwork(int index) {
    try {
      deleteNetworkBtnList.get(index).click();
    } catch (Exception e) {
      util.waitForElementLocated(this.driver, 10, deleteNetworkBtnList.get(index)).click();
    }
  }

}
