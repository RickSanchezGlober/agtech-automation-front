package pageobjects;

import org.openqa.selenium.By;

public class ProductorDetallesOrdenesPageObject {
    public static final By.ByLinkText VER_ORDENES_BUTTON = new By.ByLinkText("Ver órdenes");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("card__content");
    public static final By.ByXPath DETALLE_ORDENES_SIDESHEET = new By.ByXPath("//body/div[@id='root-portal']/div[1]/div[1]/div[1]");
    public static final By.ByClassName FECHA_HORA_TEXT = new By.ByClassName("order__properties_dates");
    public static final By.ByClassName NUMERO_ORDEN_TEXT = new By.ByClassName("order__properties_ordernumber");
    public static final By.ByClassName NOMBRE_PROVEEDOR_TEXT = new By.ByClassName("order__title_name");
    public static final By.ByClassName DESCRIPCION_ORDEN_TEXT = new By.ByClassName("productlist__description");
    public static final By.ByClassName PRECIO_TEXT = new By.ByClassName("order__descriptioncard_price");
    public static final By.ByClassName TNA_TEXT = new By.ByClassName("order__descriptioncard_tna");
    public static final By.ByClassName FECHA_VENCIMIENTO_TEXT = new By.ByClassName("order__descriptioncard_dates");
    public static final By.ByClassName MEDIO_PAGO_TEXT = new By.ByClassName("order__payment_title");
    public static final By.ByClassName MEDIO_PAGO_DISPONIBLE_CARD = new By.ByClassName("order__paymentcard");
    public static final By.ByClassName CONFIRMAR_BUTTON = new By.ByClassName("button__container_primary");
}
