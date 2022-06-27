package pages.productor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.productor.ListadoOrdenesPageObject;
import pages.BasePage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class ListadoOrdenesPage extends BasePage {

    public ListadoOrdenesPage() {
        super();
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

    public boolean verifyOrderQuantity(String orderQuantity) {
        explicitWait(ListadoOrdenesPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(ListadoOrdenesPageObject.ORDENES_CONTAINER);
        return (elementList.size() == Integer.parseInt(orderQuantity));
    }

    public boolean verifyOrderDetails(String details, String orderQuantity) {
        explicitWait(ListadoOrdenesPageObject.ORDENES_CONTAINER);
        List<WebElement> detailsList = null;
        switch (details) {
            case "Nombre del productor":
                detailsList = driver.findElements(ListadoOrdenesPageObject.NOMBRE_PRODUCTOR_CONTAINER);
                break;
            case "Número de orden":
                detailsList = driver.findElements(ListadoOrdenesPageObject.NUMERO_ORDEN_CONTAINER);
                break;
            case "Fecha y hora de generación":
                detailsList = driver.findElements(ListadoOrdenesPageObject.FECHA_HORA_GENERACION_CONTAINER);
                break;
            case "Descripción":
                detailsList = driver.findElements(ListadoOrdenesPageObject.DESCRIPCION_CONTAINER);
                break;
        }
        return detailsList.size() == Integer.parseInt(orderQuantity);
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
}
