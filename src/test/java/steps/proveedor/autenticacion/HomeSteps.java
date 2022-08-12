package steps.proveedor.autenticacion;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageobjects.proveedor.autenticacion.HomePageObject;
import pages.proveedor.autenticacion.HomePage;

public class HomeSteps {
    HomePage homePage = new HomePage();

    @And("^Ingresa la contraseña actual (.*)$")
    public void elUsuarioIngresaContrasenaActual(String password) {
        homePage.enterActualPassword(password);
    }

    @And("^Ingresa una nueva contraseña valida (.*)$")
    public void elUsuarioIngresaContrasenaValida(String password) {
        homePage.enterNewPassword(password);
    }

    @Then("^Se visualiza el mensaje (.*)$")
    public void seCambioLaContraseña(String message) {
        Assert.assertTrue(homePage.verifyMessageIsdisplayed("cambio",message));
        homePage.click(HomePageObject.LOGIN_BUTTON);

    }
}
