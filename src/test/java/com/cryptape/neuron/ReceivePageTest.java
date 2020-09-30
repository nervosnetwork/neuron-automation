package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReceivePageTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testReceiveSaveImage() throws InterruptedException {
    app.receivePage.navigateToReceivePage();
    app.receivePage.clickSaveImageButton();

    Thread.sleep(1000);
    String path = System.getProperty("user.dir") + "/resource";
    String fileName = "ckb-address" + new Date().getTime() + ".png";
    String saveReceivePath = new File(path, fileName).getAbsolutePath();

    StringSelection stringSelection = new StringSelection(saveReceivePath);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

    app.receivePage.keyCtrlV();
    app.receivePage.keyEnter();

    Thread.sleep(3000);
    File file = new File(path, fileName);
    System.out.println(file.getPath());
    Assert.assertTrue(file.exists(), file.getPath() + "Receive image is not been saved!");

  }

}
