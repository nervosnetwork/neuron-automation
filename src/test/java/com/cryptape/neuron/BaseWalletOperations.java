package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;

public class BaseWalletOperations extends TestBase {

  public void createNewWallet(String walletName, String pwd) {
    app.createPage.clickCreateBtn();

    //Generate mnemonic
    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println(mnemonic);

    // click Next
    app.createPage.nextBtn.click();

    // repeat with the generated mnemonic
    app.createPage.inputMnemonic.sendKeys(mnemonic);

    // click Next
    app.createPage.nextBtn.click();

    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(pwd);
    app.createPage.inputConfirmPassword.sendKeys(pwd);

    // click Next
    app.createPage.nextBtn.click();

  }

}
