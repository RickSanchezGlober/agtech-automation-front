package steps.productor.autenticacion;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import pages.productor.autenticacion.LoginPage;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();

    @Given("^Se navega al portal New Agro (.*)$")
    public void iGoToNewAgroPortal(String rol) {
        loginPage.navigateTo(rol);
        String actualPageTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "Nera - La nueva era del Agro");
    }

    @Given("^Usuario logueado en el portal New Agro")
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

    @Given("^Se loguea con usuario (.*) y password (.*)$")
    public void loginUserPasswdAfter(String user, String password) {
        loginPage.loginWithUserPasswdAfter(user, password);
    }

    @Then("^Se (.*) y visualiza el mensaje (.*)$")
    public void elUsuarioPuedoLoguearse(String action, String message) {
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(action, message));
    }

    @Then("^Ocurre un error en el proceso de (.*)$")
    public void anErrorOccurs(String proceso) {
        loginPage.navigateToError("productor", proceso);
    }

    @Then("^Debería ver la página de error del (.*)$")
    public void verifyErrorPageDisplayed(String process) {
        Assert.assertTrue(loginPage.verifyErrorPageDisplayed(process));
    }

    @Then("^Después de darle click en el botón (.*) Debería ser redirigido a la página del (.*)")
    public void verifyRedirectErrorPage(String button, String action) {
        loginPage.clickOnButtonByName(button);
        Assert.assertTrue(loginPage.verifyMessageIsdisplayed(action, ""));
    }

    @Then("^Se espera que se deniegue el acceso con (.*)$")
    public void verifyAcessDenied(String reason) {
        Assert.assertTrue(loginPage.verifyAccesDenied(reason));
    }
}
