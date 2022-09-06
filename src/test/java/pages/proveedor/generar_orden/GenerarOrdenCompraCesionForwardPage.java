package pages.proveedor.generar_orden;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraCesionForwardPageObject;
import pages.BasePage;
import utils.DataGenerator;

import java.util.List;


public class GenerarOrdenCompraCesionForwardPage extends BasePage {

    public GenerarOrdenCompraCesionForwardPage() {
        super();
    }

    public void fillFieldOrder(String text, String field) {
        explicitWait(GenerarOrdenCompraCesionForwardPageObject.NUEVA_ORDEN_PAGO_LAYOUT);
        By element = null;
        switch (field) {
            case "Ingresá el monto del crédito":
                element = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
                if (text.contains("mayor a $1.500.000")) {
                    text = "150000150";
                    log.info("===> Ingresando monto de crédito Cesión de Forward: " + text + " ===");
                }
                break;
        }
        write(element, text);
    }

    public void selectOptionInOrder(String dropDownName, String option) {
        Select dropDownList = null;
        String sOption = "";
        String[] arrOptions;
        String selectedOption = "";

        if(getScenarioContextVariables(option) == null){
            selectedOption = option;
        }else{
            sOption = getScenarioContextVariables(option);
            arrOptions = sOption.split(",");
            selectedOption = arrOptions[arrOptions.length - 1];
        }

        switch (dropDownName) {
            case "tipo de convenio":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraCesionForwardPageObject.TIPO_CONVENIO_SELECT));
                break;
            case "gestión del forward":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraCesionForwardPageObject.GESTION_FORWARD_SELECT));
                break;
        }

        dropDownList.selectByVisibleText(selectedOption);
    }

    public void clickOnButtonOrder(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Simular Crédito":
                element = GenerarOrdenCompraCesionForwardPageObject.SIMULAR_CREDITO_FORWARD_BUTTON;
                break;
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraCesionForwardPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
        }
        waitVisibility(element, "30");
        click(element);
    }

    public boolean viewItem(String text) {
        By element = null;
        List<WebElement> elementList = null;
        boolean result = false;
        switch (text) {
            case "Elegí el medio de pago con el cual el productor va a pagar la orden.":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.ELEGIR_MEDIO_PAGO_TEXT, "2");
                element = GenerarOrdenCompraCesionForwardPageObject.ELEGIR_MEDIO_PAGO_TEXT;
                result = verifyVisibleText(element, text);
                break;
            case "Ingresá el Monto del Crédito":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT, "5");
                element = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
                result = isDisplayed(element);
                break;
            case "Tipo de Convenio":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.TIPO_CONVENIO_SELECT, "5");
                element = GenerarOrdenCompraCesionForwardPageObject.TIPO_CONVENIO_SELECT;
                result = isDisplayed(element);
                break;
            case "Medios de Pagos Disponibles":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.CARD_CONTAINER, "2");
                elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.CARD_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de Medio de pago":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.CARD_NAME_CONTAINER, "2");
                elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.CARD_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Cesión de forward")
                        || elementList.get(1).getText().equals("Cesión de forward"));
                break;
            case "Descripción del Medio de pago":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.CARD_DESCRIPTION_CONTAINER, "2");
                elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.CARD_DESCRIPTION_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Logo de la entidad bancaria":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.CARD_BANK_LOGO_CONTAINER, "2");
                elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.CARD_BANK_LOGO_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de la entidad bancaria":
                waitVisibility(GenerarOrdenCompraCesionForwardPageObject.CARD_BANK_NAME_CONTAINER, "2");
                elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.CARD_BANK_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Banco Galicia")
                        || elementList.get(1).getText().equals("Banco Galicia"));
                break;
        }
        return result;
    }

    public boolean verifyButtonOrder(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Simular Crédito":
                element = GenerarOrdenCompraCesionForwardPageObject.SIMULAR_CREDITO_FORWARD_BUTTON;
                break;
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraCesionForwardPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Enviar orden":
                element = GenerarOrdenCompraCesionForwardPageObject.ENVIAR_ORDEN_COMPRA_BUTTON;
                break;
        }
        waitVisibility(element, "5");
        return isEnabled(element);
    }

    public boolean checkCorrectNumberAmount() {
        By webElement = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
        clear(webElement);
        String specialValue = DataGenerator.getPassword(8, 12, false, true, false);
        write(webElement, specialValue);
        log.info("===> checkCorrectNumberAmount: <" + getAttribute(webElement, "value") + "> ===");
        return getAttribute(webElement, "value").length() == 0;
    }

    public boolean checkCorrectMinorAmount() {
        By webElement = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
        By webElementError = GenerarOrdenCompraCesionForwardPageObject.ERROR_ICON;
        clear(webElement);
        //Monto no debería ser aceptado porque el valor mínimo aceptado es 1.500.001,00
        String specialValue = "1500000,99";
        write(webElement, specialValue);
        //Si aparece el ícono de advertencia, es porque se muestra en pantalla el mensaje en color rojo
        log.info("===> checkCorrectMinorAmount: <" + getAttribute(webElement, "value") + "> ===");
        return isDisplayed(webElementError);
    }

    public boolean checkCorrectMaximumAmount() {
        By webElement = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
        clear(webElement);
        //No debe aceptar 13 digitos, lo maximo son 12 dígitos permitido
        String specialValue = "1234567890123456";
        write(webElement, specialValue);
        //El valor esperado es $ 1.234.567.890,12 y no $ 12.345.678.901,23
        log.info("===> checkCorrectMaximumAmount: <" + getAttribute(webElement, "value") + "> ===");
        return getText(webElement).length() == 18;
    }

    public Boolean sinCorredorisNotDisplayed(String option) {
        boolean result = false;
        waitVisibility(GenerarOrdenCompraCesionForwardPageObject.GESTION_FORWARD_SELECT, "30");
        List<WebElement> elementList = driver.findElements(GenerarOrdenCompraCesionForwardPageObject.GESTION_FORWARD_SELECT);
        result = (elementList.size() > 0)
                && (elementList.get(0).getText().contains(option));

        log.info("===> Contiene opción: <" + option + ">? ---->  " +result+ " ===");

        return result;
    }

    public void getDataFromApiServicesValidateCForward(String sourceApi, String path, List<List<String>> table) {
        log.info("===> Ejecutando MS en <" + sourceApi + "> y path: <" + path + "> ===");
        getDataFromApiServices(path, sourceApi, table);
    }

    public boolean buttonIsNotDisplayedYet(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraCesionForwardPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Enviar orden":
                element = GenerarOrdenCompraCesionForwardPageObject.ENVIAR_ORDEN_COMPRA_BUTTON;
                break;
        }
        return waitVisibility(element, "1");
    }

    public void cleanField(String field) {
        By element = null;
        switch (field) {
            case ("Ingresá el monto del crédito"):
                element = GenerarOrdenCompraCesionForwardPageObject.MONTO_CREDITO_INPUT;
                break;
        }
        int cantChar = getAttribute(element, "value").length() - 1;
        for (int i = 0; i < cantChar; i++) {
            sendBackSpace(element);
        }
    }
}
