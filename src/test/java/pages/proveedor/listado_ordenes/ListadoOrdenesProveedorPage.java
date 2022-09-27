package pages.proveedor.listado_ordenes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.sl.In;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesPageObject;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraCesionForwardPageObject;
import pageobjects.proveedor.generar_orden.GenerarOrdenCompraSolaFirmaPageObject;
import pageobjects.proveedor.listado_ordenes.HomeUltimasOperacionesPageObject;
import pageobjects.proveedor.listado_ordenes.ListadoOrdenesFiltrarPageObject;
import pageobjects.proveedor.listado_ordenes.ListadoOrdenesProveedorPageObject;
import pages.BasePage;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ListadoOrdenesProveedorPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;
    public static String searchCriteria;

    public ListadoOrdenesProveedorPage() {
        super();
        pos = 0;
    }

    public boolean verifyVisibleElementsOrdersScreen(List<List<String>> t_table) {
        By byElement = null;
        explicitWait(ListadoOrdenesProveedorPageObject.BUSCAR_CUIT_NOMBRE_INPUT);
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < t_table.size(); i++) {
            String elementName = t_table.get(i).get(0);
            log.info(String.format("Buscando el elemento '%s'", elementName));
            switch (elementName) {
                case "el buscador Buscar CUIT o nombre de cliente":
                    byElement = ListadoOrdenesProveedorPageObject.BUSCAR_CUIT_NOMBRE_INPUT;
                    resultList.add(getAttribute(byElement, "placeholder").equalsIgnoreCase("Buscar CUIT o nombre de cliente"));
                    break;
                case "el boton Exportar":
                case "el boton Filtrar":
                case "el boton Crear Orden":
                    resultList.add(findElementOrdesScreen(elementName.replace("el boton ", ""), ListadoOrdenesProveedorPageObject.BUTTON_CONTAINER_PANTALLA_ORDENES));
                    break;
                case "la columna Creación":
                case "la columna Cliente":
                case "la columna Entidad":
                case "la columna Medio de Pago":
                case "la columna Monto":
                case "la columna Estado":
                    resultList.add(findElementOrdesScreen(elementName.replace("la columna ", ""), ListadoOrdenesProveedorPageObject.ENCABEZADO_TABLA_CONTAINER_PANTALLA_ORDENES));
                    break;
                case "los botones >":
                    //Cambiar esto cuando en Ver todas se vean mas de 4 ordenes
                    List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.FLECHA_DERECHA_ICONO_CONTAINER);
                    resultList.add(elementList.size() > 0);
                    break;
            }
        }
        Boolean finalResult = true;
        for (int j = 0; j < resultList.size(); j++) {
            if (!resultList.get(j)) {
                finalResult = resultList.get(j);
                log.info(String.format("No se pudo encontrar el elemento '%s' '%s'", (j + 1), "de la tabla"));
                break;
            }
        }
        return finalResult;
    }

    public boolean findElementOrdesScreen(String element, By byContainer) {
        boolean isVisibleButton = false;
        List<WebElement> elementList = driver.findElements(byContainer);
        for (int j = 0; j < elementList.size(); j++) {
            if (elementList.get(j).getText().equalsIgnoreCase(element)) {
                isVisibleButton = elementList.get(j).isDisplayed();
                break;
            }
        }
        return isVisibleButton;
    }

    public void checkDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "provider/auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
        pos = 0;
        if (!response.getBody().prettyPrint().equals("")) {
            explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
                list.stream().forEach(dataEntry -> getObjectAllOrder(dataEntry));

            } catch (NullPointerException e) {
                e.printStackTrace();
                log.info("Path is invalid");
            }
        } else {
            //Validar empty state
            Assert.assertTrue(verifyElementEmptyStateScreen("icono"));
            Assert.assertTrue(verifyElementEmptyStateScreen("Todavía no tenés órdenes de compra"));
            Assert.assertTrue(verifyElementEmptyStateScreen("Cuando las tengas vas a ver tus órdenes de compra acá."));
        }

    }

    public boolean verifyElementEmptyStateScreen(String elementName) {
        explicitWait(ListadoOrdenesProveedorPageObject.EMPTY_STATE_ICON);
        By element = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = ListadoOrdenesProveedorPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "Todavía no tenés órdenes de compra":
                element = ListadoOrdenesProveedorPageObject.NO_ENCONTRAMOS_OPERACIONES_TITTLE;
                result = verifyVisibleText(element, elementName);
                break;
            case "Cuando las tengas vas a ver tus órdenes de compra acá.":
                element = ListadoOrdenesProveedorPageObject.REVISA_LOS_FILTROS_SUBTITTLE;
                result = verifyVisibleText(element, elementName);
                break;
        }
        return result;
    }

    private void getObjectAllOrder(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //Texto de la ultima operacion, UI
        elementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        FIELD_TEXT_UI = elementList.get(pos).getText();


        //STATUS
        String FIELD_TEXT_API = data.get("status").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        //CREATE_DATE
        FIELD_TEXT_API = data.get("order_date").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(getDateStringFormat(FIELD_TEXT_API)));
        //PRODUCER_CUIT
        JSONObject PRODUCERS = (JSONObject) data.get("farmer");
        FIELD_TEXT_API = PRODUCERS.get("cuit").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replaceAll("-", "").contains(FIELD_TEXT_API.replaceAll("-", "")));

        //PRODUCER_NAME
        FIELD_TEXT_API = PRODUCERS.get("name").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

        //Monto de la deuda,
        if (data.get("amount") instanceof Long) {
            Long LONG_AMOUNT = (Long) data.get("amount");
            FIELD_TEXT_API = parseFromDoubleToString(LONG_AMOUNT.toString(), 2);
        } else if (data.get("amount") instanceof Double) {
            Double DOUBLE_AMOUNT = (Double) data.get("amount");
            String STRING_AMOUNT = parseFromDoubleToString(DOUBLE_AMOUNT.toString(), 0);
            FIELD_TEXT_API = StringUtils.chop(STRING_AMOUNT);
        }
//        Double DOUBLE_AMOUNT = (Double) data.get("amount");
//        FIELD_TEXT_API = parseFromDoubleToString(DOUBLE_AMOUNT.toString(), 2);
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API));
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get(String.format("result[%s].payment_methods", pos)));
            list.stream().forEach(dataEntry1 -> getObjectPaymentMethods(dataEntry1));
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
        }
        pos++;
    }

    public void getObjectPaymentMethods(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //FINANCIAL_ENTITY
        String FIELD_TEXT_API = data.get("financial_entity").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API));
        //FINANCIAL_LINE
        if (data.get("financial_line_id").toString().equals("1")) {
            FIELD_TEXT_API = "A sola firma";
        } else if (data.get("financial_line_id").toString().equals("2")) {
            FIELD_TEXT_API = "Cesión de forward";
        }
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public boolean verifyMaxNumberOrders(String orderQuantity) {
        explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        log.info(String.format("Verificando que se muestren máximo '%s' órdenes", orderQuantity));
        return (elementList.size() <= Integer.parseInt(orderQuantity));
    }

    public void doSearchByCuitName(String searchCriteria, String criteriaStatus) {
        if (searchCriteria.equalsIgnoreCase("cuit") && criteriaStatus.equalsIgnoreCase("existente")) {
            searchCriteria = response.getBody().jsonPath().get("result.farmer[0].cuit");
        } else if (searchCriteria.equalsIgnoreCase("nombre del cliente") && criteriaStatus.equalsIgnoreCase("existente")) {
            searchCriteria = response.getBody().jsonPath().get("result.farmer[0].name");
        } else if (searchCriteria.equalsIgnoreCase("cuit parcial") && criteriaStatus.equalsIgnoreCase("existente")) {
            searchCriteria = response.getBody().jsonPath().get("result.farmer[0].cuit").toString().substring(0, 5);
        } else if (searchCriteria.equalsIgnoreCase("cuit") && criteriaStatus.equalsIgnoreCase("inexistente")) {
            searchCriteria = DataGenerator.getNumber(11);
        }
        this.searchCriteria = searchCriteria;
        fillField(searchCriteria, "Buscar CUIT o nombre de cliente");
        click(ListadoOrdenesProveedorPageObject.LUPA_BUTTON);
    }

    public void fillField(String text, String field) {
        By element = null;
        switch (field) {
            case "Buscar CUIT o nombre de cliente":
                element = ListadoOrdenesProveedorPageObject.BUSCAR_CUIT_NOMBRE_INPUT;
                break;
        }
        waitVisibility(element, "40");
        clear(element);
        write(element, text);
    }

    public void checkCorrectResultDisplayed() {
        waitVisibility(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER, "30");
        List<WebElement> listWebElement = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        if (listWebElement.size() != 0) {
            for (int i = 0; i < listWebElement.size(); i++) {
                log.info(String.format("Verificando que se muestre en la orden '%s' el criterio '%s'", (i + 1), searchCriteria));
                Assert.assertTrue(listWebElement.get(i).getText().replaceAll("-", "").toLowerCase().contains(searchCriteria.replaceAll("-", "").toLowerCase()));
            }
        } else {
            Assert.fail("La busqueda no arroja resultados");
        }

    }

    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "provider/auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
    }

    public boolean verifyButtonState(String buttonName, String buttonStatus) {
        waitVisibility(ListadoOrdenesProveedorPageObject.RESULTADOS_SPAN, "10");
        boolean isEnableButton = false;
        switch (buttonStatus) {
            case "Deshabilitado":
                buttonStatus = "disabled";
                break;
        }
        //Si esta deshabiltado trae en class la palabra disabled
        //Si esta habiltado no trae nada
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.COUNTER_BUTTON_CONTAINER);
        switch (buttonName) {
            case "<":
                isEnableButton = elementList.get(0).getAttribute("class").contains(buttonStatus);
                break;
            case ">":
                isEnableButton = !elementList.get(1).getAttribute("class").contains(buttonStatus);
                break;
        }
        return isEnableButton;
    }

    public boolean moreThanXOrders(String countOrders) {
        return (Integer) response.getBody().jsonPath().get("_pagination.total_count") > Integer.parseInt(countOrders);
    }

    public boolean checkPagingOptions() {
        waitVisibility(ListadoOrdenesProveedorPageObject.RESULTADOS_SPAN, "10");
        boolean resultOpt1 = false;
        boolean resultOpt2 = false;
        boolean resultOpt3 = false;
        Select dropDownList = new Select(driver.findElement(ListadoOrdenesProveedorPageObject.PAGINADO_SELECT));
        List<WebElement> lisOptions = dropDownList.getOptions();
        for (int i = 0; i < lisOptions.size(); i++) {
            if (lisOptions.get(i).getText().equals("1 - 10")) {
                resultOpt1 = true;
            } else if (lisOptions.get(i).getText().equals("50")) {
                resultOpt2 = true;
            } else if (lisOptions.get(i).getText().equals("100")) {
                resultOpt3 = true;
            }
        }
        return resultOpt1 && resultOpt2 && resultOpt3;
    }

    public void selectPaging(String option) {
        waitVisibility(ListadoOrdenesProveedorPageObject.RESULTADOS_SPAN, "10");
        Select dropDownList = new Select(driver.findElement(ListadoOrdenesProveedorPageObject.PAGINADO_SELECT));
        dropDownList.selectByVisibleText(option);
    }

    public void clickOnButtonPaging(String buttonName) {
        waitVisibility(ListadoOrdenesProveedorPageObject.RESULTADOS_SPAN, "10");
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.COUNTER_BUTTON_CONTAINER);
        switch (buttonName) {
            case "<":
                elementList.get(0).click();
                break;
            case ">":
                elementList.get(1).click();
                break;
        }
    }

    public boolean checkRestOrders() {
        Integer countOrders = response.getBody().jsonPath().get("_pagination.total_count");
        boolean result = false;
        if (countOrders > 10) {
            waitVisibility(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER, "10");
            List<WebElement> webElementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
            result = webElementList.size() <= 10;
        }
        return result;
    }

    public boolean verifyFirstOrders(String orderQuantity) {
        explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        return (elementList.size() == Integer.parseInt(orderQuantity));
    }

    public boolean verifyEmptyStateOrdersScrenn() {
        boolean result = verifyElementEmptyStateOrdersScreen("icono")
                && verifyElementEmptyStateOrdersScreen("No encontramos operaciones")
                && verifyElementEmptyStateOrdersScreen("revisá los filtros y volvé a intentarlo.");
        return result;
    }

    public boolean verifyElementEmptyStateOrdersScreen(String elementName) {
        explicitWait(ListadoOrdenesProveedorPageObject.EMPTY_STATE_ICON);
        By element = null;
        List<WebElement> elementList = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = ListadoOrdenesProveedorPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "No encontramos operaciones":
                element = ListadoOrdenesProveedorPageObject.NO_ENCONTRAMOS_OPERACIONES_TITTLE;
                result = verifyVisibleText(element, elementName);
                break;
            case "revisá los filtros y volvé a intentarlo.":
                element = ListadoOrdenesProveedorPageObject.REVISA_LOS_FILTROS_SUBTITTLE;
                result = verifyVisibleText(element, elementName);
                break;
        }
        if (result) {
            log.info(String.format("Se visualiza el elemento %s", elementName));
        } else {
            log.error(String.format("No se visualiza el elemento %s", elementName));
        }
        return result;
    }
}
