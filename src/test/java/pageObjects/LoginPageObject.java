package pageObjects;

import org.openqa.selenium.By;

public class LoginPageObject {
  public static final By.ById LOG_IN_LINK = new By.ById("login2");
  public static final By.ById LOGIN_USERNAME = new By.ById("loginusername");
  public static final By.ById LOGIN_PASSWORD = new By.ById("loginpassword");
  public static final By.ByXPath LOG_IN_BUTTON =
      new By.ByXPath("//*[@id='logInModal']/div/div/div[3]/button[2]");
  public static final By.ById NAME_OF_USER = new By.ById("nameofuser");
  public static final By.ById PRODUCT_STORE_LOGO = new By.ById("nava");
  public static final By.ById LOG_OUT_BUTTON = new By.ById("logout2");
}
