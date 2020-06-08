package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BackupWalletTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testBackupWalletFromSetting() throws InterruptedException {
    String backupName =
        app.settingPage.navigateWalletName.getText() + new Date().getTime() + "backup.json";
    String backupPath = System.getProperty("user.dir") + "/resource";
//      String backupPath = "/Users/Cedar/Downloads";
    String backupFullPath = new File(backupPath, backupName).getAbsolutePath();

    app.settingPage.navigateWalletName.click();
    app.settingPage.clickWalletsTab();

    app.settingPage.rightClick(app.driver, app.settingPage.walletList.get(0));
    app.settingPage.clickBackupWalletFromContext();
    app.settingPage.inputPasswordForBackup.sendKeys("Aa111111");
    app.settingPage.clickSaveButton();

    StringSelection stringSelection = new StringSelection(backupFullPath);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    app.settingPage.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);


      app.createPage.keySlash();
      app.createPage.keyCommandA();
      app.createPage.keyCommandV();
      app.createPage.keyEnter();
      Thread.sleep(2000);
      app.createPage.keyEnter();
    Thread.sleep(2000);

    File file = new File(backupPath, backupName);
    System.out.println(file.getPath());
    Assert.assertTrue(file.exists(), "Wallet is not been saved!");
  }

}
