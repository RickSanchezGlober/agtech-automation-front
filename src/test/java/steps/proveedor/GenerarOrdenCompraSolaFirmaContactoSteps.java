package steps.proveedor;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.proveedor.GenerarOrdenCompraSolaFirmaContactoPage;
import pages.proveedor.GenerarOrdenCompraSolaFirmaPage;

import java.util.List;

public class GenerarOrdenCompraSolaFirmaContactoSteps {
    GenerarOrdenCompraSolaFirmaContactoPage generarOrdenCompraSolaFirmaContactoPage = new GenerarOrdenCompraSolaFirmaContactoPage();

    @And("^Se muestra la pantalla para ingresar los datos del contacto (.*)$")
    public void checkScreenContactData(String fieldText) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaContactoPage.checkScreenContactData(fieldText));
    }

    @And("^El proveedor visualiza el botón (.*) (.*)$")
    public void checkButtonState(String buttonName, String status) {
        switch (status) {
            case "Habilitado":
                Assert.assertTrue(generarOrdenCompraSolaFirmaContactoPage.checkButtonState(buttonName));
                break;
            case "Deshabilitado":
                Assert.assertFalse(generarOrdenCompraSolaFirmaContactoPage.checkButtonState(buttonName));
                break;
        }
    }
    @And("^Se llena el campo (.*) con valor (.*)$")
    public void fillInField(String field, String text) {
        generarOrdenCompraSolaFirmaContactoPage.fillInField(field, text);
    }
    @And("^El proveedor no puede ingresar más de (.*) caracteres en el campo (.*)$")
    public void checkNumberCharactersContactData(String quantity,String field) {
        Assert.assertTrue(generarOrdenCompraSolaFirmaContactoPage.checkNumberCharactersContactData(quantity,field));
    }
}
