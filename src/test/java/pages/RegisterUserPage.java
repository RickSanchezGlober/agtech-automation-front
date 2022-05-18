package pages;

import org.openqa.selenium.By;
import pageObjects.BuyArticlesPageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterUserPageObject;
import utils.DataGenerator;

public class RegisterUserPage extends BasePage {

  public static String emailGenerated;
  public static String passwordGenerated;

  public RegisterUserPage() {
    super();
    emailGenerated = DataGenerator.getEmail();
    passwordGenerated = DataGenerator.getPassword();
  }

  public void navigateTo() {
    navigateTo("https://www.demoblaze.com/index.html");
  }

  public void clickOnButton(String button) {
    By element = null;
    switch (button) {
      case "Sign up":
        element = RegisterUserPageObject.SING_UP_LINK;
        break;
      case "Sign Up":
        element = RegisterUserPageObject.SING_UP_BUTTON;
        break;
      case "Log in":
        element = LoginPageObject.LOG_IN_LINK;
        break;
      case "Log In":
        element = LoginPageObject.LOG_IN_BUTTON;
        break;
      case "Log out":
        element = LoginPageObject.LOG_OUT_BUTTON;
        break;
      case "Laptops":
        element = BuyArticlesPageObject.LAPTOPS_BUTTON;
        break;
      case "Add to cart":
        element = BuyArticlesPageObject.ADD_TO_CART_BUTTON;
        break;
      case "Cart":
        element = BuyArticlesPageObject.CART_BUTTON;
        break;
    }
    clickElement(element);
  }

  public void writreOnField(String field) {
    switch (field) {
      case "Username":
        write(RegisterUserPageObject.USERNAME_INPUT, emailGenerated);
        break;
      case "Password":
        write(RegisterUserPageObject.PASSWORD_INPUT, passwordGenerated);
        break;
    }
  }

  public boolean checkAlert(String alertName) {
    return verifyAlert(alertName);
  }
}
