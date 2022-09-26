package pages.proveedor.listado_ordenes;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.proveedor.listado_ordenes.ListadoOrdenesFiltrarPageObject;
import pageobjects.proveedor.listado_ordenes.ListadoOrdenesProveedorPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListadoOrdenesFiltrarPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;
    public static String filterSelected;
    public static LocalDateTime currentDate = LocalDateTime.now();

    public ListadoOrdenesFiltrarPage() {
        super();
        pos = 0;
    }

    public void clicOnButtonByNameOrders(String buttonName) {
        if (buttonName.equals("Cerrar Filtros")) {
            waitVisibility(ListadoOrdenesFiltrarPageObject.X_FILTROS_BUTTON, "20");
            click(ListadoOrdenesFiltrarPageObject.X_FILTROS_BUTTON);
        } else {
            waitVisibility(ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER, "30");
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
                    break;
                }
            }
        }

    }

    public void validateDataFromOrderWithFilters(String sourceApi, String path, List<List<String>> t_table) {

        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParamsHeader(sourceApi, path, t_table, getAccess_token());
        if (!response.getBody().prettyPrint().equals("")) {
            explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
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

    private void getObjectOrders(Object dataEntry) {
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
        Assert.assertTrue(FIELD_TEXT_API.contains(filterSelected));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        pos++;
    }

    public void selecctFilter(String filterName) {
        filterSelected = filterName;
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "30");
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
                LocalDateTime fromDate = getLocalDateFrom();
                clearField(element);
                write(element, fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
            case "Hasta":
                element = ListadoOrdenesFiltrarPageObject.DATE_PICKER_INPUT_HASTA;
                clearField(element);
                write(element, currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                break;
        }
    }

    private LocalDateTime getLocalDateFrom() {
        int cantMonth = 0;
        if (1 < currentDate.getDayOfMonth() && currentDate.getDayOfMonth() < 8) {
            cantMonth = 1;
        }
        LocalDateTime fromDate = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth().getValue() - cantMonth, currentDate.getDayOfMonth() - 1, currentDate.getHour(), currentDate.getMinute());
        return fromDate;
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
                buttonList.get(i).click();
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
        LocalDateTime fromDate = getLocalDateFrom();
        LocalDateTime dateOrders = null;
        for (int i = 0; i < elementList.size(); i++) {
            dateOrders = getLocalDateFromString(elementList.get(i).getText());
            Assert.assertTrue((dateOrders.isBefore(currentDate) || dateOrders.equals(currentDate))
                    && (dateOrders.isAfter(fromDate) || dateOrders.isEqual(fromDate)));
        }
    }

    private LocalDateTime getLocalDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("es-ES"));
        LocalDateTime ldt = LocalDate.parse(stringDate, formatter).atStartOfDay();
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
}
