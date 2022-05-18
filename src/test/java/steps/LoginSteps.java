package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.LoginPage;

public class LoginSteps {
  LoginPage loginPage = new LoginPage();

  @Given("^The user complete the fields with the correct data for the login$")
  public void typeUserPassword() {
    loginPage.typeCorrectUserPassword();
  }

  @Then("^Verify the username (.*) displayed$")
  public void verifyUsernameIsdisplayed(String flag) {
    switch (flag) {
      case "is":
        Assert.assertTrue(loginPage.verifyUsernameIsdisplayed());
        break;
      case "is not":
        Assert.assertTrue(loginPage.verifyUsernameIsNotDisplayed());
        break;
    }
  }
}
