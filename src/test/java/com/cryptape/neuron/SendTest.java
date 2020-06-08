package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import com.cryptape.neuron.framework.utils.WaitUntil;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.ImportWalletTest.testImportKeystoreFromMenu")
  public void testNormalTransfer() throws Exception {
    app.sendPage.navigateToSendPage();
      Thread.sleep(2000);
      String getCKBtext = app.sendPage.balance.getText();
      // wait for balance not to be 0
    boolean waitBalanceSynced = waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (!app.sendPage.balance.getText().equals("0 CKB")) {
          return true;
        }
        return false;
      }
    }, 20, 1);

    if (!waitBalanceSynced) {
      throw new Exception("timeout to wait for balance with non-zero.");
    }

    System.out.println("balance is: " + app.sendPage.balance.getText()); // 482,372.00018174 CKB
    app.sendPage.inputAddress.sendKeys("ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l");
    app.sendPage.inputAmount.sendKeys("100");
    app.sendPage.clickAddButton(0);
    System.out.println("add button size: " + app.sendPage.addBtnList.size());
    System.out.println("remove button size: " + app.sendPage.removeBtnList.size());
    app.sendPage.clickRemoveButton(1);
    app.sendPage.inputDescription.sendKeys("description for send test");
    app.sendPage.switchAdvancedTXfee();

    app.sendPage.expectedSpeed.click(); // click expected speed list
    Assert.assertEquals(app.sendPage.speedDropdownList.size(), 4);

    app.sendPage.clickSubmitButton();

    app.sendPage.inputPWD.sendKeys("Aa111111");
    app.sendPage.clickPWDSubmit();

    String txHash = app.historyPage.transactionSummaryList.get(0).getAttribute("data-hash");
    Assert.assertEquals(app.historyPage.transactionSummaryList.get(0).getAttribute("data-status"),
        "pending");

//    String cmdMiner = ckbPath + "ckb.exe miner -C " + nodePath + " --limit 5";
      String cmdMiner = ckbPath + "./ckb miner -C " + nodePath + " --limit 5";
    runCommand("\"" + cmdMiner + "\"");

    // wait for tx to be committed
    boolean waitTXCommitted = waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (!app.historyPage.transactionSummaryList.get(0).getAttribute("data-status")
            .equals("pending")) {
          return true;
        }
        return false;
      }
    }, 20, 1);

    if (!waitTXCommitted) {
      throw new Exception("timeout to wait for tx to be committed!");
    }

    int num = 0;
    for (int i = 0; i < app.historyPage.transactionSummaryList.size(); i++) {
      if (app.historyPage.transactionSummaryList.get(i).getAttribute("data-hash").equals(txHash)) {
        num = i;
        break;
      }
    }
    Assert.assertEquals(app.historyPage.transactionSummaryList.get(num).getAttribute("data-status"),
        "confirming");
  }

}
