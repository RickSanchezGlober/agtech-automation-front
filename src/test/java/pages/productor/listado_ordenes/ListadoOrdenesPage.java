package pages.productor.listado_ordenes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;


public class ListadoOrdenesPage extends BasePage {
    public static int pos;
    public static List<WebElement> elementList;
    public static String FIELD_TEXT_UI;

    public ListadoOrdenesPage() {
        super();
        pos = 0;
    }

    public boolean verifyElementEmptyStateScreen(String elementName) {
        explicitWait(ListadoOrdenesPageObject.EMPTY_STATE_ICON);
        By element = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = ListadoOrdenesPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "Todavía no tenés órdenes de compra":
                element = ListadoOrdenesPageObject.SIN_ORDENES_COMPRA_TEXT;
                result = verifyVisibleText(element, elementName);
                break;
            case "Cuando las tengas vas a ver tus órdenes de compra acá.":
                element = ListadoOrdenesPageObject.CUANDO_TENGAS_TEXT;
                result = verifyVisibleText(element, elementName);
                break;
            case "Botón Volver":
                element = ListadoOrdenesPageObject.VOLVER_BUTTON;
                result = verifyVisibleText(element, elementName.replaceFirst("Botón ", ""));
                break;
        }
        return result;
    }


    public boolean verifySortOrders(String sort) {
        explicitWait(ListadoOrdenesPageObject.ORDENES_CONTAINER);
        boolean result = false;
        List<WebElement> dateList = driver.findElements(ListadoOrdenesPageObject.FECHA_HORA_GENERACION_CONTAINER);
        //Obtengo la 1era y ultima fecha
        String dateListFirstUI = getDateFromStringUI(dateList.get(0).getText());
        String dateListLastUI = getDateFromStringUI(dateList.get(dateList.size() - 1).getText());
        //Convierto las fechas a LocalDateTime
        LocalDate ldFirst = getLocalDateFromString(dateListFirstUI);
        LocalDate ldLast = getLocalDateFromString(dateListLastUI);
        //Verifico cual es el orden
        switch (sort) {
            case "ascendente":
                //ascendente: Desde las mas antigua a la mas reciente
                result = ldFirst.isBefore(ldLast);
                break;
            case "descendente":
                //descendente: Desde las mas reciente a la mas antigua
                result = ldLast.isBefore(ldFirst);
                break;
        }
        return result;
    }

    private String getDateFromStringUI(String stringDate) {
        stringDate = stringDate.replaceAll("hrs.", "");
        stringDate = stringDate.replace(" | ", "");
        stringDate = stringDate.replaceFirst(" ", "/");
        stringDate = stringDate.replaceFirst(" ", "/");
        stringDate = stringDate.replaceFirst(" ", "");
        return stringDate.toLowerCase();
    }

    private LocalDate getLocalDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("es-ES"));
        LocalDate ldt = LocalDate.parse(stringDate, formatter);
        return ldt;
    }

    public void msOrdersProducerVolver() {
        int i = 0;
        while (i < 3) {
            if (waitVisibility(ListadoOrdenesPageObject.TENEMOS_PROBLEMA_TEXT, "1")
                    && waitVisibility(ListadoOrdenesPageObject.VOLVER_BUTTON, "1")) {
                click(ListadoOrdenesPageObject.VOLVER_BUTTON);
                //cuando se hace click en volver te lleva a la pagina del logueo,
                // deberia regresar a ver ordenes
//                explicitWait(ProductorDetallesOrdenesPageObject.VER_ORDENES_BUTTON);
//                click(ProductorDetallesOrdenesPageObject.VER_ORDENES_BUTTON);
                i++;
            } else {
                break;
            }
            if (i > 2) {
                log.info("Problema con la comunicacion al MS orders/producer");
            }
        }
    }

    public void getDataFromApiServicesAllOrders(String sourceApi, String path, List<List<String>> t_table) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices(sourceApi, "farmer/auth/login");
        response = RestAssuredExtension.getMethodWithParams(sourceApi, path, t_table, getAccess_token());
        if (!response.getBody().prettyPrint().equals("")) {
            explicitWait(ListadoOrdenesPageObject.ORDENES_CONTAINER);
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
                list.stream().forEach(dataEntry -> getObjectOrder(dataEntry));

            } catch (NullPointerException e) {
                e.printStackTrace();
                log.info("Path is invalid");
            }
        } else {
            Assert.assertTrue(verifyElementEmptyStateScreen("icono"));
            Assert.assertTrue(verifyElementEmptyStateScreen("Todavía no tenés órdenes de compra"));
            Assert.assertTrue(verifyElementEmptyStateScreen("Cuando las tengas vas a ver tus órdenes de compra acá."));
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
        elementList = driver.findElements(ListadoOrdenesPageObject.ORDENES_CONTAINER);
        FIELD_TEXT_UI = elementList.get(pos).getText();

        //order_date
        String FIELD_TEXT_API = data.get("order_date").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(getDateStringFormat(FIELD_TEXT_API)));
        //provider
        JSONObject PRODUCERS = (JSONObject) data.get("provider");
        FIELD_TEXT_API = PRODUCERS.get("name").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

        FIELD_TEXT_API = PRODUCERS.get("cuit").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(getCuitWithFormat(FIELD_TEXT_API)));
        //Estado de la orden
        FIELD_TEXT_API = data.get("status").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(FIELD_TEXT_API.toLowerCase()));

        try {
            ArrayList list = new ArrayList<>(response.getBody().jsonPath().get(String.format("result[%s].payment_methods", pos)));
            list.stream().forEach(dataEntry1 -> getObjectPaymentMethods(dataEntry1));

        } catch (NullPointerException e) {
            e.printStackTrace();
            log.info("Path is invalid");
        }
        pos++;
    }

    private void getObjectPaymentMethods(Object dataEntry) {
        JSONObject data = null;
        try {
            data = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(dataEntry));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //FINANCIAL_ENTITY
        String FIELD_TEXT_API = (data.get("financial_entity").toString());
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API));
        //FINANCIAL_LINE
        if (data.get("financial_line_id").toString().equals("1")) {
            FIELD_TEXT_API = "A sola firma";
        } else if (data.get("financial_line_id").toString().equals("2")) {
            FIELD_TEXT_API = "Cesión de forward";
        }
        //Monto de la deuda,
        if (data.get("amount") instanceof Long) {
            Long LONG_AMOUNT = (Long) data.get("amount");
            FIELD_TEXT_API = parseFromDoubleToString(LONG_AMOUNT.toString(), 2);
        } else if (data.get("amount") instanceof Double) {
            Double DOUBLE_AMOUNT = (Double) data.get("amount");
            String STRING_AMOUNT = parseFromDoubleToString(DOUBLE_AMOUNT.toString(), 0);
            FIELD_TEXT_API = StringUtils.chop(STRING_AMOUNT);
        }

        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.replace(".", "").contains(FIELD_TEXT_API.replace(".", "")));

    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.forLanguageTag("es-ES")));
    }

    public boolean checkHomePageElements() {
        waitVisibility(ListadoOrdenesPageObject.TUS_ORDENES_TITTLE, "10");
        return verifyVisibleText(ListadoOrdenesPageObject.TUS_ORDENES_TITTLE, "Tus órdenes")
                && driver.findElement(ListadoOrdenesPageObject.ORDER_PAGE_HEADER).getText().contains("Filtrar")
                && driver.findElement(ListadoOrdenesPageObject.ORDER_PAGE_HEADER).getText().contains("Exportar XLS")
                && getAttribute(ListadoOrdenesPageObject.BUSCAR_CUIT_NOMBRE_INPUT, "placeholder").equalsIgnoreCase("Buscar CUIT o nombre de cliente")
                && findElementOnList("Creación", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER)
                && findElementOnList("Proveedor", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER)
                && findElementOnList("Entidad", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER)
                && findElementOnList("Medio de pago", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER)
                && findElementOnList("Monto", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER)
                && findElementOnList("Estado de la orden", ListadoOrdenesPageObject.ENCABEZADO_TABLA_CONTAINER);
    }

    public boolean findElementOnList(String element, By byContainer) {
        boolean isVisibleButton = false;
        log.info(String.format("Verificando que el elemento '%s' esté en la lista", element));
        List<WebElement> elementList = driver.findElements(byContainer);
        for (int j = 0; j < elementList.size(); j++) {
            if (elementList.get(j).getText().equalsIgnoreCase(element)) {
                isVisibleButton = elementList.get(j).isDisplayed();
                break;
            }
        }
        return isVisibleButton;

    }
}
