package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class DetalleOrdenSolaFirmaPageObject {
    public static final By.ByClassName PRODUCTOR_ORDER_DETAIL_CONTAINER = new By.ByClassName("order_detail__container");
    public static final By.ByClassName PRODUCTOR_ORDER_TABLE_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName PRODUCTOR_ORDER_DETAIL_ROW = new By.ByClassName("last_order__arrow");
    public static final By.ByClassName PRODUCTOR_ORDER_COLLAPSIBLE_ARROW = new By.ByClassName("collapsible_box__arrow");
}
