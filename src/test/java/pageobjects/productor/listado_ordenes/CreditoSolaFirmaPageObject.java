package pageobjects.productor.listado_ordenes;

import org.openqa.selenium.By;

public class CreditoSolaFirmaPageObject {
    public static final By.ByClassName CARD_PAGO = new By.ByClassName("order__descriptioncard_paytext");
    public static final By.ByXPath DESCRIPCION_PAGO = new By.ByXPath("//div[contains(text(),'crédito a sola firma')]");
    public static final By.ByClassName BOTON_CONFIRMAR = new By.ByClassName("button__container_primary");
    public static final By.ByClassName BOTON_CONFIRMAR_DESHABILITADO = new By.ByClassName("button__container_disabled");
    public static final By.ByClassName CONFIRMATION_TITLE = new By.ByClassName("confirmation__container__title");
    public static final By.ByXPath DESCRIPCION_TITLE = new By.ByXPath("//p[contains(text(),'Revisá que todo esté bien y confirmá el pago')]");
    public static final By.ByClassName CONFIRMATION_SUBTITLE = new By.ByClassName("confirmation__container__subtitle");
    public static final By.ByXPath ORDER_TEXT = new By.ByXPath("//p[contains(text(),'Orden No.:')]");
    public static final By.ByClassName PROVEEDOR_TEXT = new By.ByClassName("confirmation__container__provider");
    public static final By.ByClassName DESCRIPCION_ORDEN_TEXT = new By.ByClassName("confirmation__container__product");
    public static final By.ByXPath AFINANCIARCON_TEXT = new By.ByXPath("//p[contains(text(),'A financiar con:')]//following-sibling::p");
    public static final By.ByXPath MONTO = new By.ByXPath("//p[contains(text(),'Monto')]//following-sibling::p");
    public static final By.ByXPath CONDICIONES_TEXT = new By.ByXPath("//p[contains(text(),'Condiciones:')]");
    public static final By.ByXPath TNA = new By.ByXPath("//p[contains(text(),'TNA')]//following-sibling::p");
    public static final By.ByXPath CFT = new By.ByXPath("//p[contains(text(),'CFT')]//following-sibling::p");
    public static final By.ByXPath CFTIVA = new By.ByXPath("//p[contains(text(),'CFT + IVA')]//following-sibling::p");
    public static final By.ByXPath CAPITAL = new By.ByXPath("//p[contains(text(),'Capital')]//following-sibling::p");
    public static final By.ByXPath INTERES = new By.ByXPath("//p[contains(text(),'Interés')]//following-sibling::p");
    public static final By.ByXPath IVA_INTERES = new By.ByXPath("//p[contains(text(),'IVA sobre interés')]//following-sibling::p");
    public static final By.ByXPath SELLADO = new By.ByXPath("//p[contains(text(),'Sellado')]//following-sibling::p");
    public static final By.ByXPath CUOTAS = new By.ByXPath("//p[contains(text(),'Cuotas')]//following-sibling::p");
    public static final By.ByXPath VENCIMIENTO = new By.ByXPath("//p[contains(text(),'Vencimiento del préstamo')]//following-sibling::p");
    public static final By.ByXPath CHECK_TERMINOS = new By.ByXPath("//div[@data-testid='checkbox']");
    public static final By.ByXPath LABEL_TERMINOS = new By.ByXPath("//p[contains(text(),'Al confirmar el pago estás aceptando nuestros')]");
    public static final By.ByPartialLinkText LINK_TERMINOS = new By.ByPartialLinkText("términos y condiciones");
    public static final By.ByClassName LINK_VOLVER = new By.ByClassName("header__container__back");
    public static final By.ByClassName BIG_CFT = new By.ByClassName("single_signature__container__big_cft");

}