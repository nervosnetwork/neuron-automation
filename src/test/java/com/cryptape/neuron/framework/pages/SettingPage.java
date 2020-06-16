package com.cryptape.neuron.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
//  @FindBy(css = "span[class='ms-ChoiceFieldLabel']")
  @FindBy(css = ".ms-ChoiceFieldLabel")
  public List<WebElement> networkNameList;
//  @FindBy(css = "div[class^='networkSetting_network']>span:nth-of-type(2)")
  @FindBy(css = ".ms-ChoiceFieldLabel")
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
          Thread.sleep(3000);
          Set winHandles = driver.getWindowHandles();
          ArrayList windows = new ArrayList(winHandles);
          if (windows.size()>2) {
              driver.switchTo().window((String) windows.get(2));
          }else {
              driver.switchTo().window((String) windows.get(1));
          }
          Thread.sleep(3000);

      } catch (Exception e) {
          util.waitForElementLocated(this.driver, 30, navigateWalletName).click();
      }
  }


    public void navigateToSettingPage0() {
        try {
            backToMainWindow();
            navigateWalletName.click();
            Thread.sleep(3000);
            Set winHandles = driver.getWindowHandles();
            ArrayList windows = new ArrayList(winHandles);
            driver.switchTo().window((String) windows.get(0));
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, navigateWalletName).click();
        }
    }

    public void navigateToSettingPage1() {
        try {
            backToMainWindow();
            navigateWalletName.click();
            Thread.sleep(3000);
            Set winHandles = driver.getWindowHandles();
            ArrayList windows = new ArrayList(winHandles);
            driver.switchTo().window((String) windows.get(1));
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, navigateWalletName).click();
        }
    }
    public int getwinHandles() {
            Set winHandles = driver.getWindowHandles();
            return winHandles.size();
    }

    public void goToMainWindow() {
        try {
            int sizeWin = 0;
            Thread.sleep(2000);
            for (int i = 0; i < 6; i++) {
                sizeWin = getwinHandles();
                if (sizeWin == 2) {
                    break;
                }
                backToMainWindow();
                Thread.sleep(2000);
                app.settingPage.keyCommandW();
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void backToMainWindow() {
        try {
            Set winHandles = driver.getWindowHandles();
            if(winHandles.size() > 1) {
                ArrayList windows = new ArrayList(winHandles);
                driver.switchTo().window((String) windows.get(0));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToTopWindow() {
        try {
            Set winHandles = driver.getWindowHandles();
            if(winHandles.size() > 1) {
                ArrayList windows = new ArrayList(winHandles);
                driver.switchTo().window((String) windows.get(winHandles.size()-1));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void clickWalletsTab() {walletsTab.click();}

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

    public void clickEditWallet(int index){
        try{
            editWalletBtnList.get(index).click();
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, editWalletBtnList.get(index));
        }
    }

    public void clickDeleteWallet(int index){
        try{
            deleteWalletBtnList.get(index).click();
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, deleteWalletBtnList.get(index));
        }
    }

    public void clickBackupWallet(int index){
        try{
            backupWalletBtnList.get(index).click();
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, backupWalletBtnList.get(index));
        }
    }

    public void clickEditNetwork(int index){
        try{
            editNetworkBtnList.get(index).click();
        } catch (Exception e) {
            util.waitForElementLocated(this.driver, 30, editNetworkBtnList.get(index));
        }
    }

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
