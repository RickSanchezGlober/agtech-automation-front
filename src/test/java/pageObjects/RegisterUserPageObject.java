package pageObjects;

import org.openqa.selenium.By;

public class RegisterUserPageObject {
  public static final By.ById SING_UP_LINK = new By.ById("signin2");
  public static final By.ById USERNAME_INPUT = new By.ById("sign-username");
  public static final By.ById PASSWORD_INPUT = new By.ById("sign-password");
  public static final By.ByXPath SING_UP_BUTTON =
      new By.ByXPath("//*[@id='signInModal']/div/div/div[3]/button[2]");
}
