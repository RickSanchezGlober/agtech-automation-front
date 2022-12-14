package pageobjects.proveedor.generar_orden;

import org.openqa.selenium.By;

public class GenerarOrdenCompraSolaFirmaContactoPageObject {
    public static final By.ByClassName DATOS_CONTACTO_TITLE = new By.ByClassName("brief_description");
    public static final By.ByClassName DATOS_CONTACTO_LABEL_CONTAINER = new By.ByClassName("input__container__label_");
    public static final By.ByClassName CONTINUAR_BUTTON = new By.ByClassName("button__container_primary");
    public static final By.ById NOMBRE_APELLIDO_INPUT = new By.ById("name");
    public static final By.ById CORREO_ELECTRONICO_INPUT = new By.ById("email");
    public static final By.ById CODIGO_AREA_INPUT = new By.ById("areaCode");
    public static final By.ById NUMERO_CELULAR_INPUT = new By.ById("phoneNumber");
    public static final By.ByXPath REVISA_SOLICITUD_COMPLETA_TITLE = new By.ByXPath("//h3[contains(text(),'Revisá que la solicitud esté completa')]");
    public static final By.ByClassName SOLICITUD_COMPLETA_CONTAINER = new By.ByClassName("step_layout__container");
    public static final By.ByClassName VOLVER_BUTTON = new By.ByClassName("goback_button__container");
    public static final By.ByCssSelector ENVIAR_ORDEN_COMPRA_BUTTON = new By.ByCssSelector("#__next > div > main > div.step_layout__container > div.confirmation__button_wrapper_desktop > button");
    public static final By.ByClassName ORDEN_GENERADA_ENVIADA_TITLE = new By.ByClassName("confirmation_page__container__title");
    public static final By.ByClassName RECIBIRAS_NOTIFICACION_SUBTITLE = new By.ByClassName("confirmation_page__container__subtitle");
    public static final By.ByTagName CONFIRMATION_ICON = new By.ByTagName("i");
    //    public static final By.ByTagName IR_A_ORDENES_BUTTON = new By.ByTagName("button");
    public static final By.ByXPath IR_A_ORDENES_BUTTON = new By.ByXPath("//button[contains(text(),'Ir a órdenes')]");
    public static final By.ByClassName CORREO_NO_VALIDO_TEXT = new By.ByClassName("input__container__helper_text_error");
    public static final By.ByTagName EMPTY_STATE_ICON = new By.ByTagName("i");
    public static final By.ByClassName NO_ES_POSIBLE_CONTINUAR_TITLE = new By.ByClassName("error_page__title");
    public static final By.ByClassName TE_PEDIMOS_DISCULPAS_SUBTITLE = new By.ByClassName("error_page__subtitle");
    public static final By.ByClassName DETALLE_DEL_PAGO_CONTAINER = new By.ByClassName("installments__main_container");
}