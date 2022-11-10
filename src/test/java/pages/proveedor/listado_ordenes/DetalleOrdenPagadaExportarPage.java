package pages.proveedor.listado_ordenes;

import org.openqa.selenium.By;
import pageobjects.proveedor.listado_ordenes.DetalleOrdenPagadaExportarPageObject;
import pages.BasePage;

public class DetalleOrdenPagadaExportarPage extends BasePage {
    public DetalleOrdenPagadaExportarPage() {
        super();
    }

    public boolean verifyButtonIsEnabled(String sButton) {
        By element = null;
                switch (sButton){
            case "Descargar detalle":
                element = DetalleOrdenPagadaExportarPageObject.DESCARGAR_DETALLE_BUTTON;
            break;
            case "Descargar PDF":
                element = DetalleOrdenPagadaExportarPageObject.DESCARGAR_PDF_BUTTON;
                //Se pasa el foco a la pestaña que visualiza todos los datos de la orden que pueden ser descargados en PDF
                driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
            break;
        }
        waitVisibility(element, "2");
        log.info("==========>>>>>>>>>>>>>>>>>>>>>> URL::::"+driver.getCurrentUrl());
        return isEnabled(element);
    }

}
