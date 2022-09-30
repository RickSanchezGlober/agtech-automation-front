package pageobjects.proveedor.listado_ordenes;

import org.openqa.selenium.By;

public class DetalleOrdenGeneradaPageObject {
    public static final By.ByClassName CERRAR_MODAL_FILTRAR_BUTTON = new By.ByClassName("rural-agro-icon-close");
    public static final By.ByClassName ORDER_TABLE_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByXPath APLICAR_FILTROS_BUTTON = new By.ByXPath("//button[contains(text(),'Aplicar filtros')]");
    public static final By.ByXPath FILTRAR_BUTTON = new By.ByXPath("//button[contains(text(),'Filtrar')]");
    public static final By.ByXPath VER_TODAS_BUTTON = new By.ByXPath("//div[contains(@class, 'last_orders__button_top')]//descendant::button");
    public static final By.ByClassName ORDER_DETAIL_CONTAINER = new By.ByClassName("order_detail_container");
    public static final By.ByClassName ORDER_PDF_CONTAINER = new By.ByClassName("pdf_container");
    public static final By.ByClassName ORDER_DETAIL_ROW = new By.ByClassName("last_order__arrow");
    public static final By.ByXPath DESCARGAR_DETALLE_BUTTON = new By.ByXPath("//button[contains(text(),'Descargar detalle')]");
}
