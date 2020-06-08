package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateWalletTest extends TestBase {
// public class CreateWalletTest {

  BaseWalletOperations BWO = new BaseWalletOperations();

  @Test
  public void testCreateNewWallet() throws InterruptedException {

    System.out.println("Welcome slogan: "+ app.createPage.welcomeSlogan.getText());

    String walletName = "钱包Test1";
    String pwd = "Aa111111";
    BWO.createNewWallet(walletName, pwd);

    // verify the name displayed on left side bar should be the same with walletName created
    Assert.assertEquals(app.createPage.navigateWalletName.getText(), walletName);

  }

//    @Test
//    public void test00() throws InterruptedException, MalformedURLException {
//
//        //New an object of webdriver
//        System.setProperty("webdriver.chrome.driver", "/Users/Cedar/Dev/Install/chromedriver");
//        WebDriver driver = new ChromeDriver();
//
//        //Open the website “www.amazon.cn”
//        driver.get("https://www.amazon.cn/");
//        driver.manage().window().maximize();
//        Thread.sleep(2000); // Let the user actually see something!
//
//        //Search “软件测试”
//        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
//        searchBox.sendKeys("软件测试");
//        searchBox.submit();
//        Thread.sleep(2000); // Let the user actually see something!
//
//        //FindElement and Click linkText of “软件测试(原书第2版)”
//        driver.findElement(By.linkText("软件测试(原书第2版)")).click();
//        Thread.sleep(2000); // Let the user actually see something!
//
//        //get the handle of current page.
//        String currentWindow = driver.getWindowHandle();
//        //get the handles of all pages.
//        Set<String> handles = driver.getWindowHandles();
//
//        //change the focus to “软件测试(原书第2版)” page
//        Iterator<String> it = handles.iterator();
//        while(it.hasNext()) {
//            if (currentWindow == it.next()) continue;
//            driver.switchTo().window(it.next());
//        }
//
//        //Click "加入购物车" at “软件测试(原书第2版)” page
//        driver.findElement(By.id("add-to-cart-button")).click();
//        Thread.sleep(2000);  // Let the user actually see something!
//        //get the string of "商品已加入购物车" in web page.
//        String assert1 = driver.findElement(By.xpath("//div[@id='huc-v2-order-row-confirm-text']/h1")).getText();
//        //get the string of prince in web page.
//        String assert2 = driver.findElement(By.xpath("//div[@id='hlb-subcart']/div/span/span[2]")).getText();
//
//        //close browser and webdriver.
//        driver.quit();
//        // Assert that text "商品已加入购物车"
//        assertEquals(assert1, "商品已加入购物车");
//        // Assert that book price is "20.40"
//        assertEquals(assert2, "￥ 20.40");
//
//    }
}
