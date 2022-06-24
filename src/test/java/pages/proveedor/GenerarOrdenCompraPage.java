package pages.proveedor;

import org.openqa.selenium.By;
import pageobjects.proveedor.GenerarOrdenCompraPageObject;
import pages.BasePage;
import utils.DataGenerator;


public class GenerarOrdenCompraPage extends BasePage {

    public GenerarOrdenCompraPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Crear Orden":
                explicitWait(GenerarOrdenCompraPageObject.CREAR_ORDEN_BUTTON);
                element = GenerarOrdenCompraPageObject.CREAR_ORDEN_BUTTON;
                break;
            case "Buscar":
                explicitWait(GenerarOrdenCompraPageObject.BUSCAR_BUTTON);
                element = GenerarOrdenCompraPageObject.BUSCAR_BUTTON;
                break;
            case "del Productor encontrado":
                waitVisibility(GenerarOrdenCompraPageObject.PRODUCTOR_ARROW, "7");
                element = GenerarOrdenCompraPageObject.PRODUCTOR_ARROW;
                break;
        }
        click(element);
    }

    public void fillField(String text, String field) {
        explicitWait(GenerarOrdenCompraPageObject.NUEVA_ORDEN_PAGO_HEADER);
        By element = null;
        switch (field) {
            case "Ingresá el CUIT":
                element = GenerarOrdenCompraPageObject.INGRESA_EL_CUIT_INPUT;
                break;
        }
        write(element, text);
    }

    public boolean verifyOrderDetails(String text) {
        waitVisibility(GenerarOrdenCompraPageObject.DESCRIPCION_LABEL, "2");
        By element = null;
        switch (text) {
            case "Descripción":
                element = GenerarOrdenCompraPageObject.DESCRIPCION_LABEL;
                break;
            case "Describe el detalle de la orden de pago para el productor":
                element = GenerarOrdenCompraPageObject.DESCRIBE_DETALLE_ORDEN_TITLE;
                break;
            case "Hasta 40 caracteres":
                element = GenerarOrdenCompraPageObject.HASTA_40_CARACTERES_TEXT;
                break;
        }
        return verifyVisibleText(element, text);
    }

    public boolean verifyButtonState(String buttonName) {
        explicitWait(GenerarOrdenCompraPageObject.DESCRIPCION_LABEL);
        By element = null;
        switch (buttonName) {
            case "Continuar":
                element = GenerarOrdenCompraPageObject.CONTINUAR_BUTTON;
                break;
        }
        return isEnabled(element);
    }

    public boolean checkNumberCharacters(String quantity) {
        boolean result = false;
        int intQuantity = Integer.parseInt(quantity);
        String randomText = DataGenerator.getText(intQuantity + 1, intQuantity + 2, true, true);
        By webElement = GenerarOrdenCompraPageObject.DESCRIPCION_INPUT;
        write(webElement, randomText);
        result = getAttribute(webElement, "value").length() == intQuantity;
        return result;
    }
}
