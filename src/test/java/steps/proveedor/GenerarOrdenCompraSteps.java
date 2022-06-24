package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraPage;

public class GenerarOrdenCompraSteps {
    GenerarOrdenCompraPage generarOrdenCompraPage = new GenerarOrdenCompraPage();

    @And("^El proveedor hace click en el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        generarOrdenCompraPage.clickOnButtonByName(buttonName);
    }

    @And("^Se ingresa (.*) en el campo (.*)$")
    public void fillField(String text, String field) {
        generarOrdenCompraPage.fillField(text, field);
    }

    @Then("^El proveedor visualiza (.*) en la pantalla detalle de la orden$")
    public void verifyOrderDetails(String text) {
        Assert.assertTrue(generarOrdenCompraPage.verifyOrderDetails(text));
    }

    @And("^El proveedor visualiza el boton (.*) (.*)$")
    public void verifyButtonState(String buttonName, String status) {
        switch (status) {
            case "Habilitado":
                Assert.assertTrue(generarOrdenCompraPage.verifyButtonState(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(generarOrdenCompraPage.verifyButtonState(buttonName));
                break;
        }
    }
    @And("^El proveedor no puede ingresar mas de (.*) caracteres$")
    public void checkNumberCharacters(String quantity) {
        Assert.assertTrue(generarOrdenCompraPage.checkNumberCharacters(quantity));
    }

}
