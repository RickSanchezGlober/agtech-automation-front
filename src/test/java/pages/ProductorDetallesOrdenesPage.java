package pages;

import org.openqa.selenium.By;
import pageobjects.ProductorDetallesOrdenesPageObject;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductorDetallesOrdenesPage extends BasePage {

    public ProductorDetallesOrdenesPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        explicitWait(ProductorDetallesOrdenesPageObject.VER_ORDENES_BUTTON);
        switch (buttonName) {
            case "Ver órdenes":
                element = ProductorDetallesOrdenesPageObject.VER_ORDENES_BUTTON;
                break;
        }
        click(element);
    }

    public void clickOnDetailOrder() {
        waitVisibility(ProductorDetallesOrdenesPageObject.DETALLE_ORDENES_SIDESHEET, "2");
        List<WebElement> elementList = driver.findElements(ProductorDetallesOrdenesPageObject.ORDENES_CONTAINER);
        elementList.get(0).click();
    }

    public boolean verifyDetailsOrder(String detail) {
        waitVisibility(ProductorDetallesOrdenesPageObject.DETALLE_ORDENES_SIDESHEET, "2");
        By element = null;
        switch (detail) {
            case "Fecha y Hora":
                element = ProductorDetallesOrdenesPageObject.FECHA_HORA_TEXT;
                break;
            case "Número de orden":
                element = ProductorDetallesOrdenesPageObject.NUMERO_ORDEN_TEXT;
                break;
            case "Nombre del Proveedor":
                element = ProductorDetallesOrdenesPageObject.NOMBRE_PROVEEDOR_TEXT;
                break;
            case "Descripción de la Orden":
                element = ProductorDetallesOrdenesPageObject.DESCRIPCION_ORDEN_TEXT;
                break;
            case "Monto":
                element = ProductorDetallesOrdenesPageObject.MONTO_TEXT;
                break;
            case "TNA":
                element = ProductorDetallesOrdenesPageObject.TNA_TEXT;
                break;
            case "Fecha Vencimiento":
                element = ProductorDetallesOrdenesPageObject.FECHA_VENCIMIENTO_TEXT;
                break;
            case "Medio de pago":
                element = ProductorDetallesOrdenesPageObject.MEDIO_PAGO_TEXT;
                break;
            case "Medio de pago disponible":
                element = ProductorDetallesOrdenesPageObject.MEDIO_PAGO_DISPONIBLE_CARD;
                break;
            case "Botón Confirmar":
                element = ProductorDetallesOrdenesPageObject.CONFIRMAR_BUTTON;
                break;
            case "Descripción de medio de pago disponible":
                element = ProductorDetallesOrdenesPageObject.PAGA_CON_TEXT;
                break;
        }
        return isDisplayed(element);
    }
}
