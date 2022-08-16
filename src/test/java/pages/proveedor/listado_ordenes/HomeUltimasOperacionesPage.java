package pages.proveedor.listado_ordenes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.proveedor.listado_ordenes.HomeUltimasOperacionesPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HomeUltimasOperacionesPage extends BasePage {

    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;


    public HomeUltimasOperacionesPage() {
        super();
        pos = 0;
    }

    public void clicOnButtonByNameHome(String buttonName) {
        waitVisibility(HomeUltimasOperacionesPageObject.ULTIMAS_REALIZADAS_TITTLE, "30");
        if (buttonName.contains("> de Ordenes próximas a vencer")) {
            List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.FLECHA_DERECHA_ICONO_CONTAINER);
            //La 1era > que aparece es la de Ordenes próximas a vencer
            elementList.get(0).click();
        } else {
            List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.HOME_BUTTON_CONTAINER);
            for (int i = 0; i < elementList.size(); i++) {
                if (elementList.get(i).getText().contains(buttonName)) {
                    elementList.get(i).click();
                    break;
                }
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
        return (elementList.size() <= Integer.parseInt(orderQuantity));
    }

    public void getDataFromApiServicesOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        if (!response.getBody().prettyPrint().equals("")) {
            explicitWait(HomeUltimasOperacionesPageObject.ORDENES_CONTAINER);
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
                list.stream().forEach(dataEntry -> getObjectOrder(dataEntry));

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
//        //PRODUCER_NAME ya no viene el nombre en el response
//        FIELD_TEXT_API = PRODUCERS.get("name").toString();
//        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
//        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API));

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
        //FINANCIAL_LINE este campo ya no esta en el response
//        FIELD_TEXT_API = data.get("financial_line").toString();
//        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
//        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

        //Monto de la deuda, conditions viene vacio
//        Long LOAN_AMOUNT = (Long) ((JSONObject) data.get("conditions")).get("loan_amount");
//        FIELD_TEXT_API = parseFromDoubleToString(LOAN_AMOUNT.toString(), 2);
//        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
//        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API));
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
        By element = null;
        switch (tittle) {
            case "Órdenes":
                element = HomeUltimasOperacionesPageObject.ORDENES_TITTLE;
                break;
            case "Órdenes próximas a vencer":
                element = HomeUltimasOperacionesPageObject.OPERACIONES_PROXIMAS_VENCER;
                break;
        }
        waitVisibility(element, "10");
        return verifyVisibleText(element, tittle);
    }

    public boolean verifyVisibleIcon(String iconName) {
        boolean result = false;
        switch (iconName) {
            case "contador de Ordenes próximas a vencer":
                By element = HomeUltimasOperacionesPageObject.CONTADOR_ORDENES_PROXIMAS_VENCER_ICONO;
                waitVisibility(element, "5");
                result = isDisplayed(element);
                break;
            case ">":
                waitVisibility(HomeUltimasOperacionesPageObject.ULTIMAS_REALIZADAS_TITTLE, "5");
                List<WebElement> elementList = driver.findElements(HomeUltimasOperacionesPageObject.FLECHA_DERECHA_ICONO_CONTAINER);
                //Verificamos que hayan 5 >, 4 son de las ultimas operaciones realizadas
                result = elementList.size() == 5;
                break;
        }

        return result;
    }

    public void getDataFromApiServicesOrdersCounter(String sourceApi, String path) {
        //API
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());
        String numberOrdersCloseToExpireAPI = "";
        try {
            numberOrdersCloseToExpireAPI = response.getBody().jsonPath().get("total").toString();
        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
        }
        //WEB-ELEMNTS
        By element = HomeUltimasOperacionesPageObject.CONTADOR_ORDENES_PROXIMAS_VENCER_ICONO;
        explicitWait(element);
        String numberOrdersCloseToExpireUI = driver.findElement(element).getText();

        //VALIDATIONS
        log.info(String.format("Verificando que se muestre '%s' '%s'", numberOrdersCloseToExpireAPI, "Ordenes próximas a vencer"));
        Assert.assertEquals(numberOrdersCloseToExpireUI, numberOrdersCloseToExpireAPI);
    }

    public boolean checkuttonFuction(String buttonName) {
        //No tengo un user con Ordenes proximas a vencer, cuando las operaciones
        // se filtren por user eso cambiara
        waitVisibility(HomeUltimasOperacionesPageObject.ORDENES_TITTLE, "10");
        return isDisplayed(HomeUltimasOperacionesPageObject.ORDENES_TITTLE);
    }
}
