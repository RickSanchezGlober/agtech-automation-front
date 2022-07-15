package pages.proveedor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaPageObject;
import pages.BasePage;
import steps.proveedor.GenerarOrdenCompraSolaFirmaSteps;
import utils.DataGenerator;

import java.util.List;


public class GenerarOrdenCompraSolaFirmaContactoPage extends BasePage {

    public GenerarOrdenCompraSolaFirmaContactoPage() {
        super();
    }

    public static String fullNameContactGenereted = "";
    public static String emailContactGenereted = "";
    public static String areaCodeGenereted = "";
    public static String cellNumberGenereted = "";

    public boolean checkScreenContactData(String fieldText) {
        log.info("Verificando que se muestre '" + fieldText + "' en la pantalla Información del contacto");
        waitVisibility(GenerarOrdenCompraSolaFirmaContactoPageObject.DATOS_CONTACTO_TITLE, "2");
        boolean result = false;
        By element = null;
        if (fieldText.contains("Datos de contacto de quien recibirá")) {
            element = GenerarOrdenCompraSolaFirmaContactoPageObject.DATOS_CONTACTO_TITLE;
            result = verifyVisibleText(element, fieldText);
        } else {
            List<WebElement> elementList = driver.findElements(GenerarOrdenCompraSolaFirmaContactoPageObject.DATOS_CONTACTO_LABEL_CONTAINER);
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().equalsIgnoreCase(fieldText)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public boolean checkButtonState(String buttonName) {
        explicitWait(GenerarOrdenCompraSolaFirmaContactoPageObject.DATOS_CONTACTO_TITLE);
        By element = null;
        switch (buttonName) {
            case "Continuar":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CONTINUAR_BUTTON;
                break;
            case "Enviar orden de compra":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.ENVIAR_ORDEN_COMPRA_BUTTON;
                break;
        }
        return isEnabled(element);
    }

    public void fillInField(String field, String text) {
        explicitWait(GenerarOrdenCompraSolaFirmaContactoPageObject.DATOS_CONTACTO_TITLE);
        By element = null;
        switch (field) {
            case "Nombre y Apellido":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NOMBRE_APELLIDO_INPUT;
                if (text.contains("Válidos")) {
                    text = DataGenerator.getFullName();
                    this.fullNameContactGenereted = text;
                }
                break;
            case "Correo electrónico":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CORREO_ELECTRONICO_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getEmail();
                } else if (text.contains("Inválido")) {
                    text = DataGenerator.getWrongEmail();
                }
                this.emailContactGenereted = text;
                break;
            case "Cód de área":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CODIGO_AREA_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getCellPhoneNumber(4);
                    areaCodeGenereted = text;
                } else if (text.contains("Inválido")) {
                    text = DataGenerator.getCellPhoneNumber(6);
                }
                break;
            case "Número de celular":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NUMERO_CELULAR_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getCellPhoneNumber(8);
                    cellNumberGenereted = text;
                } else if (text.contains("Inválido")) {
                    text = DataGenerator.getCellPhoneNumber(10);
                }
                break;
        }
        log.info("LLenando el campo '" + field + "' con valor '" + text + "'");
        clear(element);
        write(element, text);
    }

    public boolean checkNumberCharactersContactData(String quantity, String field) {
        int intQuantity = Integer.parseInt(quantity);
        By element = null;
        switch (field) {
            case "Nombre y Apellido":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NOMBRE_APELLIDO_INPUT;
                String randomText = DataGenerator.getText(intQuantity + 1, intQuantity + 2, true, true);
                clear(element);
                write(element, randomText);
                break;
            case "Cód de área":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CODIGO_AREA_INPUT;
                String randomNumbersCod = DataGenerator.getCellPhoneNumber(intQuantity + 1);
                clear(element);
                write(element, randomNumbersCod);
                break;
            case "Número de celular":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NUMERO_CELULAR_INPUT;
                String randomNumbersNo = DataGenerator.getCellPhoneNumber(intQuantity + 1);
                clear(element);
                write(element, randomNumbersNo);
                break;
        }
        log.info("Intentando ingresar mas de '" + quantity + "' caracteres en el campo '" + field + "'");
        return getAttribute(element, "value").length() == intQuantity;
    }

    public boolean checkConfirmationScreenContactData(String fieldText) {
        log.info("Verificando que se muestre '" + fieldText + "' en la pantalla Confirmación");
        By titleElement = GenerarOrdenCompraSolaFirmaContactoPageObject.REVISA_SOLICITUD_COMPLETA_TITLE;
        By containerElement = GenerarOrdenCompraSolaFirmaContactoPageObject.SOLICITUD_COMPLETA_CONTAINER;
        String textFromUI = "";
        waitVisibility(titleElement, "30");
        switch (fieldText) {
            case "Revisá que la solicitud esté completa":
                textFromUI = driver.findElement(titleElement).getText();
                break;
            case "Información del cliente":
                textFromUI = driver.findElement(containerElement).getText().replaceAll("\n", " ");
                String cuit = GenerarOrdenCompraSolaFirmaSteps.cuit;
                fieldText = "CUIT " + cuit.substring(0, 2) + "-" + cuit.substring(2, 10) + "-" + cuit.substring(10, 11);
                break;
            case "Detalles de la orden":
                textFromUI = driver.findElement(containerElement).getText().replaceAll("\n", " ");
                fieldText = fieldText + " Descripción " + GenerarOrdenCompraSolaFirmaPage.orderDescription;
                break;
            case "Información de contacto":
                textFromUI = driver.findElement(containerElement).getText().replaceAll("\n", " ");
                fieldText = fieldText + " Nombre y Apellido " + fullNameContactGenereted
                        + " Correo electrónico " + emailContactGenereted
                        + " Número de celular " + areaCodeGenereted + " " + cellNumberGenereted;
                break;
            case "Medio de pago":
                textFromUI = driver.findElement(containerElement).getText().replaceAll("\n", " ");
                fieldText = fieldText + " Banco Banco Galicia "
                        + fieldText + " " + GenerarOrdenCompraSolaFirmaPage.paymentMethod;
                break;
        }
        return textFromUI.contains(fieldText);
    }

    public void clickOnButtonConfirmationScreen(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Volver":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.VOLVER_BUTTON;
                break;
        }
        waitVisibility(element, "5");
        click(element);
    }

    public boolean buttonIsNotDisplayedConfirmationScreen(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Enviar orden de compra":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.ENVIAR_ORDEN_COMPRA_BUTTON;
                break;

        }
        return waitVisibility(element, "2");
    }
}
