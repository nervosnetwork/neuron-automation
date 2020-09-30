package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import com.cryptape.neuron.framework.utils.WaitUntil;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendTest extends TestBase {

  String language;
  int times = 0;

  @BeforeClass
  void getLanguage() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickGeneralTab();
    language = System.getProperty("user.language");
    System.out.println("language is: " + language);
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testNormalTransfer() throws Exception {
    importMinerKeystore();
    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    int txInitSize = app.historyPage.transactionSummaryList.size();

    app.sendPage.navigateToSendPage();
    // wait for balance not to be 0
    waitForBalanceNotZero();
    System.out.println("balance is: " + app.sendPage.balance.getText());

    fillInTXinfo("ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l", "99");
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

    waitForTXCommitted(txHash);
    int latestNum = getTXSeqNum(txHash);
    Assert.assertEquals(
        app.historyPage.transactionSummaryList.get(latestNum).getAttribute("data-status"),
        "confirming");

    // Click to unfold the tx
    app.historyPage.transactionSummaryList.get(latestNum).click();
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testInvalidFormatAddr() throws Exception {
    importMinerKeystore();
    app.historyPage.navigateToHistoryPage();
    app.sendPage.navigateToSendPage();
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    // try to input BTC format address
    app.sendPage.inputAddress.sendKeys("1JqAmANfWttS1eyJ7DDnNuTd7zqBoyTf47");
    String addressErrorMsg = app.sendPage.addressErrorMsg.getText();
    verifyAddressErrorMsg(addressErrorMsg,
        "请输入测试网地址",
        "Please enter a testnet address");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testInvalidChainAddr() throws Exception {
    importMinerKeystore();
    app.historyPage.navigateToHistoryPage();
    app.sendPage.navigateToSendPage();
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    // try to input mainnet address when connecting to testnet chain
    app.sendPage.inputAddress.sendKeys("ckb1qyqgsqsd549m333prhzyvdvedluuuas9u2ssxkmf53");
    String addressErrorMsg = app.sendPage.addressErrorMsg.getText();
    verifyAddressErrorMsg(addressErrorMsg,
        "请输入测试网地址",
        "Please enter a testnet address");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testAddandRemoveButton() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);

    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    waitForBalanceNotZero();

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys("ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l");
    app.sendPage.inputAmount.sendKeys("100");
    Assert.assertEquals(app.sendPage.addBtnList.size(), 1);
    Assert.assertTrue(app.sendPage.removeBtnList.isEmpty(),
        "there should be no remove button when there's only one receive address");

    app.sendPage.clickAddButton(0);
    Assert.assertEquals(app.sendPage.addBtnList.size(), 1);
    Assert.assertEquals(app.sendPage.removeBtnList.size(), 2);

    app.sendPage.clickRemoveButton(1);
    Assert.assertEquals(app.sendPage.addBtnList.size(), 1);
    Assert.assertTrue(app.sendPage.removeBtnList.isEmpty(),
        "there should be no remove button when there's only one receive address");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testMaxButton() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);

    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    waitForBalanceNotZero();

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys("ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l");
    app.sendPage.inputAmount.sendKeys("100");
    Assert.assertTrue(app.sendPage.maxBtn.isEnabled());
    app.sendPage.clickMaxButton();
    Assert.assertEquals(app.sendPage.maxBtn.getAttribute("data-is-on"), "true",
        "on the first click the Max button it should be gray");
    Assert.assertFalse(app.sendPage.inputAmount.isEnabled(),
        "on the first click the Max button the amount field should not be able to edit");

    app.sendPage.clickMaxButton();
    Assert.assertEquals(app.sendPage.maxBtn.getAttribute("data-is-on"), "false",
        "when second click the Max button it should not be gray");
    Assert.assertTrue(app.sendPage.inputAmount.isEnabled());
    Assert.assertTrue(app.sendPage.inputAmount.getText().isEmpty(),
        "when second click the Max button the amount field should be empty");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testMultiSigShortAddrTransfer() throws Exception {
    importMinerKeystore();
    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    int txInitSize = app.historyPage.transactionSummaryList.size();

    app.sendPage.navigateToSendPage();
    // wait for balance not to be 0
    waitForBalanceNotZero();
    System.out.println("balance is: " + app.sendPage.balance.getText());

    fillInTXinfo("ckt1qyq4vw6jaxf90wlfulhem46q8fkglxtscr9q80gzug", "110.110");
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

    waitForTXCommitted(txHash);
    int latestNum = getTXSeqNum(txHash);
    Assert.assertEquals(
        app.historyPage.transactionSummaryList.get(latestNum).getAttribute("data-status"),
        "confirming");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testMultiSigFullAddrTransfer() throws Exception {
    importMinerKeystore();
    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    int txInitSize = app.historyPage.transactionSummaryList.size();

    app.sendPage.navigateToSendPage();
    // wait for balance not to be 0
    waitForBalanceNotZero();
    System.out.println("balance is: " + app.sendPage.balance.getText());

    fillInTXinfo(
        "ckt1q3w9q60tppt7l3j7r09qcp7lxnp3vcanvgha8pmvsa3jplykxn3233tp8hl2y82mq0aud9fmruqpa0g0a738xz42803",
        "120.120");
    String addressErrorMsg = app.sendPage.fullAddressErrorMsg.getText();
    verifyAddressErrorMsg(addressErrorMsg,
        "收款方可能需要第三方软件才能操作该地址资产, 请再次确认地址有效性",
        "Additional software or dApp interface might be required to operate the assets on this address. Please double check its validity.");

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

    waitForTXCommitted(txHash);
    int latestNum = getTXSeqNum(txHash);
    Assert.assertEquals(
        app.historyPage.transactionSummaryList.get(latestNum).getAttribute("data-status"),
        "confirming");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testLockTimeNotAvailableForTypeFullAddrTransfer() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    waitForBalanceNotZero();

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys(
        "ckt1qjda0cr08m85hc8jlnfp3zer7xulejywt49kt2rr0vthywaa50xw39xpwg8al7vdrsqspnasu7hy9ersk5cpj33ej9w");
    String addressErrorMsg = app.sendPage.fullAddressErrorMsg.getText();
    verifyAddressErrorMsg(addressErrorMsg,
        "收款方可能需要第三方软件才能操作该地址资产, 请再次确认地址有效性",
        "Additional software or dApp interface might be required to operate the assets on this address. Please double check its validity.");
    Assert.assertTrue(app.sendPage.setLockTimeList.isEmpty(),
        "set Locktime is not available for type full address");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testLockTimeNotAvailableForDataFullAddrTransfer() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    waitForBalanceNotZero();

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys(
        "ckt1qfcf7076zt6krnavly3883t6nrlduxy28ud9nv0c3rg387wvuzryn9xpwg8al7vdrsqspnasu7hy9ersk5cpjqgrsfr");
    String addressErrorMsg = app.sendPage.fullAddressErrorMsg.getText();
    verifyAddressErrorMsg(addressErrorMsg,
        "收款方可能需要第三方软件才能操作该地址资产, 请再次确认地址有效性",
        "Additional software or dApp interface might be required to operate the assets on this address. Please double check its validity.");
    Assert.assertTrue(app.sendPage.setLockTimeList.isEmpty(),
        "set Locktime is not available for data full address");
  }

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testLockTimeOnlyAvailableForBlake160ShortAddrTransfer() throws Exception {
    importMinerKeystore();

    app.historyPage.navigateToHistoryPage();
    waitForHistoryListUpdate(0);
    app.sendPage.navigateToSendPage();

    // wait for balance not to be 0
    waitForBalanceNotZero();

    System.out.println("balance is: " + app.sendPage.balance.getText());
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys(
        "ckt1qyqffstjpl0lnrguqyqvlv884epwgu94xqvsp42s9l");
    Assert.assertTrue(app.sendPage.util.isElementPresent(app.sendPage.setLockTimeList.get(0)),
        "Set Locktime is available for Blake160 short address");

    app.sendPage.inputAddress.clear();
    app.sendPage.inputAddress.sendKeys(
        "ckt1qfcf7076zt6krnavly3883t6nrlduxy28ud9nv0c3rg387wvuzryn9xpwg8al7vdrsqspnasu7hy9ersk5cpjqgrsfr");
    Assert.assertTrue(app.sendPage.setLockTimeList.isEmpty(),
        "Set Locktime is not available for other address type");
  }


  void importMinerKeystore() throws InterruptedException {
    StringBuffer walletName = new StringBuffer("importKeystore");
    String pwd = "Aa111111";
    String keystorePath = new File(
        System.getProperty("user.dir") + "/resource", "WalletMinerHenryKeystore.json")
        .getAbsolutePath();

    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();
    app.settingPage.clickImportKeystoreButton();

    Thread.sleep(1000);
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName.append(times));
    times = times + 1;
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

  int getTXSeqNum(String findTxHash) {
    int num = 0;
    for (int i = 0; i < app.historyPage.transactionSummaryList.size(); i++) {
      if (findTxHash
          .equals(app.historyPage.transactionSummaryList.get(i).getAttribute("data-hash"))) {
        num = i;
        break;
      }
    }
    return num;
  }

  void waitForBalanceNotZero() throws Exception {
    boolean waitBalanceSynced = waitFor(new WaitUntil() {
      @Override
      public boolean waitUntil() {
        if (!"0".equals(app.sendPage.balance.getText())) {
          return true;
        }
        return false;
      }
    }, 20, 1);

    if (!waitBalanceSynced) {
      throw new Exception("timeout to wait for balance with non-zero.");
    }
  }

  /**
   * @param addressErrorMsg: the actual error message
   * @param zhMsg: the expected error message in Chinese
   * @param enMsg: the expected error message in English
   * @description verify the error message for invalid address
   */
  void verifyAddressErrorMsg(String addressErrorMsg, String zhMsg, String enMsg) {

    if ("zh".equals(language)) {
      Assert.assertEquals(addressErrorMsg, zhMsg);
    } else if ("en".equals(language)) {
      Assert.assertEquals(addressErrorMsg, enMsg);
    }
  }

  void fillInTXinfo(String address, String amount) {
    app.sendPage.inputAddress.clear();
    app.sendPage.inputAmount.clear();
    app.sendPage.inputAddress.sendKeys(address);
    app.sendPage.inputAmount.sendKeys(amount);
    app.sendPage.clickAddButton(0);
    System.out.println("add button size: " + app.sendPage.addBtnList.size());
    System.out.println("remove button size: " + app.sendPage.removeBtnList.size());
    app.sendPage.clickRemoveButton(1);
    app.sendPage.inputDescription.sendKeys("description for send test");
  }

  void waitForTXCommitted(String txHash) throws Exception {
    int num = getTXSeqNum(txHash);
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
  }

}
