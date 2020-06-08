package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class ImportWalletTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testImportMnemonicFromSetting() {

    String importMnemonic = "crew assume asset trip trim violin exhaust used bird slow universe jealous";
    String walletName = "钱包ImportTest1";
    String pwd = "Aa111111";

    app.receivePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickWalletsTab();
    app.settingPage.clickImportMnemonicbUTTON();

    app.settingPage.inputMnemonic.sendKeys(importMnemonic);
    app.settingPage.nextBtn.click();

    app.receivePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);
    String importWalletName = app.createPage.inputWalletName.getAttribute("value");

    app.settingPage.inputPassword.sendKeys(pwd);
    app.settingPage.inputConfirmPassword.sendKeys(pwd);

    // click Next
    app.settingPage.clickSaveButton();

    Assert.assertEquals(app.createPage.navigateWalletName.getText(), importWalletName);

  }


  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testImportKeystoreFromMenu() throws InterruptedException {
    String walletName = "importWalletKeystore";
    String pwd = "Aa111111";
    String keystorePath = new File(
        System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
        .getAbsolutePath();

//    app.createPage.clickMenuImportKeystore();

      app.receivePage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

      app.settingPage.navigateWalletName.click();
      app.settingPage.clickWalletsTab();
      app.settingPage.clickImportKeystoreButton();


    Thread.sleep(1000);
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);
    app.createPage.inputPasswordForImportKeystore.sendKeys(pwd);

//      app.createPage.inputPathOfKeystore.sendKeys(keystorePath);
      app.createPage.inputPathOfKeystore.click();

    StringSelection stringSelection = new StringSelection(keystorePath);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    app.createPage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

      app.createPage.keySlash();
      app.createPage.keyCommandA();
      app.createPage.keyCommandV();
      app.createPage.keyEnter();
      Thread.sleep(2000);
      app.createPage.keyEnter();

    Thread.sleep(2000);

    app.createPage.clickSubmitBtn();

    String navigateWalletName = app.createPage.util
        .waitForElementLocated(app.createPage.driver, 30, app.createPage.navigateWalletName)
        .getText();
    Assert.assertEquals(navigateWalletName, walletName);

  }

}
