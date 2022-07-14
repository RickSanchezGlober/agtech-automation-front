package pages.proveedor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageobjects.proveedor.GenerarOrdenCompraCesionForwardPageObject;
import pages.BasePage;
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
                    text = "150000100";
                    log.info("===> Ingresando monto de crédito Cesión de Forward: " + text + " ===");
                }
                break;
        }
        write(element, text);
    }

    public void selectOptionInOrder(String dropDownName, String option) {
        Select dropDownList = null;
        switch (dropDownName) {
            case "tipo de convenio":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraCesionForwardPageObject.TIPO_CONVENIO_SELECT));
                break;
            case "gestión del forward":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraCesionForwardPageObject.GESTION_FORWARD_SELECT));
                break;
        }
        dropDownList.selectByVisibleText(option);
    }

    public void clickOnButtonOrder(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Simular Crédito":
                element = GenerarOrdenCompraCesionForwardPageObject.SIMULAR_CREDITO_FORWARD_BUTTON;
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
        }
        waitVisibility(element, "5");
        return isEnabled(element);
    }
}
