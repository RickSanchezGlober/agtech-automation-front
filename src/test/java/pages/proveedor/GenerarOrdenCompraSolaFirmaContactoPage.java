package pages.proveedor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaPageObject;
import pages.BasePage;
import utils.DataGenerator;

import javax.xml.crypto.Data;
import java.util.List;


public class GenerarOrdenCompraSolaFirmaContactoPage extends BasePage {

    public GenerarOrdenCompraSolaFirmaContactoPage() {
        super();
    }


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
                }
                break;
            case "Correo electrónico":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CORREO_ELECTRONICO_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getEmail();
                } else if (text.contains("Inválido")) {
                    text = DataGenerator.getWrongEmail();
                }
                break;
            case "Cód de área":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.CODIGO_AREA_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getCellPhoneNumber(4);
                } else if (text.contains("Inválido")) {
                    text = DataGenerator.getCellPhoneNumber(6);
                }
                break;
            case "Número de celular":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NUMERO_CELULAR_INPUT;
                if (text.contains("Válido")) {
                    text = DataGenerator.getCellPhoneNumber(8);
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
}
