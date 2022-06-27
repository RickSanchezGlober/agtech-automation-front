package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraPage;

import java.util.List;

public class GenerarOrdenCompraSteps {
    GenerarOrdenCompraPage generarOrdenCompraPage = new GenerarOrdenCompraPage();
    public static String cuit = "";

    @And("^El proveedor hace click en el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        generarOrdenCompraPage.clickOnButtonByName(buttonName);
    }

    @And("^El proveedor ingresa (.*) en el campo (.*)$")
    public void fillField(String text, String field) {
        if (field.contains("Ingresá el CUIT")) {
            this.cuit = text;
        }
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

    @And("^El proveedor visualiza (.*) en la pantalla Identificación de cliente$")
    public void elProveedorVisualizaEnLaPantallaIdentificaciónDeCliente(String field) {
        Assert.assertTrue(generarOrdenCompraPage.checkFieldOnCustomerIDScreen(field));
    }

    @And("^Se lee el cuit en formato correcto")
    public void verifyCUITFormat() {
        Assert.assertTrue(generarOrdenCompraPage.verifyCUITFormat(cuit));
    }

    @And("^Recuperar datos de servicios api con ruta (.*) y guardar variables abajo$")
    public void findCustumerByCUIT(String path/*, List<List<String>> t_table*/) {
        generarOrdenCompraPage.validateCustumerByCUIT(cuit, path/*, t_table*/);
    }

}
