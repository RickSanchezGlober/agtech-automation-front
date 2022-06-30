package pages.proveedor;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.productor.ListadoOrdenesPageObject;
import pageobjects.proveedor.GenerarOrdenCompraPageObject;
import pages.BasePage;
import steps.proveedor.GenerarOrdenCompraSteps;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


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
                waitVisibility(GenerarOrdenCompraPageObject.PRODUCTOR_ARROW, "10");
                element = GenerarOrdenCompraPageObject.PRODUCTOR_ARROW;
                break;
            case "Continuar":
                explicitWait(GenerarOrdenCompraPageObject.CONTINUAR_BUTTON);
                element = GenerarOrdenCompraPageObject.CONTINUAR_BUTTON;
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
            case "Descripción":
                element = GenerarOrdenCompraPageObject.DESCRIPCION_INPUT;
                if (text.contains("Válida")) {
                    text = DataGenerator.getText(1, 40, true, true);
                } else {
                    //Ver cual seria un descripcion no valida
                }
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
            case "Elegí el medio de pago con el cual el productor va a pagar la orden.":
                element = GenerarOrdenCompraPageObject.ELEGI_MEDIO_PAGO_TITLE;
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
            case "Buscar":
                element = GenerarOrdenCompraPageObject.BUSCAR_BUTTON;
                break;
        }
        return isEnabled(element);
    }

    public boolean checkNumberCharacters(String quantity) {
        int intQuantity = Integer.parseInt(quantity);
        String randomText = DataGenerator.getText(intQuantity + 1, intQuantity + 2, true, true);
        By webElement = GenerarOrdenCompraPageObject.DESCRIPCION_INPUT;
        write(webElement, randomText);
        return getAttribute(webElement, "value").length() == intQuantity;
    }

    public boolean checkFieldOnCustomerIDScreen(String field) {
        explicitWait(GenerarOrdenCompraPageObject.BUSCAR_BUTTON);
        By element = null;
        switch (field) {
            case "Ingresá el CUIT":
                element = GenerarOrdenCompraPageObject.INGRESA_EL_CUIT_LABEL;
                break;
            case "Escribí 11 números":
                element = GenerarOrdenCompraPageObject.ESCRIBI_11_NUMEROS_TEXT;
                break;
            case "Productor Asociado":
                if (RestAssuredExtension.response.statusCode() == 200) {
                    element = GenerarOrdenCompraPageObject.DATOS_PRODUCTOR_ASOCIADO_TEXT;
                    String cuit = GenerarOrdenCompraSteps.cuit;
                    field = "CUIT " + cuit.substring(0, 2) + "-" + cuit.substring(2, 10) + "-" + cuit.substring(10, 11);
                    break;
                } else {
                    log.info("Productor no disponible en la Base de Datos local");
                }
        }
        return verifyVisibleText(element, field);
    }

    public boolean verifyCUITFormat(String cuit) {
        String cuitWithFormat = getAttribute(GenerarOrdenCompraPageObject.INGRESA_EL_CUIT_INPUT, "value");
        cuit = cuit.substring(0, 2) + "-" + cuit.substring(2, 10) + "-" + cuit.substring(10, 11);
        return cuit.equals(cuitWithFormat);
    }

    public void validateCustumerByCUIT(String sourceApi, String path, String cuit/*, List<List<String>> table*/) {
        log.info(path + cuit);
        RestAssuredExtension.getMethod(sourceApi, path + cuit);
        RestAssuredExtension.response.getBody().prettyPrint();
    }

    public boolean checkPaymentMethods(String paymentMethods) {
        explicitWait(GenerarOrdenCompraPageObject.ELEGI_MEDIO_PAGO_TITLE);
        List<WebElement> elementList = null;
        boolean result = false;
        switch (paymentMethods) {
            case "Medios de Pagos Disponibles":
                elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de Medio de pago":
                elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Crédito a sola firma")
                        || elementList.get(1).getText().equals("Crédito a sola firma"));
                break;
            case "Descripción del Medio de pago":
                elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_DESCRIPTION_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Logo de la entidad bancaria":
                elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_BANK_LOGO_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de la entidad bancaria":
                elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_BANK_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Banco Galicia")
                        || elementList.get(1).getText().equals("Banco Galicia"));
                break;
        }
        return result;
    }
}
