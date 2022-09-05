package steps.proveedor.autenticacion;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.autenticacion.LoginPage;


public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Then("^El proveedor visualiza el mensaje (.*)$")
    public void verifyMessageWellcome(String message) {
        Assert.assertTrue(loginPage.verifyMessageWellcome(message));
    }

    @Given("^El proveedor ingresa con usuario (.*) y password (.*)$")
    public void loginUserPasswd(String user, String password) {
        loginPage.loginWithUserPasswd(user, password);
    }

    @Then("^Se deniegua el acceso con (.*)$")
    public void verifyAcessDenied(String reason) {
        Assert.assertTrue(loginPage.verifyAccesDenied(reason));
    }

    @Given("^Usuario logueado en el portal Nera")
    public void loginFixedUser() {
        loginPage.loginWithUserPasswd("cristian.duque@globant.com", "Colombia123");
    }

    @Then("^Se produce un error en el proceso de (.*)$")
    public void errorOccurs(String proceso) {
        loginPage.navigateToError("proveedor", proceso);
    }

    @Then("^El proveedor se (.*) y visualiza el mensaje (.*)$")
    public void elUsuarioPuedoLoguearse(String action, String message) {
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(action, message, "proveedor"));
    }

    @Then("^El proveedor debería ver la página de error del (.*)$")
    public void verifyErrorPageDisplayed(String process) {
        Assert.assertTrue(loginPage.verifyErrorPageDisplayed(process));
    }

    @Then("^El proveedor después de darle click en el botón (.*) Debería ser redirigido a la página del (.*)")
    public void verifyRedirectErrorPage(String button, String action) {
        loginPage.clickOnButtonByName(button);
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(action, "", "proveedor"));
    }
    @Given("^El proveedor se loguea con usuario (.*) y password (.*)$")
    public void loginUserPasswdAfter(String user, String password) {
        loginPage.loginWithUserPasswdAfter(user, password);
    }
    @Given("^Se navega al portal Nera (.*)$")
    public void iGoToNewAgroPortal(String rol) {
        loginPage.navigateTo(rol);
        String actualPageTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "Nera - La nueva era del Agro");
    }
}
