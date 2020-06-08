package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;

public class BaseWalletOperations extends TestBase {

  public void createNewWallet(String walletName, String pwd) throws InterruptedException {
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

      Thread.sleep(1000);
    // input wallet name
      for(int temp=0;temp<6;temp++){
          app.createPage.inputWalletName.clear();
          Thread.sleep(1000);
          if(app.createPage.inputWalletName.getText()=="") break;
      }
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(pwd);
    app.createPage.inputConfirmPassword.sendKeys(pwd);

    // click Next
    app.createPage.nextBtn.click();

  }
}
