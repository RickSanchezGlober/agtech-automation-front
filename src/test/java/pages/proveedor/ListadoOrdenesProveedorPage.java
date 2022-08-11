package pages.proveedor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.proveedor.ListadoOrdenesProveedorPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ListadoOrdenesProveedorPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;

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
                case "la columna Monto total":
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

    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
            list.stream().forEach(dataEntry -> getObjectAllOrder(dataEntry));

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
        }
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
        FIELD_TEXT_API = data.get("create_date").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(getDateStringFormat(FIELD_TEXT_API)));
        //PRODUCER_CUIT
        JSONObject PRODUCERS = (JSONObject) data.get("producer");
        FIELD_TEXT_API = PRODUCERS.get("cuit").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replaceAll("-", "").contains(FIELD_TEXT_API.replaceAll("-", "")));

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
    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public boolean verifyMaxNumberOrders(String orderQuantity) {
        explicitWait(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(ListadoOrdenesProveedorPageObject.ORDENES_CONTAINER);
        log.info(String.format("Verificando que se muestren máximo '%s' órdenes", orderQuantity));
        return (elementList.size() <= Integer.parseInt(orderQuantity));
    }
}
