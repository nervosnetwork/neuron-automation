package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class NetworkTest extends TestBase {

    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testAddNetwork() throws InterruptedException {
        String addNetworkName = "Add new NetworkName 28 chars";
        String addNetworkURL = "http://localhost:8225";

        app.settingPage.navigateToSettingPage();
        app.settingPage.clickNetworkTab();

        app.settingPage.clickAddNetworkButton();

        app.networkPage.inputRPCURL.sendKeys(addNetworkURL);
        app.networkPage.inputNetworkName.sendKeys(addNetworkName);
        app.settingPage.clickSaveButton();

        Thread.sleep(3000);

        app.settingPage.mouseover(app.driver, app.settingPage.networkNameList.get(1));

        assertThat(app.settingPage.getNetworkNameText(1), containsString(addNetworkName));
        assertThat(app.settingPage.getNetworkURLText(1), containsString(addNetworkURL));
        app.settingPage.backToMainWindow();
    }

    @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
    public void testEditNetwork() {
        String updateNetworkName = "Updated NetworkName 28 chars";
        String updateNetworkURL = "http://localhost:8115";

        app.settingPage.navigateToSettingPage();
        app.settingPage.clickNetworkTab();

//        int i = app.settingPage.networkNameList.size();

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

}