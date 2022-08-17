package pages.productor.listado_ordenes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageobjects.productor.listado_ordenes.ListadoOrdenesPageObject;
import pages.BasePage;
import utils.RestAssuredExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        LocalDateTime ldFirst = getLocalDateFromString(dateListFirstUI);
        LocalDateTime ldLast = getLocalDateFromString(dateListLastUI);
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

    private LocalDateTime getLocalDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm", Locale.forLanguageTag("es-ES"));
        LocalDateTime ldt = LocalDateTime.parse(stringDate, formatter);
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

    public void getDataFromApiServicesAllOrders(String sourceApi, String path) {
        log.info(String.format("Consumiendo API: '%s' '%s'", sourceApi, path));
        getAcessTokenFromApiServices("bff", "auth/login");
        response = RestAssuredExtension.getMethod(sourceApi, path, getAccess_token());
        if (!response.getBody().prettyPrint().equals("")) {
            explicitWait(ListadoOrdenesPageObject.ORDENES_CONTAINER);
            try {
                ArrayList list = new ArrayList<>(response.getBody().jsonPath().get("result"));
                list.stream().forEach(dataEntry -> getObjectOrder(dataEntry));

            } catch (NullPointerException e) {
                e.printStackTrace();
                log.info("Path is invalid");
            }
        }else {
            verifyElementEmptyStateScreen("icono");
            verifyElementEmptyStateScreen("Todavía no tenés órdenes de compra");
            verifyElementEmptyStateScreen("Cuando las tengas vas a ver tus órdenes de compra acá.");
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

        //CREATE_DATE
        String FIELD_TEXT_API = data.get("create_date").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.toLowerCase().contains(getDateStringFormat(FIELD_TEXT_API)));
        //id_order
        FIELD_TEXT_API = data.get("id_order").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        //title
        FIELD_TEXT_API = data.get("title").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));
        //producer
        JSONObject PRODUCERS = (JSONObject) data.get("producer");
        FIELD_TEXT_API = PRODUCERS.get("name").toString();
        log.info(String.format("Verificando que se muestre '%s''%s'", "en la operacion " + (pos + 1), FIELD_TEXT_API));
        Assert.assertTrue(FIELD_TEXT_UI.contains(FIELD_TEXT_API));

        pos++;
    }

    private String getDateStringFormat(String stringDate) {
        LocalDateTime ldt = LocalDateTime.parse(stringDate);
        return ldt.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.forLanguageTag("es-ES")));
    }
}
