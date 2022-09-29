package pages.proveedor.listado_ordenes;

import org.openqa.selenium.By;
import pageobjects.proveedor.listado_ordenes.DetalleOrdenPagadaExportarPageObject;
import pages.BasePage;

public class DetalleOrdenPagadaExportarPage extends BasePage {
    public DetalleOrdenPagadaExportarPage() {
        super();
    }

    public boolean verifyButtonIsEnabled() {
        By element = DetalleOrdenPagadaExportarPageObject.DESCARGAR_DETALLE_BUTTON;
        waitVisibility(element, "2");
        return isEnabled(element);
    }

}
