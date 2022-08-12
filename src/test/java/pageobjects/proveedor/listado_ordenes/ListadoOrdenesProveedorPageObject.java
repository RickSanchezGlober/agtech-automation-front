package pageobjects.proveedor.listado_ordenes;

import org.openqa.selenium.By;

public class ListadoOrdenesProveedorPageObject {
    public static final By.ByClassName BUSCAR_CUIT_NOMBRE_INPUT = new By.ByClassName("search_bar__input");
    public static final By.ByTagName BUTTON_CONTAINER_PANTALLA_ORDENES = new By.ByTagName("button");
    public static final By.ByClassName ENCABEZADO_TABLA_CONTAINER_PANTALLA_ORDENES = new By.ByClassName("order_table__thead_item");
    public static final By.ByClassName FLECHA_DERECHA_ICONO_CONTAINER = new By.ByClassName("rural-agro-icon-arrow-right");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("last_order__order");
}