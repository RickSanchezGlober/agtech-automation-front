package steps.productor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import pages.productor.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("^Se navega al portal New Agro (.*)$")
    public void iGoToNewAgroPortal(String rol) {
        loginPage.navigateTo(rol);
        String actualPageTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "AgTech");
    }

    @Given("^Usuario logueado en el portal New Agro productor")
    public void loginFixedUser() {
        loginPage.loginWithUserPasswd("cristian.duque@globant.com", "Colombia123");
    }

    @And("^Se hace click sobre el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        loginPage.clickOnButtonByName(buttonName);
    }

    @Given("^Se ingresa con usuario (.*) y password (.*)$")
    public void loginUserPasswd(String user, String password) {
        loginPage.loginWithUserPasswd(user, password);
    }

    @Then("^Se (.*) y visualiza el mensaje (.*)$")
    public void elUsuarioPuedoLoguearse(String action,String message) {
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(action,message));
    }

    @Then("^Se espera que se deniegue el acceso con (.*)$")
    public void verifyAcessDenied(String reason) {
        Assert.assertTrue(loginPage.verifyAccesDenied(reason));
    }
}
