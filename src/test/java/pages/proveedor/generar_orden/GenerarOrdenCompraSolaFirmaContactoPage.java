package pages.proveedor.generar_orden;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesPageObject;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pages.BasePage;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


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

    public void checkConfirmationScreenContactData(List<List<String>> t_table) {
        DataTable data = createDataTable(t_table);
        By containerElement = GenerarOrdenCompraSolaFirmaContactoPageObject.SOLICITUD_COMPLETA_CONTAINER;
        explicitWait(containerElement);
        String FIELDS_TEXT = driver.findElement(containerElement).getText().replaceAll("\n", " ");
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {
                                // TABLE
                                List<String> rField = Collections.singletonList(value.get(0));
                                String FIELDS = rField.get(0);
                                String VALUES = getScenarioContextVariables(rField.get(0));

                                // WEB ELEMENTS
                                switch (FIELDS) {
                                    case "producer_cuit":
                                        VALUES = "CUIT " + getCuitWithFormat(VALUES);
                                        break;
                                    case "loan_amount":
                                        String numberS = parseFromDoubleToString(VALUES, 2);
                                        VALUES = "Monto a acreditar " + "$" + numberS.substring(0, 1) + "." + numberS.substring(1, 7);
                                        break;
                                    case "financing_type":
                                        VALUES = "Tipo de convenio " + VALUES;
                                        break;
                                    case "fees":
                                        String cuotaFormat = String.format("%02d", Integer.parseInt(VALUES));
                                        VALUES = "Cantidad de cuotas " + cuotaFormat;
                                        break;
                                    case "tna":
                                    case "cft":
                                        //no verifico este texto "CFT" y "TNA del crédito" pq para mapear no esta por filas sino por columnas
                                        VALUES = parseFromDoubleToString(VALUES, 2) + "%";
                                        break;
                                    case "interest":
                                    case "interest_iva":
                                    case "sealed":
                                        //no verifico este texto "Sellado"
                                        // "IVA s/ interés" "Sellado" pq para mapear
                                        // no esta por filas sino por columnas
                                        VALUES = "$ " + VALUES;
                                        break;
                                    case "end_to_pay":
                                        String amount = parseFromDoubleToString(VALUES, 2);
                                        VALUES = "$ " + amount.substring(0, 1) + "." + amount.substring(1, 7);
                                        break;
                                }
                                log.info(FIELDS + " " + FIELDS_TEXT);

                                // VALIDATIONS
                                Assert.assertTrue(FIELDS_TEXT.contains(VALUES));
                                i.getAndIncrement();
                            }
                    );
        }
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

    public void getDataFromApiServicesSimulation(String sourceApi, String path, String body, List<List<String>> t_table) {
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.postMethod(sourceApi, path, body, getAccess_token());
        DataTable data = createDataTable(t_table);
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {
                                List<String> rField = Collections.singletonList(value.get(0));
                                String KEY = rField.get(0);
                                // SAVE
                                try {
                                    saveInScenarioContext(KEY, response.getBody().jsonPath().get(KEY).toString());
                                } catch (NullPointerException e) {
                                }
                            });
        }
    }

    public boolean verifyTitleIsDisplayed(String title) {
        By titleElement = GenerarOrdenCompraSolaFirmaContactoPageObject.REVISA_SOLICITUD_COMPLETA_TITLE;
        By containerElement = GenerarOrdenCompraSolaFirmaContactoPageObject.SOLICITUD_COMPLETA_CONTAINER;
        waitVisibility(titleElement, "30");
        String textFromUI = driver.findElement(containerElement).getText().replaceAll("\n", " ");
        String fieldText = "";
        boolean result = false;
        switch (title) {
            case "Revisá que la solicitud esté completa":
                textFromUI = driver.findElement(titleElement).getText();
                result = textFromUI.contains(title);
                break;
            case "Detalles de la orden":
                fieldText = " Descripción " + GenerarOrdenCompraSolaFirmaPage.orderDescription;
                result = textFromUI.contains(title) && textFromUI.contains(fieldText);
                break;
            case "Información de contacto":
                fieldText = fieldText + " Nombre y Apellido " + fullNameContactGenereted
                        + " Correo electrónico " + emailContactGenereted
                        + " Número de celular " + areaCodeGenereted + " " + cellNumberGenereted;
                result = textFromUI.contains(title) && textFromUI.contains(fieldText);
                break;
            case "Medio de pago":
                fieldText = "Banco Banco Galicia " + title + " "
                        + GenerarOrdenCompraSolaFirmaPage.paymentMethod;
                result = textFromUI.contains(title) && textFromUI.contains(fieldText);
                break;
        }
        return result;
    }

    public boolean verifyOrderGeneratedScreen() {
        boolean result = false;
        if (response.statusCode() == 200) {
            waitVisibility(GenerarOrdenCompraSolaFirmaContactoPageObject.ORDEN_GENERADA_ENVIADA_TITLE, "10");
            result = verifyVisibleText(GenerarOrdenCompraSolaFirmaContactoPageObject.ORDEN_GENERADA_ENVIADA_TITLE, "Orden generada y enviada exitosamente")
                    && verifyVisibleText(GenerarOrdenCompraSolaFirmaContactoPageObject.RECIBIRAS_NOTIFICACION_SUBTITLE, "Recibirás una notificación tan pronto Productor S.A acepte la orden.")
                    && isDisplayed(GenerarOrdenCompraSolaFirmaContactoPageObject.CONFIRMATION_ICON)
                    && isEnabled(GenerarOrdenCompraSolaFirmaContactoPageObject.IR_A_ORDENES_BUTTON);
        } else {
            //Valido el Empty State
            result = verifyElementEmptyStateScreen("icono")
                    && verifyElementEmptyStateScreen("No es posible continuar en este momento")
                    && verifyElementEmptyStateScreen("Te pedimos disculpas. Hay un problema técnico. Por favor volvé a intentar en unos minutos.")
                    && verifyElementEmptyStateScreen("Ir a órdenes");
        }
        return result;
    }

    public boolean verifyElementEmptyStateScreen(String elementName) {
        explicitWait(GenerarOrdenCompraSolaFirmaContactoPageObject.EMPTY_STATE_ICON);
        By element = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "No es posible continuar en este momento":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.NO_ES_POSIBLE_CONTINUAR_TITLE;
                result = verifyVisibleText(element, elementName);
                break;
            case "Te pedimos disculpas. Hay un problema técnico. Por favor volvé a intentar en unos minutos.":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.TE_PEDIMOS_DISCULPAS_SUBTITLE;
                result = verifyVisibleText(element, elementName);
                break;
            case "Ir a órdenes":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.IR_A_ORDENES_BUTTON;
                result = verifyVisibleText(element, elementName);
                break;
        }
        return result;
    }

    public boolean verifyScreenErrorMail() {
        waitVisibility(GenerarOrdenCompraSolaFirmaContactoPageObject.CORREO_NO_VALIDO_TEXT, "10");
        return verifyVisibleText(GenerarOrdenCompraSolaFirmaContactoPageObject.CORREO_NO_VALIDO_TEXT, "Correo electrónico no válido")
                && !isEnabled(GenerarOrdenCompraSolaFirmaContactoPageObject.CONTINUAR_BUTTON);
    }

    public void getDataFromApiServicesConfirm(String sourceApi, String path, String body) {
        log.info(String.format("Consumiendo API: '%s' '%s' '%s'", sourceApi, path, body));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.postMethod(sourceApi, path, body, getAccess_token());
    }
}
