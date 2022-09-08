package pages.proveedor.listado_ordenes;

import org.openqa.selenium.By;
import pageobjects.proveedor.listado_ordenes.DetalleOrdenGeneradaPageObject;
import pages.BasePage;

public class DetalleOrdenGeneradaPage extends BasePage {
    public DetalleOrdenGeneradaPage() {
        super();
    }

    public void clickOnCloseButton() {
        By element = DetalleOrdenGeneradaPageObject.CERRAR_MODAL_FILTRAR_BUTTON;
        waitVisibility(element, "10");
        click(element);
    }
}
