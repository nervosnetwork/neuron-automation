package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteWalletTest extends TestBase {

    BaseWalletOperations BWO = new BaseWalletOperations();


    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testDeleteAWalletByWrongPWDNegative() throws InterruptedException {

        app.settingPage.goToMainWindow();
        app.settingPage.navigateToSettingPage();
//        app.settingPage.clickWalletsTab();
        int size = app.settingPage.walletList.size();
        app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
        Thread.sleep(5000);
        app.settingPage.clickDeleteWallet(0);
        Thread.sleep(5000);
        app.settingPage.inputPasswordForDeleteWallet.sendKeys("Aa111112");
        app.settingPage.clickSaveButton();
        // verify the error message is displayed
        Assert.assertTrue(app.settingPage.msgForWrongPassword.isDisplayed(),
                "should display error message for wrong password");

        app.settingPage.clickCancelButton();
        app.settingPage.backToMainWindow();
    }




    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testDeleteAllWalletFromSetting() throws InterruptedException {
        app.settingPage.goToMainWindow();
      app.settingPage.navigateToSettingPage();
//    app.settingPage.clickWalletsTab();

    int size = app.settingPage.walletList.size();
    for (int i = 0; i < size; i++) {
        app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
        app.settingPage.clickDeleteWallet(0);
        Thread.sleep(5000);
        if (app.settingPage.walletList.get(0).getText().contains("Longest")) {
            app.settingPage.inputPasswordForDeleteWallet.sendKeys(longestPWD);
        } else {
            app.settingPage.inputPasswordForDeleteWallet.sendKeys("Aa111111");
        }
      app.settingPage.clickSaveButton();
        Thread.sleep(3000);
    }
      app.settingPage.backToMainWindow();
      Assert.assertTrue(app.createPage.util.isElementPresent(app.createPage.welcomeSlogan),
        "No welcome slogan, there is still wallet left.");

  }
}
