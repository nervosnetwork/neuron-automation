package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import com.cryptape.neuron.framework.utils.WaitUntil;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testNormalTransfer() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    int txInitSize = app.historyPage.transactionSummaryList.size();

    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    boolean waitBalanceSynced = waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (!app.sendPage.balance.getText().equals("0")) {
          return true;
        }
        return false;
      }
    }, 20, 1);

    if (!waitBalanceSynced) {
      throw new Exception("timeout to wait for balance with non-zero.");
    }

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys("ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l");
    app.sendPage.inputAmount.sendKeys("100");
    app.sendPage.clickAddButton(0);
    System.out.println("add button size: " + app.sendPage.addBtnList.size());
    System.out.println("remove button size: " + app.sendPage.removeBtnList.size());
    app.sendPage.clickRemoveButton(1);
    app.sendPage.inputDescription.sendKeys("description for send test");
    app.sendPage.switchAdvancedTXfee();

    app.sendPage.expectedSpeed.click(); // click expected speed list
    Assert.assertEquals(app.sendPage.speedDropdownList.size(), 3);

    app.sendPage.clickSubmitButton();

    app.sendPage.inputPWD.sendKeys("Aa111111");
    app.sendPage.clickPWDSubmit();

    waitForHistoryListUpdate(txInitSize);

    String txHash = app.historyPage.transactionSummaryList.get(0).getAttribute("data-hash");
    System.out.println("committed tx hash should be: " + txHash);
    Assert.assertEquals(app.historyPage.transactionSummaryList.get(0).getAttribute("data-status"),
        "pending");

    String cmdMiner = ckbPath + "ckb.exe miner -C " + nodePath + " --limit 5";
    runCommand("\"" + cmdMiner + "\"");

    int num = getTXseqNum(txHash);
    // wait for tx to be committed
    boolean waitTXCommitted = waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (!app.historyPage.transactionSummaryList.get(num).getAttribute("data-status")
            .equals("pending")) {
          return true;
        }
        return false;
      }
    }, 30, 1);

    if (!waitTXCommitted) {
      throw new Exception("timeout to wait for tx to be committed!");
    }

    int latestNum = getTXseqNum(txHash);
    Assert.assertEquals(
        app.historyPage.transactionSummaryList.get(latestNum).getAttribute("data-status"),
        "confirming");

    // Click to unfold the tx
    app.historyPage.transactionSummaryList.get(latestNum).click();
  }

  void importMinerKeystore() throws InterruptedException {
    String walletName = "importWalletKeystore";
    String pwd = "Aa111111";
    String keystorePath = new File(
        System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
        .getAbsolutePath();

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();
    app.settingPage.clickImportKeystoreButton();

    Thread.sleep(1000);
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);
    app.createPage.inputPasswordForImportKeystore.clear();
    app.createPage.inputPasswordForImportKeystore.sendKeys(pwd);
    app.createPage.inputPathOfKeystore.click();
    Thread.sleep(3000);
    StringSelection stringSelection = new StringSelection(keystorePath);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

    app.createPage.keyCtrlV();
    app.createPage.keyEnter();
    Thread.sleep(3000);

    app.createPage.clickSubmitBtn();
    app.settingPage.backToMainWindow();
  }

  void waitForHistoryListUpdate(int compareSize) {
    waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (app.historyPage.transactionSummaryList.size() > compareSize) {
          return true;
        }
        return false;
      }
    }, 20, 1);
  }

  int getTXseqNum(String findTxHash) {
    int num = 0;
    for (int i = 0; i < app.historyPage.transactionSummaryList.size(); i++) {
      if (app.historyPage.transactionSummaryList.get(i).getAttribute("data-hash")
          .equals(findTxHash)) {
        num = i;
        break;
      }
    }
    return num;
  }

}
