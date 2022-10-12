package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class ListadoOrdenesFiltrarPageObject {
    public static final By.ByClassName ORDENES_TITTLE = new By.ByClassName("order_page__title");
    public static final By.ByTagName ORDERS_BUTTON_CONTAINER = new By.ByTagName("button");
    public static final By.ByClassName FILTRO_ESTADO_CONTAINER = new By.ByClassName("selection_snippet");
    public static final By.ByClassName FILTRO_MEDIO_PAGO_CONTAINER = new By.ByClassName("orders_filter__payment_content");
    public static final By.ByClassName FILTRO_DE_ORDENES_TITLE = new By.ByClassName("aside_modal__title");
    public static final By.ByClassName TITLE_CONTAINER_FILTER_SCREEN = new By.ByClassName("segment_title");
    public static final By.ByClassName SIDE_MENU_BUTTON_CONTAINER = new By.ByClassName("sidebar_render_options__option");
    public static final By.ByClassName DATE_PICKER_INPUT_CONTAINER = new By.ByClassName("react-datepicker__input-container");
    public static final By.ByClassName DATE_PICKER_INPUT_DESDE = new By.ByClassName("orders_filter__date_picker_start");
    public static final By.ByClassName DATE_PICKER_INPUT_HASTA = new By.ByClassName("orders_filter__date_picker_end");
    public static final By.ByClassName FECHA_ORDENES_CONTAINER = new By.ByClassName("last_order__item_dates");
    public static final By.ByClassName X_FILTROS_BUTTON = new By.ByClassName("rural-agro-icon-close");
    public static final By.ByClassName ULTIMAS_REALIZADAS_TITTLE = new By.ByClassName("last_orders__title");
    public static final By.ByClassName FLECHA_DERECHA_ICONO_CONTAINER = new By.ByClassName("rural-agro-icon-arrow-right");
    public static final By.ByCssSelector HOME_BUTTON_CONTAINER = new By.ByCssSelector("button");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName CHECK_MEDIO_PAGO_CONTAINER = new By.ByClassName("MultiCheckbox_container__6jjx5");
    public static final By.ByClassName BUSCAR_CUIT_NOMBRE_INPUT = new By.ByClassName("search_bar__input");
    public static final By.ByClassName LUPA_BUTTON = new By.ByClassName("search_bar__button");
    //Elements of Empty state orders
    public static final By.ByClassName EMPTY_STATE_ICON = new By.ByClassName("rural-agro-icon-empty-order");
    public static final By.ByClassName NO_ENCONTRAMOS_OPERACIONES_TITTLE = new By.ByClassName("confirmation_page__container__title");
    public static final By.ByClassName REVISA_LOS_FILTROS_SUBTITTLE = new By.ByClassName("confirmation_page__container__subtitle");
}