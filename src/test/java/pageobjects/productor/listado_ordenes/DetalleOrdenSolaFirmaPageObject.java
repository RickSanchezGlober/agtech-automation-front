package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class DetalleOrdenSolaFirmaPageObject {
    public static final By.ByClassName PRODUCTOR_ORDER_DETAIL_CONTAINER = new By.ByClassName("order_detail__container");
    public static final By.ByClassName COSTO_CAPITAL_TOOLTIP = new By.ByClassName("main-tooltip");
    public static final By.ByClassName PRODUCTOR_ORDER_TABLE_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName PRODUCTOR_ORDER_DETAIL_ROW = new By.ByClassName("last_order__arrow");
    public static final By.ByClassName PRODUCTOR_ORDER_COLLAPSIBLE_ARROW = new By.ByClassName("collapsible_box__arrow");
    public static final By.ByClassName CONTINUAR_BUTTON = new By.ByClassName("button__container_primary");
    public static final By.ByClassName VOLVER_BUTTON = new By.ByClassName("goback_button__text");
    public static final By.ByXPath FILTRAR_BUTTON = new By.ByXPath("//button[contains(text(),'Filtrar')]");
    public static final By.ByClassName ORDEN_PAGADA_DETALLE_CONTAINER = new By.ByClassName("paid_order_detail__info_container");

}
