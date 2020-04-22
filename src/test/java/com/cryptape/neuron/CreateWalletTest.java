package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateWalletTest extends TestBase {

  BaseWalletOperations BWO = new BaseWalletOperations();

  @Test
  public void testCreateNewWallet() {

    System.out.println("Welcome slogan: "+ app.createPage.welcomeSlogan.getText());

    String walletName = "钱包Test1";
    String pwd = "Aa111111";
    BWO.createNewWallet(walletName, pwd);

    // verify the name displayed on left side bar should be the same with walletName created
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), walletName);

  }


}
