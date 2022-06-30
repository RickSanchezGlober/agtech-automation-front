package pageobjects.proveedor;

import org.openqa.selenium.By;

public class GenerarOrdenCompraPageObject {
    public static final By.ByLinkText CREAR_ORDEN_BUTTON = new By.ByLinkText("Crear Orden");
    public static final By.ByClassName NUEVA_ORDEN_PAGO_HEADER = new By.ByClassName("step_layout__main_title");
    public static final By.ById INGRESA_EL_CUIT_INPUT = new By.ById("cuit");
    public static final By.ByClassName INGRESA_EL_CUIT_LABEL = new By.ByClassName("input__container__label_");
    public static final By.ByClassName BUSCAR_BUTTON = new By.ByClassName("button__container_secondary");
    public static final By.ByClassName PRODUCTOR_ARROW = new By.ByClassName("card_icon_arrow");
    public static final By.ById DESCRIPCION_INPUT = new By.ById("description");
    public static final By.ByClassName DESCRIPCION_LABEL = new By.ByClassName("input__container__label_");
    public static final By.ByClassName CONTINUAR_BUTTON = new By.ByClassName("button__container_primary");
    public static final By.ByClassName DESCRIBE_DETALLE_ORDEN_TITLE = new By.ByClassName("title_details");
    public static final By.ByClassName HASTA_40_CARACTERES_TEXT = new By.ByClassName("input__container__helper_text");
    public static final By.ByClassName ESCRIBI_11_NUMEROS_TEXT = new By.ByClassName("input__container__helper_text_");
    public static final By.ByClassName DATOS_PRODUCTOR_ASOCIADO_TEXT = new By.ByClassName("card_icon_description");
    public static final By.ByClassName ELEGI_MEDIO_PAGO_TITLE = new By.ByClassName("payment_page__title_payment");
    public static final By.ByClassName PAYMENT_CARD_CONTAINER = new By.ByClassName("custom_payment_card");
    public static final By PAYMENT_CARD_NAME_CONTAINER = new By.ByClassName("payment_card__name");
    public static final By PAYMENT_CARD_DESCRIPTION_CONTAINER = new By.ByClassName("payment_card__description");
    public static final By PAYMENT_CARD_BANK_LOGO_CONTAINER = new By.ByClassName("payment_card__bank_logo");
    public static final By PAYMENT_CARD_BANK_NAME_CONTAINER = new By.ByClassName("payment_card__bank_name");
}