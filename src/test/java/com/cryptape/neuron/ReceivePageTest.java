package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.Date;

public class ReceivePageTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWallet")
  public void testReceiveSaveImage() throws Exception {
      app.settingPage.goToMainWindow();
    app.receivePage.navigateToReceivePage();
//    app.receivePage.clickSaveImageButton();
      app.receivePage.clickCopyImageButton();


    Thread.sleep(1000);
    String path = System.getProperty("user.dir") + "/resource";
    String fileName = "ckb-address" + new Date().getTime() + ".png";
    String saveReceivePath = new File(path, fileName).getAbsolutePath();

    StringSelection stringSelection = new StringSelection(saveReceivePath);
//    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

      Image image = getImageFromClipboard();
      File file1 = new File(saveReceivePath);
      //转成png
      BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
      Graphics2D g = bufferedImage.createGraphics();
      g.drawImage(image, null, null);
      ImageIO.write((RenderedImage) bufferedImage, "png", file1);

//    app.receivePage.keyCtrlV();
//    app.receivePage.keyEnter();

    Thread.sleep(1000);
    File file = new File(path, fileName);
    System.out.println(file.getPath());
    Assert.assertTrue(file.exists(), file.getPath() + "Receive image is not been saved!");

  }


    /**
     * 从剪切板获得图片。
     */
    public static Image getImageFromClipboard() throws Exception {
        Clipboard sysc = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable cc = sysc.getContents(null);
        if (cc == null)
            return null;
        else if (cc.isDataFlavorSupported(DataFlavor.imageFlavor))
            return (Image) cc.getTransferData(DataFlavor.imageFlavor);
        return null;
    }

}
