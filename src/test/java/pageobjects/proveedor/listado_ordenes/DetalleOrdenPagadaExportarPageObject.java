package pageobjects.proveedor.listado_ordenes;

import org.openqa.selenium.By;

public class DetalleOrdenPagadaExportarPageObject {
    public static final By.ByXPath DESCARGAR_DETALLE_BUTTON = new By.ByXPath("//button[contains(text(),'Descargar detalle')]");
    public static final By.ByXPath DESCARGAR_PDF_BUTTON = new By.ByXPath("//button[contains(text(),'Descargar PDF')]");
}
