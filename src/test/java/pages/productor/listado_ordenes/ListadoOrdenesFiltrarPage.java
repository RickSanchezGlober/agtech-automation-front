package pages.productor.listado_ordenes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesFiltrarPageObject;
import pages.BasePage;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListadoOrdenesFiltrarPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;
    public static String filterSelected;
    public static LocalDate currentDate = LocalDate.now();
    public static String searchCriteria;

    public ListadoOrdenesFiltrarPage() {
        super();
        pos = 0;
    }

    public void clicOnButtonByNameOrders(String buttonName) {
        if (buttonName.equals("Cerrar Filtros")) {
            waitVisibility(ListadoOrdenesFiltrarPageObject.X_FILTROS_BUTTON, "20");
            click(ListadoOrdenesFiltrarPageObject.X_FILTROS_BUTTON);
        } else {
            waitVisibility(ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER, "10");
            List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER);
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().contains(buttonName)) {
                    elementList.get(i).click();
                    //En ocaciones es necesario hacer click más de una vez en Filtrar
                    int count = 0;
                    while (buttonName.equalsIgnoreCase("Filtrar") && !waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "5") && count < 2) {
                        elementList.get(i).click();
                        count++;
                    }
                    //En ocaciones es necesario hacer click mas de una vez en Exportar
                    File file = ListadoOrdenesExportarPage.createFile();
                    int countExportarButton = 0;
                    while (buttonName.contains("Exportar") && !file.exists() && countExportarButton < 2) {
                        elementList.get(i).click();
                        countExportarButton++;
                    }
                    break;
                }
            }
        }

    }

    public void validateDataFromOrderWithFilters(String sourceApi, String path, List<List<String>> t_table) {

        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices(sourceApi, "farmer/auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
        if (!response.getBody().prettyPrint().equals("")) {
            pos = 0;
            explicitWait(ListadoOrdenesFiltrarPageObject.ORDENES_CONTAINER);
            //Lista de ordenes
            elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.ORDENES_CONTAINER);
            //STATUS y financial_line_name
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
                list.stream().forEach(dataEntry -> getObjectOrders(dataEntry));

            } catch (NullPointerException e) {
                e.printStackTrace();
                log.info("Path is invalid");
            }

        } else {
            //Validar empty state
//            verifyElementEmptyStateScreen("icono");
//            verifyElementEmptyStateScreen("Todavía no tenés órdenes de compra");
//            verifyElementEmptyStateScreen("Cuando las tengas vas a ver tus órdenes de compra acá.");
        }
    }

    private void getObjectPaymentMethods(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //Texto de la ultima operacion, UI
        FIELD_TEXT_UI = elementList.get(pos).getText();
        //Payment Methods
        String FIELD_TEXT_API = data.get("financial_line_name").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_API.toLowerCase().contains(filterSelected.toLowerCase()));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

    }

    private void getObjectOrders(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //STATUS
        if (filterSelected.contains("Pendiente") || filterSelected.contains("Pagada")
                || filterSelected.contains("Rechazada") || filterSelected.contains("Vencida")) {
            //Texto de la ultima operacion, UI
            FIELD_TEXT_UI = elementList.get(pos).getText();

            String FIELD_TEXT_API = data.get("status").toString();
            log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
            Assert.assertTrue(FIELD_TEXT_API.contains(filterSelected));
            Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        }
        //Payment Methods
        if (filterSelected.contains("Cesión de forward")
                || filterSelected.contains("A sola firma")) {
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get(String.format("result[%s].payment_methods", pos)));
                list.stream().forEach(dataEntry1 -> getObjectPaymentMethods(dataEntry1));
            } catch (NullPointerException e) {
                e.printStackTrace();
                log.info("Path is invalid");
            }
        }
        pos++;
    }

    public void selecctFilter(String filterName) {
        filterSelected = filterName;
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "10");
        List<WebElement> elementList = null;
        By element = null;
        switch (filterName) {
            case "Pendiente":
            case "Pagada":
            case "Rechazada":
            case "Vencida":
                elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.FILTRO_ESTADO_CONTAINER);
                for (int i = 0; i < elementList.size(); i++) {
                    if (elementList.get(i).getText().equalsIgnoreCase(filterName)) {
                        elementList.get(i).click();
                        break;
                    }
                }
                break;
            case "Cesión de forward":
            case "A sola firma":
            case "Pago con granos":
                elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.FILTRO_MEDIO_PAGO_CONTAINER);
                clickOnButtonListButton(elementList, filterName);
                break;
            case "Desde":
                element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_DESDE;
                LocalDate fromDate = getLocalDateFrom();
                clearField(element);
                write(element, fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
            case "Hasta":
                element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_HASTA;
                clearField(element);
                write(element, currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
            case "Desde Fecha Futura":
                element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_DESDE;
                LocalDate futureDateFrom = getLocalDateFuture(0, 1, 0);
                clearField(element);
                write(element, futureDateFrom.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
            case "Hasta Fecha Futura":
                element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_HASTA;
                LocalDate futureDateUntil = getLocalDateFuture(0, 2, 0);
                clearField(element);
                write(element, futureDateUntil.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
        }
    }

    private LocalDate getLocalDateFrom() {
        int cantMonth = 0;
        if (1 < currentDate.getDayOfMonth() && currentDate.getDayOfMonth() < 8) {
            cantMonth = 1;
        }
        LocalDate fromDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth().getValue() - cantMonth, currentDate.getDayOfMonth() - 1);
        return fromDate;
    }

    private LocalDate getLocalDateFuture(int futureYear, int futureMonth, int futureDayOfMonth) {
        LocalDate futureDate = LocalDate.of(currentDate.getYear() + futureYear, currentDate.getMonth().getValue() + futureMonth, currentDate.getDayOfMonth() + futureDayOfMonth);
        return futureDate;
    }

    public void clearField(By element) {
        int cantChar = getAttribute(element, "value").length();
        for (int i = 0; i < cantChar; i++) {
            sendBackSpace(element);
        }
    }

    public void clickOnButtonListButton(List<WebElement> buttonList, String buttonName) {
        for (int i = 0; i < buttonList.size(); i++) {
            if (buttonList.get(i).getText().equalsIgnoreCase(buttonName)) {
                if (buttonName.contains("A sola firma") || buttonName.contains("Cesión de forward")) {
                    List<WebElement> checkList = driver.findElements(ListadoOrdenesFiltrarPageObject.CHECK_MEDIO_PAGO_CONTAINER);
                    checkList.get(i).click();
                } else {
                    buttonList.get(i).click();
                }
                break;
            }
        }
    }

    public boolean checkElementsFiltersScreen(List<List<String>> t_table) {
        By byElement = null;
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "35");
        List<Boolean> resultList = new ArrayList<>();
        for (int i = 0; i < t_table.size(); i++) {
            String elementName = t_table.get(i).get(0);
            log.info(String.format("Buscando el elemento '%s'", elementName));
            switch (elementName) {
                case "el titulo Filtros de órdenes":
                    byElement = ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE;
                    resultList.add(verifyVisibleText(byElement, elementName.replace("el titulo ", "")));
                    break;
                case "el titulo Estado":
                case "el titulo Medio de pago":
                case "el titulo Fecha de creación de la orden":
                    resultList.add(findElementOrdesScreen(elementName.replace("el titulo ", ""), ListadoOrdenesFiltrarPageObject.TITLE_CONTAINER_FILTER_SCREEN));
                    break;
                case "el check Pendiente":
                case "el check Pagada":
                case "el check Rechazada":
                case "el check Vencida":
                    resultList.add(findElementOrdesScreen(elementName.replace("el check ", ""), ListadoOrdenesFiltrarPageObject.FILTRO_ESTADO_CONTAINER));
                    break;
                case "el check Cesión de forward":
                case "el check A sola firma":
                case "el check Pago con granos":
                    resultList.add(findElementOrdesScreen(elementName.replace("el check ", ""), ListadoOrdenesFiltrarPageObject.FILTRO_MEDIO_PAGO_CONTAINER));
                    break;
                case "el botón Aplicar filtros":
                    resultList.add(findElementOrdesScreen(elementName.replace("el botón ", ""), ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER));
                    break;
                case "los campos Desde, Hasta":
                    List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_CONTAINER);
                    resultList.add(elementList.size() == 2);
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

    public boolean verifyButtonState(String buttonName) {
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "5");
        boolean isEnableButton = false;
        List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER);
        for (int j = 0; j < elementList.size(); j++) {
            if (elementList.get(j).getText().equalsIgnoreCase(buttonName)) {
                isEnableButton = elementList.get(j).isEnabled();
                break;
            }
        }
        return isEnableButton;
    }

    public void clicOnButtonByNameSideMenu(String buttonName) {
//        explicitWait(ListadoOrdenesFiltrarPageObject.ORDENES_TITTLE);
        List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.SIDE_MENU_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }

    public void verifyListOrdersInRange() {
        waitVisibility(ListadoOrdenesFiltrarPageObject.FECHA_ORDENES_CONTAINER, "20");
        List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.FECHA_ORDENES_CONTAINER);
        LocalDate fromDate = getLocalDateFrom();
        LocalDate dateOrders = null;
        for (int i = 0; i < elementList.size(); i++) {
            dateOrders = getLocalDateFromString(elementList.get(i).getText());
            Assert.assertTrue((dateOrders.isBefore(currentDate) || dateOrders.equals(currentDate))
                    && (dateOrders.isAfter(fromDate) || dateOrders.isEqual(fromDate)));
        }
    }

    private LocalDate getLocalDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("es-ES"));
        LocalDate ldt = LocalDate.parse(stringDate, formatter);
        return ldt;
    }

    public boolean verifyDefaultValue(String field, String date) {
        By element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_HASTA;
        waitVisibility(element, "10");
        boolean result = false;
        switch (field) {
            case "Hasta":
                if (date.equals("fecha actual")) {
                    date = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                }
                result = getAttribute(element, "value").equals(date);
                break;
        }
        return result;
    }

    public void clicOnButtonByNameHome(String buttonName) {
        waitVisibility(ListadoOrdenesFiltrarPageObject.ULTIMAS_REALIZADAS_TITTLE, "30");
        if (buttonName.contains("> de Ordenes próximas a vencer")) {
            List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.FLECHA_DERECHA_ICONO_CONTAINER);
            //La 1era > que aparece es la de Ordenes próximas a vencer
            elementList.get(0).click();
        } else {
            List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.HOME_BUTTON_CONTAINER);
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().contains(buttonName)) {
                    elementList.get(i).click();
                    break;
                }
            }
        }
    }

    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices(sourceApi, "farmer/auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
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
        click(ListadoOrdenesFiltrarPageObject.LUPA_BUTTON);
    }

    public void fillField(String text, String field) {
        By element = null;
        switch (field) {
            case "Buscar CUIT o nombre de cliente":
                element = ListadoOrdenesFiltrarPageObject.BUSCAR_CUIT_NOMBRE_INPUT;
                break;
        }
        waitVisibility(element, "40");
        clear(element);
        write(element, text);
    }

    public void checkCorrectResultDisplayed() {
        waitVisibility(ListadoOrdenesFiltrarPageObject.ORDENES_CONTAINER, "30");
        List<WebElement> listWebElement = driver.findElements(ListadoOrdenesFiltrarPageObject.ORDENES_CONTAINER);
        if (listWebElement.size() != 0) {
            for (int i = 0; i < listWebElement.size(); i++) {
                log.info(String.format("Verificando que se muestre en la orden '%s' el criterio '%s'", (i + 1), searchCriteria));
                Assert.assertTrue(listWebElement.get(i).getText().replaceAll("-", "").toLowerCase().contains(searchCriteria.replaceAll("-", "").toLowerCase()));
            }
        } else {
            Assert.fail("La busqueda no arroja resultados");
        }

    }

    public boolean verifyEmptyStateOrdersScrenn() {
        boolean result = verifyElementEmptyStateOrdersScreen("icono")
                && verifyElementEmptyStateOrdersScreen("No encontramos operaciones")
                && verifyElementEmptyStateOrdersScreen("revisá los filtros y volvé a intentarlo.");
        return result;
    }

    public boolean verifyElementEmptyStateOrdersScreen(String elementName) {
        explicitWait(ListadoOrdenesFiltrarPageObject.EMPTY_STATE_ICON);
        By element = null;
        List<WebElement> elementList = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = ListadoOrdenesFiltrarPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "No encontramos operaciones":
                element = ListadoOrdenesFiltrarPageObject.NO_ENCONTRAMOS_OPERACIONES_TITTLE;
                result = verifyVisibleText(element, elementName);
                break;
            case "revisá los filtros y volvé a intentarlo.":
                element = ListadoOrdenesFiltrarPageObject.REVISA_LOS_FILTROS_SUBTITTLE;
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
