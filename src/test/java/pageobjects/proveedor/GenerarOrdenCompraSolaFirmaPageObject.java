package pageobjects.proveedor;

import org.openqa.selenium.By;

public class GenerarOrdenCompraSolaFirmaPageObject {
    public static final By.ByCssSelector CREAR_ORDEN_BUTTON = new By.ByCssSelector("p.home__first_section__action_text");
    public static final By.ByClassName NUEVA_ORDEN_PAGO_HEADER = new By.ByClassName("step_layout__main_title");
    public static final By.ById INGRESA_EL_CUIT_INPUT = new By.ById("cuit");
    public static final By.ByClassName INGRESA_EL_CUIT_LABEL = new By.ByClassName("input__container__label_");
    public static final By.ByXPath BUSCAR_BUTTON = new By.ByXPath("//button[contains(text(),'Buscar')]");
    public static final By.ByClassName PRODUCTOR_ARROW = new By.ByClassName("card_icon_arrow");
    public static final By.ById DESCRIPCION_INPUT = new By.ById("description");
    public static final By.ByClassName DESCRIPCION_LABEL = new By.ByClassName("input__container__label_");
    public static final By.ByClassName CONTINUAR_BUTTON = new By.ByClassName("button__container_primary");
    public static final By.ByClassName DESCRIBE_DETALLE_ORDEN_TITLE = new By.ByClassName("title_details");
    public static final By.ByClassName HASTA_40_CARACTERES_TEXT = new By.ByClassName("input__container__helper_text");
    public static final By.ByClassName ESCRIBI_11_NUMEROS_TEXT = new By.ByClassName("input__container__helper_text_");
    public static final By.ByClassName DATOS_PRODUCTOR_ASOCIADO_TEXT = new By.ByClassName("card_icon_description");
    public static final By.ByClassName NOMBRE_PRODUCTOR_ASOCIADO_TEXT = new By.ByClassName("card_icon_title");
    public static final By.ByClassName ELEGI_MEDIO_PAGO_TITLE = new By.ByClassName("payment_page__title_payment");
    public static final By.ByClassName PAYMENT_CARD_CONTAINER = new By.ByClassName("custom_payment_card");
    public static final By.ByClassName PAYMENT_CARD_NAME_CONTAINER = new By.ByClassName("payment_card__name");
    public static final By.ByClassName PAYMENT_CARD_DESCRIPTION_CONTAINER = new By.ByClassName("payment_card__description");
    public static final By.ByClassName PAYMENT_CARD_BANK_LOGO_CONTAINER = new By.ByClassName("payment_card__bank_logo");
    public static final By.ByClassName PAYMENT_CARD_BANK_NAME_CONTAINER = new By.ByClassName("payment_card__bank_name");
    public static final By.ById INGRESA_EL_MONTO_CREDITO_INPUT = new By.ById("amount");
    public static final By.ByClassName SUBSIDIO_TASA_DROP_DOWN_LIST = new By.ByClassName("select_input__input");
    public static final By.ByClassName SIMULAR_CREDITO_BUTTON = new By.ByClassName("button__container_secondary");
    public static final By.ByClassName RESULTADO_SIMULACION_TEXT = new By.ByClassName("simulation_voucher__result");
    public static final By.ByClassName SIMULATION_CARD_CONTAINER = new By.ByClassName("simulation_voucher__card");
    public static final By.ByCssSelector CONFIRMAR_MEDIO_PAGO_BUTTON = new By.ByCssSelector("div.modalviewer.modalviewer__aside div.modalcontent.modalcontent__aside.modalcontent__aside_active div.aside_modal div.aside_modal__footer > button.button__container.button__container_primary");
    public static final By.ByClassName X_BUTTON = new By.ByClassName("aside_modal__icon");
    public static final By.ByClassName CUIT_INCORRECTO_TEXT = new By.ByClassName("input__container__helper_text_error");
    public static final By.ByClassName AHORA_NO_ES_POSIBLE_TITTLE = new By.ByClassName("error_page__title");
    public static final By.ByClassName REVISA_TU_CONEXION_SUBTITTLE = new By.ByClassName("error_page__subtitle");
    public static final By.ByClassName LOGO_ERROR_STATE = new By.ByClassName("rural-agro-icon-info");
    public static final By.ByTagName BUTTON_CONTAINER_ERROR_SCREEN = new By.ByTagName("button");
    public static final By.ByClassName CUIT_NO_AUTORIZADO_TITTLE = new By.ByClassName("card_icon_title__disabled");
    public static final By.ByClassName ESTE_CUIT_NO_CUMPLE_TITTLE = new By.ByClassName("card_icon_description__disabled");
}