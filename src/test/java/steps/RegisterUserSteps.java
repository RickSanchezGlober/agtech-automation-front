package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.RegisterUserPage;

public class RegisterUserSteps {
  RegisterUserPage registerUserPage = new RegisterUserPage();

  @Given("^Navigate to Demoblaze web$")
  public void navigateToDemozable() {
    registerUserPage.navigateTo();
  }

  @When("^Click on the button (.*)$")
  public void clickOnButton(String button) {
    registerUserPage.clickOnButton(button);
  }

  @And("Write (.*)$")
  public void writreOnField(String field) {
    registerUserPage.writreOnField(field);
  }

  @Then("The (.*) alert is displayed$")
  public void checkAlert(String alertName) {
    Assert.assertTrue(registerUserPage.checkAlert(alertName));
  }
}
