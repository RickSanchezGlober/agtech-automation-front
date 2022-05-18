package steps;

import cucumber.api.java.en.And;
import pages.BuyArticlesPage;

public class BuyArticlesSteps {
  BuyArticlesPage buyArticlesPage = new BuyArticlesPage();

  @And("^Select the laptop (.*)$")
  public void selectTheLaptop(String laptopName) {
    buyArticlesPage.selectItem(laptopName);
  }

  @And("^Verify the laptop (.*) is displayed$")
  public void verifyTheLaptopIsDisplayed(String laptopName) {
    buyArticlesPage.verifyTheLaptopIsDisplayed(laptopName);
  }
}
