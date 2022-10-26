package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class ListadoOrdenesProductorPageObject {
    public static final By.ByClassName BUSCAR_CUIT_NOMBRE_INPUT = new By.ByClassName("search_bar__input");
    public static final By.ByTagName BUTTON_CONTAINER_PANTALLA_ORDENES = new By.ByTagName("button");
    public static final By.ByClassName ENCABEZADO_TABLA_CONTAINER_PANTALLA_ORDENES = new By.ByClassName("order_table__thead_item");
    public static final By.ByClassName FLECHA_DERECHA_ICONO_CONTAINER = new By.ByClassName("rural-agro-icon-arrow-right");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName LUPA_BUTTON = new By.ByClassName("search_bar__button");
    public static final By.ByClassName RESULTADOS_SPAN = new By.ByClassName("counter__totalizer");
    public static final By.ByClassName TUS_ORDENES_TITTLE = new By.ByClassName("order_page__title");
    public static final By.ByClassName OPERACIONES_PROXIMAS_VENCER = new By.ByClassName("next_expiration_orders__title");
    //Botones <> para el paginado
    public static final By.ByClassName COUNTER_BUTTON_CONTAINER = new By.ByClassName("counter__button");
    public static final By.ByClassName PAGINADO_SELECT = new By.ByClassName("counter__select");
    //Elements of Empty state orders
    public static final By.ByClassName EMPTY_STATE_ICON = new By.ByClassName("rural-agro-icon-empty-order");
    public static final By.ByClassName NO_ENCONTRAMOS_OPERACIONES_TITTLE = new By.ByClassName("confirmation_page__container__title");
    public static final By.ByClassName REVISA_LOS_FILTROS_SUBTITTLE = new By.ByClassName("confirmation_page__container__subtitle");
}