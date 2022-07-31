package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraCesionForwardPage;

import java.util.List;

public class GenerarOrdenCompraCesionForwardSteps {
    GenerarOrdenCompraCesionForwardPage generarOrdenCompraCesionForwardPage = new GenerarOrdenCompraCesionForwardPage();

    @And("^El proveedor introduce (.*) en el campo (.*)$")
    public void elProveedorIntroduceValor(String text, String field) {
        generarOrdenCompraCesionForwardPage.fillFieldOrder(text, field);
    }

    @And("^El proveedor escoge en (.*) la opcion (.*)$")
    public void elProveedorEscogeLaOpcion(String dropDownName, String option) {
        generarOrdenCompraCesionForwardPage.selectOptionInOrder(dropDownName, option);
    }

    @And("^El proveedor presiona el botón (.*)$")
    public void elProveedorPresionaBoton(String buttonName) {
        generarOrdenCompraCesionForwardPage.clickOnButtonOrder(buttonName);
    }

    @Then("^El proveedor verifica que se muestre (.*)$")
    public void elProveedorVisualiza(String text) {
        Assert.assertTrue(generarOrdenCompraCesionForwardPage.viewItem(text));
    }

    @And("^El proveedor percibe el boton (.*) (.*)$")
    public void validarEstadoBoton(String buttonName, String status) {
        switch (status) {
            case "Habilitado":
                Assert.assertTrue(generarOrdenCompraCesionForwardPage.verifyButtonOrder(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(generarOrdenCompraCesionForwardPage.verifyButtonOrder(buttonName));
                break;
        }
    }

    @And("^La plataforma no permite ingresar ningún valor que sea distinto a numérico$")
    public void checkAmount() {
        Assert.assertTrue(generarOrdenCompraCesionForwardPage.checkCorrectNumberAmount());
    }

    @And("^La plataforma no permite ingresar monto menor a 1.500.001$")
    public void checkMinorAmount() {
        Assert.assertTrue(generarOrdenCompraCesionForwardPage.checkCorrectMinorAmount());
    }

    @And("^La plataforma no permite ingresar monto mayor a 12 digitos incluídos 2 decimales$")
    public void checkMayorAmount() {
        Assert.assertTrue(generarOrdenCompraCesionForwardPage.checkCorrectMaximumAmount());
    }

    @And("^El proveedor presiona el campo desplegable de Gestion del forward y (.*) la opción (.*)$")
    public void verifySinCorredor(String verificarOpcion, String sOpcion) {
        switch (verificarOpcion){
            case "no se muestra":
                Assert.assertFalse(generarOrdenCompraCesionForwardPage.sinCorredorisNotDisplayed(sOpcion));
                break;
            case "se muestra":
                Assert.assertTrue(generarOrdenCompraCesionForwardPage.sinCorredorisNotDisplayed(sOpcion));
                break;
        }
    }

    @And("^Obtener datos de endpoint en (.*) con ruta (.*) y guardar valores en variables")
    public void getDataFromApiServicesAndSave(String sourceApi, String path, List<List<String>> t_table) {
        generarOrdenCompraCesionForwardPage.getDataFromApiServicesValidateCForward(sourceApi, path, t_table);
    }
}
