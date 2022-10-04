package pages.productor.listado_ordenes;

import org.openqa.selenium.By;
import pageobjects.productor.listado_ordenes.DetallesOrdenesPageObject;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class DetallesOrdenesPage extends BasePage {

    public DetallesOrdenesPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        waitVisibility(DetallesOrdenesPageObject.ORDENES_BUTTON, "6");
        switch (buttonName) {
            case "Órdenes":
                element = DetallesOrdenesPageObject.ORDENES_BUTTON;
                break;
        }
        click(element);
    }

    public void clickOnDetailOrder() {
        waitVisibility(DetallesOrdenesPageObject.DETALLE_ORDENES_SIDESHEET, "2");
        List<WebElement> elementList = driver.findElements(DetallesOrdenesPageObject.ORDENES_CONTAINER);
        elementList.get(0).click();
    }

    public boolean verifyDetailsOrder(String detail) {
        waitVisibility(DetallesOrdenesPageObject.DETALLE_ORDENES_SIDESHEET, "2");
        By element = null;
        switch (detail) {
            case "Fecha y Hora":
                element = DetallesOrdenesPageObject.FECHA_HORA_TEXT;
                break;
            case "Número de orden":
                element = DetallesOrdenesPageObject.NUMERO_ORDEN_TEXT;
                break;
            case "Nombre del Proveedor":
                element = DetallesOrdenesPageObject.NOMBRE_PROVEEDOR_TEXT;
                break;
            case "Descripción de la Orden":
                element = DetallesOrdenesPageObject.DESCRIPCION_ORDEN_TEXT;
                break;
            case "Monto":
                element = DetallesOrdenesPageObject.MONTO_TEXT;
                break;
            case "TNA":
                element = DetallesOrdenesPageObject.TNA_TEXT;
                break;
            case "Fecha Vencimiento":
                element = DetallesOrdenesPageObject.FECHA_VENCIMIENTO_TEXT;
                break;
            case "Medio de pago":
                element = DetallesOrdenesPageObject.MEDIO_PAGO_TEXT;
                break;
            case "Medio de pago disponible":
                element = DetallesOrdenesPageObject.MEDIO_PAGO_DISPONIBLE_CARD;
                break;
            case "Botón Confirmar":
                element = DetallesOrdenesPageObject.CONFIRMAR_BUTTON;
                break;
            case "Descripción de medio de pago disponible":
                element = DetallesOrdenesPageObject.PAGA_CON_TEXT;
                break;
        }
        return isDisplayed(element);
    }
}
