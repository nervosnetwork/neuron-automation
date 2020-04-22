package com.cryptape.neuron;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import com.cryptape.neuron.framework.TestBase;
import java.util.concurrent.TimeUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NetworkTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testAddNetwork() throws InterruptedException {
    String addNetworkName = "Add new NetworkName 28 chars";
    String addNetworkURL = "http://localhost:8225";

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickNetworkTab();

    app.settingPage.clickAddNetworkButton();

    app.networkPage.inputRPCURL.sendKeys(addNetworkURL);
    app.networkPage.inputNetworkName.sendKeys(addNetworkName);
    app.settingPage.clickSaveButton();

    Thread.sleep(3000);

    Assert.assertEquals(app.settingPage.getNetworkNameText(1), addNetworkName);
    assertThat(app.settingPage.getNetworkURLText(1), containsString(addNetworkURL));
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetwork() {
    String updateNetworkName = "Updated NetworkName 28 chars";
    String updateNetworkURL = "http://localhost:8115";

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickNetworkTab();

    app.settingPage.rightClick(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetworkFromContext();

    app.receivePage.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);

    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(updateNetworkName);

    app.settingPage.clickSaveButton();

    Assert.assertEquals(app.settingPage.getNetworkNameText(0), updateNetworkName);
    assertThat(app.settingPage.getNetworkURLText(0), containsString(updateNetworkURL));
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkURL() throws InterruptedException {
    String updateNetworkURL = "http://localhost:8116";

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickNetworkTab();

    app.settingPage.rightClick(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetworkFromContext();

    Thread.sleep(1000);
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);

    app.settingPage.clickSaveButton();

    assertThat(app.settingPage.getNetworkURLText(0), containsString(updateNetworkURL));
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkName() throws InterruptedException {
    String updateNetworkName = "UpdatedNetworkName28chars123";

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickNetworkTab();

    app.settingPage.rightClick(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetworkFromContext();

    Thread.sleep(3000);
    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(updateNetworkName);

    app.settingPage.clickSaveButton();

    Assert.assertEquals(app.settingPage.getNetworkNameText(0), updateNetworkName);
  }

}
