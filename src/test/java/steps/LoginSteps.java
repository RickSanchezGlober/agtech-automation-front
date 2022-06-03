package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {
    public static LoginPage loginPage = new LoginPage();

    @Given("Se ingresa al portal Galicia Rural")
    public void navigateToGaliciaRural() {
        loginPage.navigateTo();
    }

    @And("Se hace click sobre el boton (.*)$")
    public void clicnOnButtonName(String buttonName) {
        loginPage.clickOnButton(buttonName);
    }

    @And("Se loguea con usuario (.*) y password (.*)$")
    public void loginUserPasswd(String user,String password) {
        loginPage.loginWithUserPasswd(user,password);
    }

    @Then("El usuario pudo loguearse")
    public void elUsuarioPuedoLoguearse() {
        Assert.assertTrue(loginPage.verifyProductorNameIsdisplayed());
    }
}
