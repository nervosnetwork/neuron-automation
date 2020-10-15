package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import com.cryptape.neuron.framework.utils.WaitUntil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testClearCacheFullyRebuildIndex() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickGeneralTab();
    app.settingPage.clickClearCache();
    // fully rebuild index
    app.settingPage.checkRebuildCache();
    app.settingPage.clickSubmitClearCacheButton();
    app.settingPage.backToMainWindow();
    app.settingPage.mouseover(app.driver, app.settingPage.networkStatus);

    Assert.assertEquals(app.settingPage.networkStatusSyncedBlockNum.getText(), "0");

    // wait until block synced completely
    waitForBlockSynced();

    System.out
        .println("Tip block number is: " + app.settingPage.networkStatusTipBlockNum.getText());
    System.out.println(
        "Synced block number is: " + app.settingPage.networkStatusSyncedBlockNum.getText());
    Assert.assertEquals(app.settingPage.networkStatusTipBlockNum.getText(),
        app.settingPage.networkStatusSyncedBlockNum.getText());
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testClearCacheRefresh() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickGeneralTab();
    app.settingPage.clickClearCache();
    // submit be default of only refresh cache
    app.settingPage.clickSubmitClearCacheButton();
    app.settingPage.backToMainWindow();
    app.settingPage.mouseover(app.driver, app.settingPage.networkStatus);

    // wait until block synced completely
    waitForBlockSynced();

    System.out
        .println("Tip block number is: " + app.settingPage.networkStatusTipBlockNum.getText());
    System.out.println(
        "Synced block number is: " + app.settingPage.networkStatusSyncedBlockNum.getText());
    Assert.assertEquals(app.settingPage.networkStatusTipBlockNum.getText(),
        app.settingPage.networkStatusSyncedBlockNum.getText());
  }

  void waitForBlockSynced() {
    waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (app.historyPage.networkStatusSyncedBlockNum.getText()
            .equals(app.settingPage.networkStatusTipBlockNum.getText())) {
          return true;
        }
        return false;
      }
    }, 20, 1);
  }

}
