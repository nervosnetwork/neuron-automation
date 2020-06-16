package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class BaseWalletOperations extends TestBase {

  public void createNewWallet(String walletName, String pwd) throws InterruptedException {
    app.createPage.clickCreateBtn();

    //Generate mnemonic
    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println(mnemonic);

    // click Next
    app.createPage.nextBtn.click();

    // repeat with the generated mnemonic
    app.createPage.inputMnemonic.sendKeys(mnemonic);

    // click Next
    app.createPage.nextBtn.click();

      Thread.sleep(1000);
    // input wallet name
//      for(int temp=0;temp<6;temp++){
//          app.createPage.inputWalletName.clear();
//          Thread.sleep(2000);
//          if(app.createPage.inputWalletName.getText()=="") break;
//      }
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(pwd);
    app.createPage.inputConfirmPassword.sendKeys(pwd);

    // click Next
    app.createPage.nextBtn.click();

  }

    public String importWallet(String walletName, String pwd) throws InterruptedException {

        String keystorePath = new File(
                System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
                .getAbsolutePath();

//    app.createPage.clickMenuImportKeystore();
//        app.createPage.importKeystoreBtn.click();

//        app.settingPage.navigateWalletName.click();
        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        app.settingPage.clickImportKeystoreButton();

        Thread.sleep(1000);
        app.createPage.inputWalletName.clear();
        app.createPage.inputWalletName.sendKeys(walletName);
        app.createPage.inputPasswordForImportKeystore.sendKeys(pwd);

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

        app.settingPage.backToMainWindow();

        String navigateWalletName = app.createPage.util
                .waitForElementLocated(app.createPage.driver, 30, app.createPage.navigateWalletName)
                .getText();
        return navigateWalletName;
    }
}
