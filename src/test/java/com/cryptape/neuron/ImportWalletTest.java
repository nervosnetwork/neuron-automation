package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ImportWalletTest extends TestBase {

    BaseWalletOperations BWO = new BaseWalletOperations();

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testImportMnemonicFromSetting() {

    String importMnemonic = "crew assume asset trip trim violin exhaust used bird slow universe jealous";
    String walletName = "钱包ImportTest1";
    String pwd = "Aa111111";

      app.settingPage.goToMainWindow();
      app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
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

    app.settingPage.backToMainWindow();
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), importWalletName);

  }


    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testImportMnemonicWithLongestNamePWDPositive() {
        String importMnemonic = "crew assume asset trip trim violin exhaust used bird slow universe jealous";
        String walletNameLong = "LongestTest Mnemonic";
        String pwd = longestPWD; // covers case of same mnemonic with different password

        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        app.settingPage.clickImportMnemonicButton();

        app.settingPage.inputMnemonic.sendKeys(importMnemonic);
        app.settingPage.nextBtn.click();
        app.settingPage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        app.createPage.inputWalletName.clear();
        app.createPage.inputWalletName.sendKeys(walletNameLong);
        String importWalletName = app.createPage.inputWalletName.getAttribute("value");
        app.settingPage.inputPassword.sendKeys(pwd);
        app.settingPage.inputConfirmPassword.sendKeys(pwd);

        app.createPage.nextBtn.click();
        app.settingPage.backToMainWindow();
        Assert.assertEquals(app.createPage.navigateWalletName.getText(), importWalletName);
    }


    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testCannotImportMnemonicWithSameNameNegative() throws InterruptedException {
        String importMnemonic = "crew assume asset trip trim violin exhaust used bird slow universe jealous";
        String walletName = "钱包 1钱包Test1"; // same name with create wallet case
        String pwd = "Aa111111";

        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        app.settingPage.clickImportMnemonicButton();
        app.settingPage.inputMnemonic.sendKeys(importMnemonic);
        app.settingPage.nextBtn.click();
        app.settingPage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        app.createPage.inputWalletName.clear();
        app.createPage.inputWalletName.sendKeys(walletName);
        app.settingPage.inputPassword.sendKeys(pwd);
        app.settingPage.inputConfirmPassword.sendKeys(pwd);

        Assert.assertTrue(app.createPage.nextBtn.isDisplayed(),
                "Next button should be disabled if wallet name already exist");
//        app.settingPage.backToMainWindow();
        app.settingPage.cancelBtn.click();
        Thread.sleep(2000);
        app.settingPage.cancelBtn.click();
    }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testImportKeystoreFromMenu() throws InterruptedException {
    String walletName = "import"+ new Date().getTime();
    String pwd = "Aa111111";

    String navigateWalletName = BWO.importWallet(walletName,pwd);

    Assert.assertEquals(navigateWalletName, walletName);

  }


    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testImportKeystoreWrongPWDFromSettingNegative()
            throws InterruptedException, AWTException {
        String walletName = "Keystore Error PWD";
        String wrongPWD = "Aa123456";
        String keystorePath = new File(
                System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
                .getAbsolutePath();

        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        app.settingPage.clickImportKeystoreButton();
        app.createPage.inputWalletName.clear();
        app.createPage.inputWalletName.sendKeys(walletName);
        app.createPage.inputPasswordForImportKeystore.sendKeys(wrongPWD);
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
        Thread.sleep(5000);

        // close the pop-up error window
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

//        app.settingPage.backToMainWindow();
        app.settingPage.cancelBtn.click();
    }

    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testImportKeystoreLongestNameFromSettingPositive()
            throws InterruptedException {
        String walletName = "Keystore Name1234567";
        String wrongPWD = "Aa111111";
        String keystorePath = new File(
                System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
                .getAbsolutePath();

        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        app.settingPage.clickImportKeystoreButton();
        app.createPage.inputWalletName.clear();
        app.createPage.inputWalletName.sendKeys(walletName);
        app.createPage.inputPasswordForImportKeystore.sendKeys(wrongPWD);
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
        Assert.assertEquals(navigateWalletName, walletName);
    }
}
