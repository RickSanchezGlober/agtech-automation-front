package pageobjects.proveedor.listado_ordenes;

import org.openqa.selenium.By;

public class HomeUltimasOperacionesPageObject {
    public static final By.ByCssSelector HOME_BUTTON_CONTAINER = new By.ByCssSelector("button");
    public static final By.ByXPath INGRESA_CUIT_LABEL = new By.ByXPath("//label[contains(text(),'Ingresá el CUIT')]");
    public static final By.ByClassName ESCRIBI_11_NUMEROS_TEXT = new By.ByClassName("input__container__helper_text_");
    public static final By.ByClassName BUSCAR_BUTTON = new By.ByClassName("button__container_secondary");
    public static final By.ByClassName LUPA_ICONO = new By.ByClassName("rural-agro-icon-search");
    public static final By.ByClassName ORDENES_CONTAINER = new By.ByClassName("last_order__order");
    public static final By.ByClassName ULTIMAS_REALIZADAS_TITTLE = new By.ByClassName("last_orders__title");
    public static final By.ByClassName ORDENES_TITTLE = new By.ByClassName("order_page__title");
    public static final By.ByClassName OPERACIONES_PROXIMAS_VENCER = new By.ByClassName("next_expiration_orders__title");
    public static final By.ByClassName CONTADOR_ORDENES_PROXIMAS_VENCER_ICONO = new By.ByClassName("next_expiration_orders__counter");
    public static final By.ByClassName FLECHA_DERECHA_ICONO_CONTAINER = new By.ByClassName("rural-agro-icon-arrow-right");
}