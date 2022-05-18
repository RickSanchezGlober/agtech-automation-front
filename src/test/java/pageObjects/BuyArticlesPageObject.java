package pageObjects;

import org.openqa.selenium.By;

public class BuyArticlesPageObject {
  public static final By.ByXPath LAPTOPS_BUTTON = new By.ByXPath("//a[contains(text(),'Laptops')]");
  public static final By.ByTagName ITEMS_LIST = new By.ByTagName("h4");
  public static final By.ByTagName ITEMS_LIST_ON_CART = new By.ByTagName("td");
  public static final By.ByLinkText ADD_TO_CART_BUTTON = new By.ByLinkText("Add to cart");
  public static final By.ByLinkText CART_BUTTON = new By.ByLinkText("Cart");
}
