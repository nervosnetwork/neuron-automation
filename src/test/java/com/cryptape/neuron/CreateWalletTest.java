package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateWalletTest extends TestBase {

  BaseWalletOperations BWO = new BaseWalletOperations();

  @Test
  public void testCreateNewWallet() {

    System.out.println("Welcome slogan: " + app.createPage.welcomeSlogan.getText());

    String walletName = "钱包Test1";
    String pwd = "Aa111111";
    BWO.createNewWallet(walletName, pwd);

    // verify the name displayed on left side bar should be the same with walletName created
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), walletName);

  }

  @Test(priority = 1)
  public void testCreateNewWalletFromMenu() {

    app.createPage.clickMenuCreateWallet();

    String walletName = "钱包Test" + System.currentTimeMillis();
    String pwd = "Aa111111";
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

    // verify the name displayed on left side bar should be the same with walletName created
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), walletName);

  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCreateWithLongestNamePWDPositive() {
//    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(mnemonic);
    app.createPage.nextBtn.click();
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(longestWalletName);

    app.createPage.inputPassword.sendKeys(longestPWD);
    app.createPage.inputConfirmPassword.sendKeys(longestPWD);
    // click Next
    app.createPage.nextBtn.click();
    app.settingPage.backToMainWindow();
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), longestWalletName);
  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCannotCreateWithWrongMnemonicNegative() {
    String assumeMnemonic = "undo table grace achieve relief shadow express six magnet purity release awkward";
    //    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(assumeMnemonic);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when mnemonic is not the same with generated one");

    app.createPage.backBtn.click();
    String newMnemonic = app.createPage.generateMnemonic.getText();
    // verify the mnemonic should be re-generated when click back
    Assert.assertNotEquals(mnemonic, newMnemonic, "Mnemonic should be re-generated when back");

    // go back to home page
    app.createPage.backBtn.click();
  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCannotCreateWithDiffPWDNegative() {
    String pwd = "Aa111111";
    String confirmPWD = "Aa123456";
    // wallet name with longest length of 20
    String walletName = "Wallet AutoTest12345";
    //    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(mnemonic);
    app.createPage.nextBtn.click();
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(pwd);
    app.createPage.inputConfirmPassword.sendKeys(confirmPWD);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when two password are not the same");
    // go back to home page
    backToHomePage();
  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCannotCreateWithLongerPWDNegative() {
    // password of 53 length that longer than 50
    String veryLongPWD = "_1234567890abcdefg~!@#$%^&*()_+QWERTYUIOP[] ASDFGH123";
    // wallet name with longest length of 20
    String walletName = "Wallet AutoTest12345";
    //    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(mnemonic);
    app.createPage.nextBtn.click();
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(veryLongPWD);
    app.createPage.inputConfirmPassword.sendKeys(veryLongPWD);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when password is longer than 50");
    // go back to home page
    backToHomePage();
  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCannotCreateWithShortPWDNegative() {
    // password of 7 length which shorter than required of 8 length
    String veryLongPWD = "Aa12345";
    // wallet name with longest length of 20
    String walletName = "Wallet AutoTestShort";
    //    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(mnemonic);
    app.createPage.nextBtn.click();
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(veryLongPWD);
    app.createPage.inputConfirmPassword.sendKeys(veryLongPWD);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when password is shorter than 8");
    // go back to home page
    backToHomePage();
  }

  @Test(dependsOnMethods = "testCreateNewWalletFromMenu")
  public void testCannotCreateWithInvalidPWDNegative() {
    // password with only two kinds of character categories
    String invalidPWD = "_1234567890";
    // wallet name with longest length of 20
    String walletName = "Wallet AutoTest";
    //    app.createPage.clickMenuCreateWallet();
    clickCreateButtonFromSetting();

    String mnemonic = app.createPage.generateMnemonic.getText();
    System.out.println("generated mnemonic: " + mnemonic);
    app.createPage.nextBtn.click();
    app.createPage.inputMnemonic.sendKeys(mnemonic);
    app.createPage.nextBtn.click();
    // input wallet name
    app.createPage.inputWalletName.clear();
    app.createPage.inputWalletName.sendKeys(walletName);

    app.createPage.inputPassword.sendKeys(invalidPWD);
    app.createPage.inputConfirmPassword.sendKeys(invalidPWD);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when password is invalid");

    invalidPWD = "A1234567";
    app.createPage.inputPassword.clear();
    app.createPage.inputConfirmPassword.clear();
    app.createPage.inputPassword.sendKeys(invalidPWD);
    app.createPage.inputConfirmPassword.sendKeys(invalidPWD);
    Assert.assertFalse(app.createPage.nextBtn.isEnabled(),
        "Next button should be disabled when password is invalid");

    // go back to home page
    backToHomePage();
  }


  void backToHomePage() {
    app.createPage.backBtn.click();
    app.createPage.backBtn.click();
    app.createPage.backBtn.click();
  }

  public void clickCreateButtonFromSetting() {
    app.settingPage.navigateToSettingPage();
    app.settingPage.clickWalletsTab();
    app.settingPage.clickCreateButton();
  }

}
