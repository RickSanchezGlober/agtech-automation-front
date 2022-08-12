package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraSolaFirmaPage;

import java.util.List;

public class GenerarOrdenCompraSolaFirmaSteps {
    GenerarOrdenCompraSolaFirmaPage generarOrdenCompraSolaFirmaPage = new GenerarOrdenCompraSolaFirmaPage();
    public static String cuit = "";

    @And("^El proveedor hace click en el botón (.*)$")
    public void clicOnButtonByName(String buttonName) {
        generarOrdenCompraSolaFirmaPage.clickOnButtonByName(buttonName);
    }

    @And("^El proveedor ingresa (.*) en el campo (.*)$")
    public void fillField(String text, String field) {
        if (field.contains("Ingresá el CUIT")) {
            this.cuit = text;
        }
        generarOrdenCompraSolaFirmaPage.fillField(text, field);
    }

    @Then("^El proveedor visualiza (.*) en la pantalla detalle de la orden$")
    public void verifyOrderDetails(String text) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.verifyOrderDetails(text));
    }

    @And("^El proveedor visualiza el boton (.*) (.*)$")
    public void verifyButtonState(String buttonName, String status) {
        switch (status) {
            case "Habilitado":
                Assert.assertTrue(generarOrdenCompraSolaFirmaPage.verifyButtonState(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(generarOrdenCompraSolaFirmaPage.verifyButtonState(buttonName));
                break;
        }
    }

    @And("^El proveedor no puede ingresar mas de (.*) caracteres$")
    public void checkNumberCharacters(String quantity) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkNumberCharacters(quantity));
    }

    @And("^El proveedor visualiza (.*) en la pantalla Identificación de cliente$")
    public void elProveedorVisualizaEnLaPantallaIdentificaciónDeCliente(String field) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkFieldOnCustomerIDScreen(field));
    }

    @And("^Se lee el cuit en formato correcto")
    public void verifyCUITFormat() {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.verifyCUITFormat(cuit));
    }

    @And("^Recuperar datos de servicios api (.*) con ruta (.*) y guardar variables abajo$")
    public void getDataFromApiServices(String sourceApi, String path, List<List<String>> t_table) {
        generarOrdenCompraSolaFirmaPage.getDataFromApiServicesValidation(sourceApi, path, cuit, t_table);
    }

    @And("^El proveedor observa (.*)$")
    public void checkPaymentMethods(String paymentMethods) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkPaymentMethods(paymentMethods));
    }

    @And("^El proveedor puede ver Datos del Productor Asociado$")
    public void validateProductorName(List<List<String>> t_table) {
        generarOrdenCompraSolaFirmaPage.validateProductorName(t_table);
    }

    @And("^El proveedor seleciona medio de pago (.*)$")
    public void selectPaymentMethod(String paymentMethod) {
        generarOrdenCompraSolaFirmaPage.selectPaymentMethod(paymentMethod);
    }

    @And("^El proveedor selecciona en (.*) opcion (.*)$")
    public void selectOptionRateSubsidy(String dropDownName, String option) {
        generarOrdenCompraSolaFirmaPage.selectOptionFromDropDownList(dropDownName, option);
    }

    @Then("^Validar datos de servicios api (.*) con ruta (.*) con body (.*)")
    public void getDataFromApiServicesSimulation(String sourceApi, String path, String body, List<List<String>> t_table) {
        generarOrdenCompraSolaFirmaPage.getDataFromApiServicesSimulation(sourceApi, path, body, t_table);
    }

    @And("^Validar Nombre del Productor$")
    public void validateProductor(List<List<String>> t_table) {
        generarOrdenCompraSolaFirmaPage.validateProductor(t_table);
    }

    @And("^Se borra el campo (.*)$")
    public void validateProductor(String field) {
        generarOrdenCompraSolaFirmaPage.deleteField(field);
    }

    @Then("^El proveedor no visualiza el boton (.*)$")
    public void buttonIsNotDisplayed(String buttonName) {
        Assert.assertFalse(generarOrdenCompraSolaFirmaPage.buttonIsNotDisplayed(buttonName));
    }

    @And("^El proveedor cambia en (.*) opcion (.*)$")
    public void changeOptionRateSubsidy(String dropDownName, String option) {
        generarOrdenCompraSolaFirmaPage.changeOptionRateSubsidy(dropDownName, option);
    }

    @And("^No se puede ingresar mas de (.*) digitos en el campo (.*)$")
    public void checkNumberQuantity(String quantity, String field) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkNumberQuantity(quantity, field));
    }
    @And("^Imposible escribir caracteres especiales en el campo (.*)$")
    public void checkNumberQuantity( String field) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkWritingSpecialCharacters(field));
    }
    @And("^Se permite ingresar hasta (.*) decimales$")
    public void checkEnterDecimalPLaces(String quantity) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkEnterDecimalPLaces(quantity));
    }
    @And("^El proveedor ve el mensaje de error (.*)$")
    public void checkErrorMessage(String messageError) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.checkErrorMessage(messageError));
    }
    @And("^Verificar pantalla de error si la conexion con el MS customer-validation no se realiza correctamente$")
    public void chekErrorScreen() {
        generarOrdenCompraSolaFirmaPage.chekErrorScreen(cuit);
    }
    @And("^Se visualiza pantalla de error Cuit no autorizado$")
    public void chekErrorScreenUnauthorisedCuit() {
        Assert.assertTrue(generarOrdenCompraSolaFirmaPage.chekErrorScreenUnauthorisedCuit());
    }
    @And("^Verificar pantalla de error si la conexion con el MS agreement no se realiza correctamente$")
    public void chekErrorScreenPaymentMethod() {
        generarOrdenCompraSolaFirmaPage.chekErrorScreenPaymentMethod();
    }
}
