package pages.proveedor;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageobjects.proveedor.GenerarOrdenCompraPageObject;
import pages.BasePage;
import steps.proveedor.GenerarOrdenCompraSteps;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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
                waitVisibility(GenerarOrdenCompraPageObject.PRODUCTOR_ARROW, "15");
                element = GenerarOrdenCompraPageObject.PRODUCTOR_ARROW;
                break;
            case "Continuar":
                explicitWait(GenerarOrdenCompraPageObject.CONTINUAR_BUTTON);
                element = GenerarOrdenCompraPageObject.CONTINUAR_BUTTON;
                break;
            case "Simular Crédito":
                explicitWait(GenerarOrdenCompraPageObject.SIMULAR_CREDITO_BUTTON);
                element = GenerarOrdenCompraPageObject.SIMULAR_CREDITO_BUTTON;
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
            case "Ingresá el monto del crédito":
                element = GenerarOrdenCompraPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
                if (text.contains("mayor a $1.000")) {
                    text = "1001";
//                    do {
//                        text = DataGenerator.getNumber(4);
//                    } while (Integer.parseInt(text) < 1001);
                    log.info("Ingresando monto de crédito: " + text);
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
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Simular Crédito":
                element = GenerarOrdenCompraPageObject.SIMULAR_CREDITO_BUTTON;
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
            case "CUIT del Productor Asociado":
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

    public void validateProductorName(List<List<String>> table) {
        DataTable data = createDataTable(table);
        explicitWait(GenerarOrdenCompraPageObject.DATOS_PRODUCTOR_ASOCIADO_TEXT);
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
                                try {
                                    Thread.sleep(1000);
                                    By element = null;

                                    switch (FIELDS) {
                                        case "name":
                                            element = GenerarOrdenCompraPageObject.NOMBRE_PRODUCTOR_ASOCIADO_TEXT;
                                            break;
                                    }

                                    String FIELDS_TEXT = driver.findElement(element).getText();
                                    log.info(FIELDS + " " + FIELDS_TEXT);

                                    // VALIDATIONS
                                    Assert.assertTrue(FIELDS_TEXT.contains(VALUES));

                                } catch (InterruptedException | NullPointerException e) {
                                    e.printStackTrace();
                                }
                                i.getAndIncrement();
                            }
                    );
        }
    }

    public boolean verifyCUITFormat(String cuit) {
        String cuitWithFormat = getAttribute(GenerarOrdenCompraPageObject.INGRESA_EL_CUIT_INPUT, "value");
        cuit = cuit.substring(0, 2) + "-" + cuit.substring(2, 10) + "-" + cuit.substring(10, 11);
        return cuit.equals(cuitWithFormat);
    }

    public void getDataFromApiServicesValidation(String sourceApi, String path, String cuit, List<List<String>> table) {
        log.info(path + cuit);
        getDataFromApiServices(path + cuit, sourceApi, table);
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

    public void selectPaymentMethod(String paymentMethod) {
        explicitWait(GenerarOrdenCompraPageObject.ELEGI_MEDIO_PAGO_TITLE);
        List<WebElement> elementList = driver.findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_CONTAINER);
        boolean result = false;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(paymentMethod)) {
                result = true;
                log.info("Haciendo click en el medio de pago :" + paymentMethod);
                sleep(1);
                elementList.get(i).findElements(GenerarOrdenCompraPageObject.PAYMENT_CARD_BANK_NAME_CONTAINER).get(0).click();
                break;
            }
        }
        if (!result) {
            log.error("No se pudo hacer click en el medio de pago: " + paymentMethod);
        }
    }

    public void selectOptionFromDropDownList(String dropDownName, String option) {
        Select dropDownList = null;
        switch (dropDownName) {
            case "subsidio de tasa":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraPageObject.SUBSIDIO_TASA_DROP_DOWN_LIST));
                break;
        }
        dropDownList.selectByVisibleText(option);
    }

    public void getDataFromApiServicesSimulation(String sourceApi, String path, String body, List<List<String>> t_table) {
        log.info(path);
        getDataFromApiServices(path, body, sourceApi, t_table);
        validateSimulationData(t_table);
    }

    private void validateSimulationData(List<List<String>> t_table) {
        DataTable data = createDataTable(t_table);
        waitVisibility(GenerarOrdenCompraPageObject.SIMULATION_CARD_CONTAINER, "30");
        // WEB ELEMENTS
        String FIELDS_TEXT = driver.findElement(GenerarOrdenCompraPageObject.SIMULATION_CARD_CONTAINER).getText().replaceAll("\n", " ");
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {
                                // TABLE
                                List<String> rField = Collections.singletonList(value.get(0));
                                String FIELDS = rField.get(0);
                                String VALUES = getScenarioContextVariables(rField.get(0));

                                log.info(FIELDS + " " + FIELDS_TEXT);
                                // VALIDATIONS
                                if (FIELDS.contains("TNA") || FIELDS.contains("CFT") || FIELDS.contains("Interés")) {
                                    VALUES = FIELDS + " " + parseFromDoubleToString(VALUES, 2) + " %";
                                } else if (FIELDS.contains("Total Crédito a sola firma")) {
                                    String numberS = parseFromDoubleToString(VALUES, 2);
                                    VALUES = FIELDS + " $ " + numberS.substring(0, 1) + "." + numberS.substring(1, 7);
                                } else if (FIELDS.contains("Cuota única, vencimiento:")) {
                                    VALUES = FIELDS + " " + getDateStringFormat(VALUES);
                                }
                                Assert.assertTrue(FIELDS_TEXT.contains(VALUES));
                                i.getAndIncrement();
                            });
        }
    }

    private String parseFromDoubleToString(String doubleNumber, int numbersAfterDot) {
        BigDecimal bd = new BigDecimal(doubleNumber).setScale(numbersAfterDot, RoundingMode.HALF_UP);
        return String.valueOf(bd).replace(".", ",");
    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        //revisar q pasa si la fecha trae 2 cifras
        return ldt.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    public void validateProductor(List<List<String>> t_table) {
        DataTable data = createDataTable(t_table);
        explicitWait(GenerarOrdenCompraPageObject.RESULTADO_SIMULACION_TEXT);
        // WEB ELEMENTS
        String FIELDS_TEXT = driver.findElement(GenerarOrdenCompraPageObject.SIMULATION_CARD_CONTAINER).getText().replaceAll("\n", " ");
        if (data != null) {
            AtomicInteger i = new AtomicInteger(1);
            data.cells()
                    .forEach(
                            value -> {
                                // TABLE
                                List<String> rField = Collections.singletonList(value.get(0));
                                String FIELDS = rField.get(0);
                                String VALUES = getScenarioContextVariables(rField.get(0));

                                log.info(FIELDS + " " + FIELDS_TEXT);
                                // VALIDATIONS
                                Assert.assertTrue(FIELDS_TEXT.contains("Productor " + VALUES));
                                i.getAndIncrement();
                            }
                    );
        }
    }

    public void deleteField(String field) {
        By element = null;
        switch (field) {
            case ("Ingresá el monto del crédito"):
                element = GenerarOrdenCompraPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
                break;
        }
        int cantChar = getAttribute(element, "value").length() - 1;
        for (int i = 0; i < cantChar; i++) {
            sendBackSpace(element);
        }
    }

    public boolean buttonIsNotDisplayed(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;

        }
        return waitVisibility(element, "1");
    }

    public void changeOptionRateSubsidy(String dropDownName, String option) {
        Select dropDownList = null;
        waitVisibility(GenerarOrdenCompraPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON, "20");
        switch (dropDownName) {
            case "subsidio de tasa":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraPageObject.SUBSIDIO_TASA_DROP_DOWN_LIST));
                break;
        }
        dropDownList.selectByVisibleText(option);
    }
}
