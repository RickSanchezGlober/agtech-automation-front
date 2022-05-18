package pages;

import pageObjects.BuyArticlesPageObject;

public class BuyArticlesPage extends BasePage {
  BuyArticlesPageObject buyArticlesPageObject = new BuyArticlesPageObject();

  public BuyArticlesPage() {
    super();
  }

  public void selectItem(String laptopName) {
    clickOnSelectedObjet(buyArticlesPageObject.ITEMS_LIST, laptopName);
  }

  public void verifyTheLaptopIsDisplayed(String laptopName) {
    verifyTheItemIsDisplayed(buyArticlesPageObject.ITEMS_LIST_ON_CART, laptopName);
  }
}
