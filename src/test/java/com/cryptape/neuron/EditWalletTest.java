package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.annotations.Test;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class EditWalletTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditWallet() throws InterruptedException {
    app.settingPage.goToMainWindow();
    app.settingPage.navigateToSettingPage();
    //        app.settingPage.clickWalletsTab();
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

}
