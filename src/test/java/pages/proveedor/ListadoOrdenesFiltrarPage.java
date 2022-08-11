package pages.proveedor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.proveedor.ListadoOrdenesFiltrarPageObject;
import pageobjects.proveedor.ListadoOrdenesProveedorPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.util.ArrayList;
import java.util.List;


public class ListadoOrdenesFiltrarPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;
    public static String filterSelected;

    public ListadoOrdenesFiltrarPage() {
        super();
        pos = 0;
    }

    public void clicOnButtonByNameOrders(String buttonName) {
        waitVisibility(ListadoOrdenesFiltrarPageObject.ORDENES_TITTLE, "30");
        List<WebElement> elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.ORDERS_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }

    public void validateDataFromOrderWithFilters(String sourceApi, String path, List<List<String>> t_table) {

        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
            list.stream().forEach(dataEntry -> getObjectOrders(dataEntry));

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
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
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "5");
        List<WebElement> elementList = null;
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
            case "A sola Firma":
            case "Pago con granos":
                elementList = driver.findElements(ListadoOrdenesFiltrarPageObject.FILTRO_MEDIO_PAGO_CONTAINER);
                for (int i = 0; i < elementList.size(); i++) {
                    if (elementList.get(i).getText().equalsIgnoreCase(filterName)) {
                        elementList.get(i).click();
                        break;
                    }
                }
                break;
        }
    }

    public boolean checkElementsFiltersScreen(List<List<String>> t_table) {
        By byElement = null;
        waitVisibility(ListadoOrdenesFiltrarPageObject.FILTRO_DE_ORDENES_TITLE, "30");
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

}
