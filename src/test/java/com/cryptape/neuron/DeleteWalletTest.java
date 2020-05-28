package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteWalletTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testDeleteAllWalletFromSetting() {

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();

    int size = app.settingPage.walletList.size();
    for (int i = 0; i < size; i++) {
      app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
      app.settingPage.clickDeleteWallet(0);
      app.settingPage.inputPasswordForDeleteWallet.sendKeys("Aa111111");
      app.settingPage.clickSaveButton();
    }
    app.settingPage.backToMainWindow();

    Assert.assertTrue(app.createPage.util.isElementPresent(app.createPage.welcomeSlogan),
        "No welcome slogan, there is still wallet left.");

  }


}
