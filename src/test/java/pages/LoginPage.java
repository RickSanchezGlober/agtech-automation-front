package pages;

import pageObjects.LoginPageObject;

public class LoginPage extends BasePage {
  LoginPageObject loginPageObject = new LoginPageObject();

  public LoginPage() {
    super();
  }

  public void typeCorrectUserPassword() {
    write(loginPageObject.LOGIN_USERNAME, RegisterUserPage.emailGenerated);
    write(loginPageObject.LOGIN_PASSWORD, RegisterUserPage.passwordGenerated);
  }

  public boolean verifyUsernameIsdisplayed() {
    explicitWait(loginPageObject.PRODUCT_STORE_LOGO);
    return verifyVisibleText(
        loginPageObject.NAME_OF_USER, "Welcome " + RegisterUserPage.emailGenerated);
  }

  public boolean verifyUsernameIsNotDisplayed() {
    return verifyInvisibleText(loginPageObject.NAME_OF_USER);
  }
}
