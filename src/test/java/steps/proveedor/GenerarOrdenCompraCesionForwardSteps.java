package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraCesionForwardPage;

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

    @Then("^El proveedor visualiza (.*)$")
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

}
