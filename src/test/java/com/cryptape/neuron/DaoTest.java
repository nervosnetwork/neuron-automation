package com.cryptape.neuron;

import com.cryptape.neuron.framework.TestBase;
import org.testng.annotations.Test;

public class DaoTest extends TestBase {

  @Test(dependsOnMethods = "com.cryptape.neuron.CreateWalletTest.testCreateNewWalletFromMenu")
  public void testDAO() {
    app.daoPage.navigateToDAOPage();
    //
  }

}
