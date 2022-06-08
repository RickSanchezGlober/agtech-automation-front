package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.ProductorListadoOrdenesPageObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class ProductorListadoOrdenesPage extends BasePage {

    public ProductorListadoOrdenesPage() {
        super();
    }

    public boolean verifyElementEmptyStateScreen(String elementName) {
        explicitWait(ProductorListadoOrdenesPageObject.EMPTY_STATE_ICON);
        By element = null;
        boolean result = false;
        switch (elementName) {
            case "icono":
                element = ProductorListadoOrdenesPageObject.EMPTY_STATE_ICON;
                result = isDisplayed(element);
                break;
            case "Todavía no tenés órdenes de compra":
                element = ProductorListadoOrdenesPageObject.SIN_ORDENES_COMPRA_TEXT;
                result = verifyVisibleText(element, elementName);
                break;
            case "Cuando las tengas vas a ver tus órdenes de compra acá.":
                element = ProductorListadoOrdenesPageObject.CUANDO_TENGAS_TEXT;
                result = verifyVisibleText(element, elementName);
                break;
            case "Botón Volver":
                element = ProductorListadoOrdenesPageObject.VOLVER_BUTTON;
                result = verifyVisibleText(element, elementName.replaceFirst("Botón ", ""));
                break;
        }
        return result;
    }
    public boolean verifyOrderQuantity(String orderQuantity) {
        explicitWait(ProductorListadoOrdenesPageObject.ORDENES_CONTAINER);
        List<WebElement> elementList = driver.findElements(ProductorListadoOrdenesPageObject.ORDENES_CONTAINER);
        return (elementList.size() == Integer.parseInt(orderQuantity));
    }

    public boolean verifyOrderDetails(String details, String orderQuantity) {
        explicitWait(ProductorListadoOrdenesPageObject.ORDENES_CONTAINER);
        List<WebElement> detailsList = null;
        switch (details) {
            case "Nombre del productor":
                detailsList = driver.findElements(ProductorListadoOrdenesPageObject.NOMBRE_PRODUCTOR_CONTAINER);
                break;
            case "Número de orden":
                detailsList = driver.findElements(ProductorListadoOrdenesPageObject.NUMERO_ORDEN_CONTAINER);
                break;
            case "Fecha y hora de generación":
                detailsList = driver.findElements(ProductorListadoOrdenesPageObject.FECHA_HORA_GENERACION_CONTAINER);
                break;
            case "Descripción":
                detailsList = driver.findElements(ProductorListadoOrdenesPageObject.DESCRIPCION_CONTAINER);
                break;
        }
        return detailsList.size() == Integer.parseInt(orderQuantity);
    }

    public boolean verifySortOrders(String sort) {
        explicitWait(ProductorListadoOrdenesPageObject.ORDENES_CONTAINER);
        boolean result = false;
        List<WebElement> dateList = driver.findElements(ProductorListadoOrdenesPageObject.FECHA_HORA_GENERACION_CONTAINER);
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
        stringDate = stringDate.replace(" |", "");
        stringDate = stringDate.replaceFirst(" ", "/");
        stringDate = stringDate.replaceFirst(" ", "/");
        return stringDate;
    }

    private LocalDateTime getLocalDateFromString(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm", Locale.forLanguageTag("es-ES"));
        LocalDateTime ldt = LocalDateTime.parse(stringDate, formatter);
        return ldt;
    }
}
