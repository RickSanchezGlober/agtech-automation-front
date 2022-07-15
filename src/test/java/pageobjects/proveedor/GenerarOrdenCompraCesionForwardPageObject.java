package pageobjects.proveedor;

import org.openqa.selenium.By;

public class GenerarOrdenCompraCesionForwardPageObject {
    public static final By.ByClassName NUEVA_ORDEN_PAGO_LAYOUT = new By.ByClassName("step_layout__main_title");
    public static final By.ById MONTO_CREDITO_INPUT = new By.ById("amount");
    public static final By.ByName TIPO_CONVENIO_SELECT = new By.ByName("agreement");
    public static final By.ByName GESTION_FORWARD_SELECT = new By.ByName("broker");
    public static final By.ByClassName SIMULAR_CREDITO_FORWARD_BUTTON = new By.ByClassName("button__container_secondary");
    public static final By.ByClassName ELEGIR_MEDIO_PAGO_TEXT = new By.ByClassName("payment_page__title_payment");
    public static final By.ByClassName CARD_CONTAINER = new By.ByClassName("custom_payment_card");
    public static final By.ByClassName CARD_NAME_CONTAINER = new By.ByClassName("payment_card__name");
    public static final By.ByClassName CARD_DESCRIPTION_CONTAINER = new By.ByClassName("payment_card__description");
    public static final By.ByClassName CARD_BANK_LOGO_CONTAINER = new By.ByClassName("payment_card__bank_logo");
    public static final By.ByClassName CARD_BANK_NAME_CONTAINER = new By.ByClassName("payment_card__bank_name");

    public static final By.ByClassName ERROR_ICON = new By.ByClassName("input__container__wrapper__icon");
}