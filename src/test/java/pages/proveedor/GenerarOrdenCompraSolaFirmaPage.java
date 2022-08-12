package pages.proveedor;

import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageobjects.productor.ListadoOrdenesPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaPageObject;
import pages.BasePage;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class GenerarOrdenCompraSolaFirmaPage extends BasePage {
    public static String orderDescription = "";
    public static String paymentMethod = "";

    public GenerarOrdenCompraSolaFirmaPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        //En ocaciones es necesario hacer click mas de una vez en Simular Credito
        By element1 = null;
        switch (buttonName) {
            case "Crear Orden":
                element = GenerarOrdenCompraSolaFirmaPageObject.CREAR_ORDEN_BUTTON;
                break;
            case "Buscar":
                element = GenerarOrdenCompraSolaFirmaPageObject.BUSCAR_BUTTON;
                break;
            case "del Productor encontrado":
                element = GenerarOrdenCompraSolaFirmaPageObject.PRODUCTOR_ARROW;
                break;
            case "Continuar":
                element = GenerarOrdenCompraSolaFirmaPageObject.CONTINUAR_BUTTON;
                break;
            case "Simular Crédito":
                element = GenerarOrdenCompraSolaFirmaPageObject.SIMULAR_CREDITO_BUTTON;
                element1 = GenerarOrdenCompraSolaFirmaPageObject.RESULTADO_SIMULACION_TEXT;
                break;
            case "X":
                element = GenerarOrdenCompraSolaFirmaPageObject.X_BUTTON;
                break;
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraSolaFirmaPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Enviar orden de compra":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.ENVIAR_ORDEN_COMPRA_BUTTON;
                break;
            case "Ir a órdenes":
                element = GenerarOrdenCompraSolaFirmaContactoPageObject.IR_A_ORDENES_BUTTON;
                break;
        }
        waitVisibility(element, "30");
        click(element);

        //En ocaciones es necesario hacer click más de una vez en Simular Credito
        int count = 0;
        while (buttonName.equalsIgnoreCase("Simular Crédito") && !waitVisibility(element1, "5") && count < 2) {
            click(element);
            count++;
        }
    }

    public void fillField(String text, String field) {
        By element = null;
        switch (field) {
            case "Ingresá el CUIT":
                element = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_CUIT_INPUT;
                if (text.contains("Inválido")) {
                    text = DataGenerator.getNumber(11);
                }
                break;
            case "Descripción":
                element = GenerarOrdenCompraSolaFirmaPageObject.DESCRIPCION_INPUT;
                if (text.contains("Válida")) {
                    text = DataGenerator.getText(1, 40, true, true);
                    this.orderDescription = text;
                } else {
                    //Ver cual seria un descripcion no valida
                }
                break;
            case "Ingresá el monto del crédito":
                element = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
                if (text.contains("mayor a $1.000")) {
                    text = "100100";
//                    do {
//                        text = DataGenerator.getNumber(4);
//                    } while (Integer.parseInt(text) < 1001);
                    log.info("Ingresando monto de crédito: " + text);
                }
                break;
        }
        waitVisibility(element, "40");
        clear(element);
        write(element, text);
    }

    public boolean verifyOrderDetails(String text) {
        waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.DESCRIPCION_LABEL, "2");
        By element = null;
        switch (text) {
            case "Descripción":
                element = GenerarOrdenCompraSolaFirmaPageObject.DESCRIPCION_LABEL;
                break;
            case "Describe el detalle de la orden de pago para el productor":
                element = GenerarOrdenCompraSolaFirmaPageObject.DESCRIBE_DETALLE_ORDEN_TITLE;
                break;
            case "Hasta 40 caracteres":
                element = GenerarOrdenCompraSolaFirmaPageObject.HASTA_40_CARACTERES_TEXT;
                break;
            case "Elegí el medio de pago con el cual el productor va a pagar la orden.":
                element = GenerarOrdenCompraSolaFirmaPageObject.ELEGI_MEDIO_PAGO_TITLE;
                break;
        }
        return verifyVisibleText(element, text);
    }

    public boolean verifyButtonState(String buttonName) {
        By element = null;
        switch (buttonName) {
            case "Continuar":
                element = GenerarOrdenCompraSolaFirmaPageObject.CONTINUAR_BUTTON;
                break;
            case "Buscar":
                element = GenerarOrdenCompraSolaFirmaPageObject.BUSCAR_BUTTON;
                break;
            case "Confirmar medio de pago":
                element = GenerarOrdenCompraSolaFirmaPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Simular Crédito":
                element = GenerarOrdenCompraSolaFirmaPageObject.SIMULAR_CREDITO_BUTTON;
                break;
        }
        waitVisibility(element, "5");
        return isEnabled(element);
    }

    public boolean checkNumberCharacters(String quantity) {
        int intQuantity = Integer.parseInt(quantity);
        String randomText = DataGenerator.getText(intQuantity + 1, intQuantity + 2, true, true);
        By webElement = GenerarOrdenCompraSolaFirmaPageObject.DESCRIPCION_INPUT;
        write(webElement, randomText);
        return getAttribute(webElement, "value").length() == intQuantity;
    }

    public boolean checkFieldOnCustomerIDScreen(String field) {
        explicitWait(GenerarOrdenCompraSolaFirmaPageObject.BUSCAR_BUTTON);
        By element = null;
        switch (field) {
            case "Ingresá el CUIT":
                element = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_CUIT_LABEL;
                break;
            case "Escribí 11 números":
                element = GenerarOrdenCompraSolaFirmaPageObject.ESCRIBI_11_NUMEROS_TEXT;
                break;
        }
        return verifyVisibleText(element, field);
    }

    public void validateProductorName(List<List<String>> table) {
        DataTable data = createDataTable(table);
        explicitWait(GenerarOrdenCompraSolaFirmaPageObject.DATOS_PRODUCTOR_ASOCIADO_TEXT);
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
                                            element = GenerarOrdenCompraSolaFirmaPageObject.NOMBRE_PRODUCTOR_ASOCIADO_TEXT;
                                            break;
                                        case "cuit_teradata":
                                            element = GenerarOrdenCompraSolaFirmaPageObject.DATOS_PRODUCTOR_ASOCIADO_TEXT;
                                            VALUES = "CUIT " + getCuitWithFormat(VALUES);
                                            break;
                                    }

                                    String FIELDS_TEXT = driver.findElement(element).getText();
                                    log.info(FIELDS + " " + FIELDS_TEXT);

                                    // VALIDATIONS
                                    Assert.assertTrue(FIELDS_TEXT.toLowerCase().contains(VALUES.toLowerCase()));

                                } catch (InterruptedException | NullPointerException e) {
                                    e.printStackTrace();
                                }
                                i.getAndIncrement();
                            }
                    );
        }
    }

    public boolean verifyCUITFormat(String cuit) {
        String cuitWithFormat = getAttribute(GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_CUIT_INPUT, "value");
        cuit = getCuitWithFormat(cuit);
        return cuit.equals(cuitWithFormat);
    }

    public void getDataFromApiServicesValidation(String sourceApi, String path, String cuit, List<List<String>> table) {
        log.info("Consumiendo API " + sourceApi + path);
        getDataFromApiServices(path + cuit, sourceApi, table);
    }

    public boolean checkPaymentMethods(String paymentMethods) {
        explicitWait(GenerarOrdenCompraSolaFirmaPageObject.ELEGI_MEDIO_PAGO_TITLE);
        List<WebElement> elementList = null;
        boolean result = false;
        switch (paymentMethods) {
            case "Medios de Pagos Disponibles":
                elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de Medio de pago":
                elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Crédito a sola firma")
                        || elementList.get(1).getText().equals("Crédito a sola firma"));
                break;
            case "Descripción del Medio de pago":
                elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_DESCRIPTION_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Logo de la entidad bancaria":
                elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_BANK_LOGO_CONTAINER);
                result = (elementList.size() > 0);
                break;
            case "Nombre de la entidad bancaria":
                elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_BANK_NAME_CONTAINER);
                result = (elementList.size() > 0)
                        && (elementList.get(0).getText().equals("Banco Galicia")
                        || elementList.get(1).getText().equals("Banco Galicia"));
                break;
        }
        return result;
    }

    public void selectPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        explicitWait(GenerarOrdenCompraSolaFirmaPageObject.ELEGI_MEDIO_PAGO_TITLE);
        List<WebElement> elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_CONTAINER);
        boolean result = false;
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(paymentMethod)) {
                result = true;
                log.info("Haciendo click en el medio de pago :" + paymentMethod);
                sleep(1);
                elementList.get(i).findElements(GenerarOrdenCompraSolaFirmaPageObject.PAYMENT_CARD_BANK_NAME_CONTAINER).get(0).click();
                break;
            }
        }
        if (!result) {
            log.error("No se pudo hacer click en el medio de pago: " + paymentMethod);
        }
    }

    public void selectOptionFromDropDownList(String dropDownName, String option) {
        waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.SIMULAR_CREDITO_BUTTON, "30");
        Select dropDownList = null;
        switch (dropDownName) {
            case "subsidio de tasa":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraSolaFirmaPageObject.SUBSIDIO_TASA_DROP_DOWN_LIST));
                break;
        }
        dropDownList.selectByVisibleText(option);
    }

    public void getDataFromApiServicesSimulation(String sourceApi, String path, String body, List<List<String>> t_table) {
        log.info("Consumiendo API " + sourceApi + path + " con body " + body);
        getDataFromApiServices(path, body, sourceApi, t_table);
        validateSimulationData(t_table);
    }

    private void validateSimulationData(List<List<String>> t_table) {
        DataTable data = createDataTable(t_table);
        waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.SIMULATION_CARD_CONTAINER, "30");
        // WEB ELEMENTS
        String FIELDS_TEXT = driver.findElement(GenerarOrdenCompraSolaFirmaPageObject.SIMULATION_CARD_CONTAINER).getText().replaceAll("\n", " ");
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
                                    VALUES = FIELDS + " " + parseFromDoubleToString(VALUES, 2) + "%";
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

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        //revisar q pasa si la fecha trae 2 cifras
        return ldt.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    public void validateProductor(List<List<String>> t_table) {
        DataTable data = createDataTable(t_table);
        explicitWait(GenerarOrdenCompraSolaFirmaPageObject.RESULTADO_SIMULACION_TEXT);
        // WEB ELEMENTS
        String FIELDS_TEXT = driver.findElement(GenerarOrdenCompraSolaFirmaPageObject.SIMULATION_CARD_CONTAINER).getText().replaceAll("\n", " ");
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
                element = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
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
                element = GenerarOrdenCompraSolaFirmaPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON;
                break;
            case "Simular Crédito":
                element = GenerarOrdenCompraSolaFirmaPageObject.SIMULAR_CREDITO_BUTTON;
                break;

        }
        return waitVisibility(element, "1");
    }

    public void changeOptionRateSubsidy(String dropDownName, String option) {
        Select dropDownList = null;
        waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.CONFIRMAR_MEDIO_PAGO_BUTTON, "20");
        switch (dropDownName) {
            case "subsidio de tasa":
                dropDownList = new Select(driver.findElement(GenerarOrdenCompraSolaFirmaPageObject.SUBSIDIO_TASA_DROP_DOWN_LIST));
                break;
        }
        dropDownList.selectByVisibleText(option);
    }

    public boolean checkNumberQuantity(String quantity, String field) {
        int intQuantity = Integer.parseInt(quantity);
        String randomNumbers = DataGenerator.getNumber(intQuantity + 1);
        By webElement = null;
        switch (field) {
            case "Ingresá el monto del crédito":
                webElement = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
                break;
        }
        write(webElement, randomNumbers);
        //restamos 6 pq se autocompleta con 3 puntos + 1 coma + $ y espacio en blanco
        return getAttribute(webElement, "value").length() - 6 == intQuantity;
    }

    public boolean checkWritingSpecialCharacters(String field) {
        By webElement = null;
        switch (field) {
            case "Ingresá el monto del crédito":
                webElement = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
                break;
        }
        clear(webElement);
        String special = DataGenerator.getPassword(8, 12, false, true, false);
        write(webElement, special);
        return getAttribute(webElement, "value").length() == 0;
    }

    public boolean checkEnterDecimalPLaces(String quantity) {
        By webElement = GenerarOrdenCompraSolaFirmaPageObject.INGRESA_EL_MONTO_CREDITO_INPUT;
        String[] splitChainArray = getAttribute(webElement, "value").split("\\,");
        int cantDecimalPlaces = 0;
        for (int i = 1; i <= splitChainArray[1].length(); i++) {
            cantDecimalPlaces = i;
        }
        return cantDecimalPlaces == Integer.parseInt(quantity);
    }

    public boolean checkErrorMessage(String messageError) {
        By webElement = GenerarOrdenCompraSolaFirmaPageObject.CUIT_INCORRECTO_TEXT;
        return verifyVisibleText(webElement, messageError);
    }

    public void chekErrorScreen(String cuit) {
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethod("bff", "customer-validation/" + cuit, getAccess_token());
        if (response.getStatusCode() != 200) {
            waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.AHORA_NO_ES_POSIBLE_TITTLE, "5");
            String ahoraTitle = "Ahora no es posible mostrar información";
            log.info(String.format("Verificando que se muestre '%s'", ahoraTitle));
            Assert.assertTrue(verifyVisibleText(GenerarOrdenCompraSolaFirmaPageObject.AHORA_NO_ES_POSIBLE_TITTLE, ahoraTitle));

            String revisaTitle = "Revisá tu conexión a internet o intenta nuevamente más tarde.";
            log.info(String.format("Verificando que se muestre '%s'", revisaTitle));
            Assert.assertTrue(verifyVisibleText(GenerarOrdenCompraSolaFirmaPageObject.REVISA_TU_CONEXION_SUBTITTLE, revisaTitle));

            log.info(String.format("Verificando que se muestre '%s'", "El logo de error"));
            Assert.assertTrue(isDisplayed(GenerarOrdenCompraSolaFirmaPageObject.LOGO_ERROR_STATE));

            List<WebElement> elementList = driver.findElements(GenerarOrdenCompraSolaFirmaPageObject.BUTTON_CONTAINER_ERROR_SCREEN);

            boolean isVisibleTextButtonIntentar = false;
            boolean isVisibleTextButtonVolver = false;
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().contains("Intentar nuevamente")) {
                    isVisibleTextButtonIntentar = true;
                } else if (elementList.get(i).getText().contains("Volver a órdenes")) {
                    isVisibleTextButtonVolver = true;
                }
            }
            log.info(String.format("Verificando que se muestre '%s'", "El boton Intentar nuevamente"));
            Assert.assertTrue(isVisibleTextButtonIntentar);
            log.info(String.format("Verificando que se muestre '%s'", "El boton Volver a órdenes"));
            Assert.assertTrue(isVisibleTextButtonVolver);

            int j = 0;
            while (j < 3 && isDisplayed(GenerarOrdenCompraSolaFirmaPageObject.AHORA_NO_ES_POSIBLE_TITTLE)) {
                elementList.get(3).click();
                click(GenerarOrdenCompraSolaFirmaPageObject.BUSCAR_BUTTON);
                sleep(1);
                j++;
            }
        }
    }

    public boolean chekErrorScreenUnauthorisedCuit() {
        waitVisibility(GenerarOrdenCompraSolaFirmaPageObject.CUIT_NO_AUTORIZADO_TITTLE, "30");
        return verifyVisibleText(GenerarOrdenCompraSolaFirmaPageObject.CUIT_NO_AUTORIZADO_TITTLE, "CUIT No Autorizado")
                && verifyVisibleText(GenerarOrdenCompraSolaFirmaPageObject.ESTE_CUIT_NO_CUMPLE_TITTLE, "Este cuit no cumple con alguno de los requisitos de los medios de pago disponible");
    }
}
