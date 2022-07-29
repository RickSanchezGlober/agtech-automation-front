package pageobjects.proveedor;

import org.openqa.selenium.By;

public class HomeUltimasOperacionesPageObject {
    public static final By.ByCssSelector CREAR_ORDEN_BUTTON = new By.ByCssSelector("div:nth-child(1) main.layout__container.light div.order_page__container section.order_page__top > button.button__container.button__container_primary:nth-child(4)");
    public static final By.ByCssSelector VER_TODAS_BUTTON = new By.ByCssSelector("main.layout__container.light div.home__container section.last_orders:nth-child(3) div.card_component.default.last_orders__container div.last_orders__header div.last_orders__button_top > button.button__container.button__container_secondary");
    public static final By.ByXPath INGRESA_CUIT_LABEL = new By.ByXPath("//label[contains(text(),'Ingresá el CUIT')]");
    public static final By.ByClassName ESCRIBI_11_NUMEROS_TEXT = new By.ByClassName("input__container__helper_text_");
    public static final By.ByTagName BUSCAR_BUTTON = new By.ByTagName("button");
    public static final By.ByClassName LUPA_ICONO = new By.ByClassName("rural-agro-icon-search");
}