package com.cryptape.neuron;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import com.cryptape.neuron.framework.TestBase;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditWalletTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditWallet() throws InterruptedException {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();

    String updateWalletName = "Updated" + new Date().getTime(); //20 characters long

    app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
    app.settingPage.clickEditWallet(0);
    Thread.sleep(1000);
    app.settingPage.editWalletName.clear();
    app.settingPage.editWalletName.sendKeys(updateWalletName);

    app.settingPage.clickSaveButton();
    Thread.sleep(1000);

    assertThat(app.settingPage.walletList.get(0).getText(), containsString(updateWalletName));
    app.settingPage.backToMainWindow();

  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditWalletClearNameCntSaveNegative() throws InterruptedException {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();

    app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
    app.settingPage.clickEditWallet(0);
    Thread.sleep(1000);
    app.settingPage.editWalletName.clear();

    Assert.assertFalse(app.settingPage.saveBtn.isEnabled(),
        "Save button should be disabled when wallet name is blank.");
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditWalletNameWithSpecialCharsPositive() throws InterruptedException {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();

    String updateWalletName = "~!@#$%^&*()_+=- [];'"; //20 characters long

    app.settingPage.mouseover(app.driver, app.settingPage.walletList.get(0));
    app.settingPage.clickEditWallet(0);
    Thread.sleep(1000);
    app.settingPage.editWalletName.clear();
    app.settingPage.editWalletName.sendKeys(updateWalletName);
    app.settingPage.clickSaveButton();
    Thread.sleep(1000);

    assertThat(app.settingPage.walletList.get(0).getText(), containsString(updateWalletName));
    app.settingPage.backToMainWindow();
  }

}
