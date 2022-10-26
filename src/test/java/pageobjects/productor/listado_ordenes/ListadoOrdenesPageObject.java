package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class ListadoOrdenesPageObject {
    public static final By.ByTagName EMPTY_STATE_ICON = new By.ByTagName("i");
    public static final By.ByClassName SIN_ORDENES_COMPRA_TEXT = new By.ByClassName("confirmation_page__container__title");
    public static final By.ByClassName CUANDO_TENGAS_TEXT = new By.ByClassName("confirmation_page__container__subtitle");
    public static final By.ByTagName VOLVER_BUTTON = new By.ByTagName("a");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName NOMBRE_PRODUCTOR_CONTAINER = new By.ByClassName("card__content_title");
    public static final By.ByClassName NUMERO_ORDEN_CONTAINER = new By.ByClassName("card__content_ordernumber");
    public static final By.ByClassName FECHA_HORA_GENERACION_CONTAINER = new By.ByClassName("last_order__item_dates");
    public static final By.ByClassName DESCRIPCION_CONTAINER = new By.ByClassName("card__content_description");
    public static final By.ByClassName TENEMOS_PROBLEMA_TEXT = new By.ByClassName("confirmation_page__container__subtitle");
    public static final By.ByClassName TUS_ORDENES_TITTLE = new By.ByClassName("order_page__title");
    public static final By.ByClassName BUSCAR_CUIT_NOMBRE_INPUT = new By.ByClassName("search_bar__input");
    public static final By.ByClassName ORDER_PAGE_HEADER = new By.ByClassName("order_page__top");
    public static final By.ByClassName ENCABEZADO_TABLA_CONTAINER = new By.ByClassName("order_table__thead_item");

}