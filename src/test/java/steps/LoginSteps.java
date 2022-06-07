package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import pages.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("^Se navega al portal Galicia Rural$")
    public void iGoToGoogle() {
        loginPage.navigateTo();
        String actualPageTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "AgTech");
    }
    @And("^Se hace click sobre el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        loginPage.clickOnButtonByName(buttonName);
    }
    @And("^Se ingresa con usuario (.*) y password (.*)$")
    public void loginUserPasswd(String user,String password) {
        loginPage.loginWithUserPasswd(user,password);
    }
    @Then("^Se visualiza el mensaje (.*)$")
    public void elUsuarioPuedoLoguearse(String message) {
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(message));
    }
}
