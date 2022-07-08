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

    @And("^Recuperar datos de servicios api (.*) con ruta (.*) y guardar variables abajo$")
    public void getDataFromApiServices(String sourceApi, String path, List<List<String>> t_table) {
        generarOrdenCompraPage.getDataFromApiServicesValidation(sourceApi, path, cuit, t_table);
    }

    @And("^El proveedor observa (.*)$")
    public void checkPaymentMethods(String paymentMethods) {
        Assert.assertTrue(generarOrdenCompraPage.checkPaymentMethods(paymentMethods));
    }

    @And("^El proveedor puede ver Datos del Productor Asociado$")
    public void validateProductorName(List<List<String>> t_table) {
        generarOrdenCompraPage.validateProductorName(t_table);
    }

    @And("^El proveedor seleciona medio de pago (.*)$")
    public void selectPaymentMethod(String paymentMethod) {
        generarOrdenCompraPage.selectPaymentMethod(paymentMethod);
    }

    @And("^El proveedor selecciona en (.*) opcion (.*)$")
    public void selectOptionRateSubsidy(String dropDownName, String option) {
        generarOrdenCompraPage.selectOptionFromDropDownList(dropDownName, option);
    }

    @And("^Recuperar datos de servicios api (.*) con ruta (.*) con body (.*)")
    public void getDataFromApiServicesSimulation(String sourceApi, String path, String body, List<List<String>> t_table) {
        generarOrdenCompraPage.getDataFromApiServicesSimulation(sourceApi, path, body, t_table);
    }
}
