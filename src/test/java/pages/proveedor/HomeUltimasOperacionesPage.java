package pages.proveedor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import org.codehaus.groovy.util.StringUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaContactoPageObject;
import pageobjects.proveedor.GenerarOrdenCompraSolaFirmaPageObject;
import pageobjects.proveedor.HomeUltimasOperacionesPageObject;
import pages.BasePage;
import utils.DataGenerator;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class HomeUltimasOperacionesPage extends BasePage {

    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;


    public HomeUltimasOperacionesPage() {
        super();
        pos = 0;
    }

    public void clicOnButtonByNameHome(String buttonName) {
        explicitWait(HomeUltimasOperacionesPageObject.ULTIMAS_REALIZADAS_TITTLE);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.HOME_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }


    public void clicOnButtonByNameOrders(String buttonName) {
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_TITTLE);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.ORDERS_BUTTON_CONTAINER);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText().contains(buttonName)) {
                elementList.get(i).click();
                break;
            }
        }
    }

    public boolean verifyScreenNewOrder() {
        By byElement = HomeUltimasOperacionesPageObject.INGRESA_CUIT_LABEL;
        waitVisibility(byElement, "10");
        log.info("Verificando que se visualise la pantalla de nueva orden");
        return verifyVisibleText(byElement, "Ingresá el CUIT") &&
                verifyVisibleText(HomeUltimasOperacionesPageObject.ESCRIBI_11_NUMEROS_TEXT, "Escribí 11 números") &&
                !isEnabled(HomeUltimasOperacionesPageObject.BUSCAR_BUTTON) &&
                isDisplayed(HomeUltimasOperacionesPageObject.LUPA_ICONO);
    }

    public boolean verifyMaxNumberOrders(String orderQuantity) {
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
        return (elementList.size() == Integer.parseInt(orderQuantity));
    }

    public void getDataFromApiServicesOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        explicitWait(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
            list.stream().forEach(dataEntry -> getObjectOrder(dataEntry));

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
        }
    }

    public void getObjectOrder(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //Texto de la ultima operacion, UI
        elementList = driver.findElements(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
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
        //PRODUCER_NAME
        FIELD_TEXT_API = PRODUCERS.get("name").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API));

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
        //FINANCIAL_LINE
        FIELD_TEXT_API = data.get("financial_line").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

        //Monto de la deuda
        Long LOAN_AMOUNT = (Long) ((JSONObject) data.get("conditions")).get("loan_amount");
        FIELD_TEXT_API = parseFromDoubleToString(LOAN_AMOUNT.toString(), 2);
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API));
    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
    }

    public boolean verifyVisibleElements(List<List<String>> t_table) {
        By byElement = null;
        boolean result1 = false;
        boolean result2 = false;
        for (int i = 0; i < t_table.size(); i++) {
            String elementName = t_table.get(i).get(0);
            if (elementName.contains("Últimas realizadas")) {
                byElement = HomeUltimasOperacionesPageObject.ULTIMAS_REALIZADAS_TITTLE;
                result1 = isDisplayed(byElement);
            } else if (elementName.contains("Ver todas")) {
                List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.HOME_BUTTON_CONTAINER);

                for (int j = 0; j < elementList.size(); j++) {
                    if (elementList.get(j).getText().equals("Ver todas")) {
                        result2 = elementList.get(j).isDisplayed();
                        break;
                    }
                }
            }
        }
        explicitWait(byElement);
        return result1 && result2;
    }

    public boolean verifyVisibleTittle(String tittle) {
        return verifyVisibleText(HomeUltimasOperacionesPageObject.ORDENES_TITTLE, tittle);
    }
}
