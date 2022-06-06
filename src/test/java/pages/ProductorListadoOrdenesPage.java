package pages;

import org.openqa.selenium.By;
import pageobjects.ProductorListadoOrdenesPageObject;

public class ProductorListadoOrdenesPage extends BasePage {
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
                result = verifyVisibleText(element, elementName.replaceFirst("Botón ",""));
                break;
        }
        return result;
    }

}
