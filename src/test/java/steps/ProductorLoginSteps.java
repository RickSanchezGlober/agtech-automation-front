package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import pages.ProductorLoginPage;

public class ProductorLoginSteps {
    ProductorLoginPage productorLoginPage = new ProductorLoginPage();

    @Given("^Se navega al portal Galicia Rural (.*)$")
    public void iGoToRuralGaliciaPortal(String rol) {
        productorLoginPage.navigateTo(rol);
        String actualPageTitle = productorLoginPage.getPageTitle();
        Assert.assertEquals(actualPageTitle, "AgTech");
    }

    @And("^Se hace click sobre el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        productorLoginPage.clickOnButtonByName(buttonName);
    }

    @And("^Se ingresa con usuario (.*) y password (.*)$")
    public void loginUserPasswd(String user, String password) {
        productorLoginPage.loginWithUserPasswd(user, password);
    }

    @Then("^Se visualiza el mensaje (.*)$")
    public void elUsuarioPuedoLoguearse(String message) {
        Assert.assertTrue(productorLoginPage.verifyMessageIsdisplayed(message));
    }
}
