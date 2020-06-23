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
    String addNetworkURL = "http://localhost:8225"; // url with http

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();

    app.settingPage.clickAddNetworkButton();

    app.networkPage.inputRPCURL.sendKeys(addNetworkURL);
    app.networkPage.inputNetworkName.sendKeys(addNetworkName);
    app.settingPage.clickSaveButton();

    Thread.sleep(3000);

    assertThat(app.settingPage.getNetworkNameText(1), containsString(addNetworkName));
    assertThat(app.settingPage.getNetworkURLText(1), containsString(addNetworkURL));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetwork() {
    String updateNetworkName = "Updated NetworkName 28 chars";
    String updateNetworkURL = "https://localhost:8115"; //url with https

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();

    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);

    app.receivePage.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);

    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(updateNetworkName);

    app.settingPage.clickSaveButton();

    assertThat(app.settingPage.getNetworkNameText(0), containsString(updateNetworkName));
    assertThat(app.settingPage.getNetworkURLText(0), containsString(updateNetworkURL));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkURL() throws InterruptedException {
    String updateNetworkURL = "http://localhost:8116";

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();

    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);

    Thread.sleep(1000);
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);

    app.settingPage.clickSaveButton();

    assertThat(app.settingPage.getNetworkURLText(0), containsString(updateNetworkURL));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkName() throws InterruptedException {
    String updateNetworkName = "UpdatedNetworkName28chars123";

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();

    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);

    Thread.sleep(3000);
    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(updateNetworkName);

    app.settingPage.clickSaveButton();

    assertThat(app.settingPage.getNetworkNameText(0), containsString(updateNetworkName));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testCancelAddNetwork() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    String addNetworkName = "Cancel Add new NetworkName";
    String addNetworkURL = "http://localhost:8226";
    int originNetworkNum = app.settingPage.networkNameList.size();
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    app.settingPage.clickAddNetworkButton();
    app.networkPage.inputRPCURL.sendKeys(addNetworkURL);
    app.networkPage.inputNetworkName.sendKeys(addNetworkName);
    app.settingPage.clickCancelButton();
    Assert.assertEquals(app.settingPage.networkNameList.size(), originNetworkNum);
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testCancelEditNetwork() {
    String updateNetworkName = "Cancel Update NetworkName";
    String updateNetworkURL = "http://localhost:8115";

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);
    String originNetworkURL = app.networkPage.inputRPCURL.getAttribute("value");
    String originNetworkName = app.networkPage.inputNetworkName.getAttribute("value");
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);
    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(updateNetworkName);
    app.settingPage.clickCancelButton();

    // Verify that the network's name and RPC URL are the same with original
    assertThat(app.settingPage.getNetworkNameText(0), containsString(originNetworkName));
    assertThat(app.settingPage.getNetworkURLText(0), containsString(originNetworkURL));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkInvalidURLNegative() {
    String updateNetworkURL = "localhost:8115";
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);
    app.receivePage.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);
    Assert.assertFalse(app.settingPage.saveBtn.isEnabled(),
        "Save button should be disabled when RPC url is invalid");
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkWithSameInfoNegative(){
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);
    String originNetworkURL = app.networkPage.inputRPCURL.getAttribute("value");
    String originNetworkName = app.networkPage.inputNetworkName.getAttribute("value");
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(originNetworkURL);
    app.networkPage.inputNetworkName.clear();
    app.networkPage.inputNetworkName.sendKeys(originNetworkName);
    Assert.assertFalse(app.settingPage.saveBtn.isEnabled(),
        "Save button should be disabled when info are the same as before");
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testEditNetworkWithLongURL() throws InterruptedException {
    String updateNetworkURL =
        "http://localhost1234567890abcdefghijklmnopqrstuvwxyz~!@#$%^}|:<>?'';1234567890qwertyuiopsdfghjklzxcvbnm:811677777777777777777777777";
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(0));
    app.settingPage.clickEditNetwork(0);
    Thread.sleep(1000);
    app.networkPage.inputRPCURL.clear();
    app.networkPage.inputRPCURL.sendKeys(updateNetworkURL);
    app.settingPage.clickSaveButton();
    assertThat(app.settingPage.getNetworkURLText(0), containsString(updateNetworkURL));
    app.settingPage.backToMainWindow();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testCannotDeleteFirstNetwork() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    int editButtonNum = app.settingPage.editNetworkBtnList.size();
    int deleteButtonNum = app.settingPage.deleteWalletBtnList.size();
    System.out.println("editButtonNum: " + editButtonNum);
    System.out.println("deleteButtonNum: " + deleteButtonNum);
    Assert.assertEquals(deleteButtonNum, editButtonNum - 1,
        "Number of delete button should be 1 less than number edit button");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testDeleteAllNetworkExceptFirst() throws InterruptedException {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickNetworkTab();
    int deleteButtonNum = app.settingPage.deleteWalletBtnList.size();
    if (deleteButtonNum != 0) {
      for (int i = 0; i < deleteButtonNum; i++) {
        app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(1));
        app.settingPage.clickDeleteNetwork(0);
        app.settingPage.keyEnter();
      }
      Thread.sleep(1000);
      Assert.assertEquals(app.settingPage.networkNameList.size(), 1,
          "Should remain only 1 network after remove all networks that can be deleted");
    } else {
      Assert.assertEquals(deleteButtonNum, 0);
    }
  }

}
