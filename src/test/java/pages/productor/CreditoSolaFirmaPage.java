package pages.productor;

import org.openqa.selenium.By;
import pageobjects.productor.CreditoSolaFirmaPageObject;
import pages.BasePage;

public class CreditoSolaFirmaPage extends BasePage {

    public CreditoSolaFirmaPage() {
        super();
    }

    public boolean verifyCreditoSolaFirmaIsdisplayed() {
        explicitWait(CreditoSolaFirmaPageObject.CARD_PAGO);
        return verifyVisibleText(CreditoSolaFirmaPageObject.DESCRIPCION_PAGO, "Paga con crédito a sola firma");
    }

    public void clickOnElement(String elementName) {
        By element = null;

        switch (elementName) {
            case "check terminos y condiciones":
                explicitWait(CreditoSolaFirmaPageObject.CHECK_TERMINOS);
                element = CreditoSolaFirmaPageObject.CHECK_TERMINOS;
                break;
            case "boton Continuar":
                explicitWait(CreditoSolaFirmaPageObject.BOTON_CONFIRMAR);
                element = CreditoSolaFirmaPageObject.BOTON_CONFIRMAR;
                break;
            case "enlace en la navegación superior de regreso":
                explicitWait(CreditoSolaFirmaPageObject.LINK_VOLVER);
                element = CreditoSolaFirmaPageObject.LINK_VOLVER;
                break;
        }
        click(element);
    }

    public boolean verifyPaymentDetailIsdisplayed() {
        explicitWait(CreditoSolaFirmaPageObject.CONFIRMATION_TITLE);
        return verifyVisibleText(CreditoSolaFirmaPageObject.DESCRIPCION_TITLE, "Revisá que todo esté bien y confirmá el pago");
    }

    public boolean verifyValuesOrderDetail(String value) {
        waitVisibility(CreditoSolaFirmaPageObject.CONFIRMATION_SUBTITLE, "1");
        By element = null;
        switch (value) {
            case "titulo del detalle":
                element = CreditoSolaFirmaPageObject.CONFIRMATION_SUBTITLE;
                break;
            case "campo Orden No.":
                element = CreditoSolaFirmaPageObject.ORDER_TEXT;
                break;
            case "campo Nombre del Proveedor":
                element = CreditoSolaFirmaPageObject.PROVEEDOR_TEXT;
                break;
            case "campo Descripción de la orden":
                element = CreditoSolaFirmaPageObject.DESCRIPCION_ORDEN_TEXT;
                break;
            case "campo A financiar con que debe tener por debajo el valor Crédito a sola firma":
                element = CreditoSolaFirmaPageObject.AFINANCIARCON_TEXT;
                break;
            case "campo Monto":
                element = CreditoSolaFirmaPageObject.MONTO;
                break;
            case "campo Condiciones":
                element = CreditoSolaFirmaPageObject.CONDICIONES_TEXT;
                break;
            case "campo TNA":
                element = CreditoSolaFirmaPageObject.TNA;
                break;
            case "campo CFT":
                element = CreditoSolaFirmaPageObject.CFT;
                break;
            case "campo CFT + IVA":
                element = CreditoSolaFirmaPageObject.CFTIVA;
                break;
            case "campo Capital":
                element = CreditoSolaFirmaPageObject.CAPITAL;
                break;
            case "campo Interés":
                element = CreditoSolaFirmaPageObject.INTERES;
                break;
            case "campo IVA sobre interés":
                element = CreditoSolaFirmaPageObject.IVA_INTERES;
                break;
            case "campo Sellado":
                element = CreditoSolaFirmaPageObject.SELLADO;
                break;
            case "campo Cuotas":
                element = CreditoSolaFirmaPageObject.CUOTAS;
                break;
            case "campo Vencimiento del préstamo":
                element = CreditoSolaFirmaPageObject.VENCIMIENTO;
                break;
            case "check de terminos y condiciones":
                element = CreditoSolaFirmaPageObject.CHECK_TERMINOS;
                break;
            case "label terminos y condiciones":
                element = CreditoSolaFirmaPageObject.LABEL_TERMINOS;
                break;
            case "link terminos y condiciones":
                element = CreditoSolaFirmaPageObject.LINK_TERMINOS;
                break;
            case "boton Confirmar Pago Habilitado":
                element = CreditoSolaFirmaPageObject.BOTON_CONFIRMAR;
                break;
            case "boton Confirmar Pago Deshabilitado":
                element = CreditoSolaFirmaPageObject.BOTON_CONFIRMAR_DESHABILITADO;
                break;
            case "campo CFT en un tamaño especial":
                element = CreditoSolaFirmaPageObject.BIG_CFT;
                break;
        }
        return isDisplayed(element);
    }

    public boolean validateCorrectWidth(String typeValue) {
        By element = null;
        switch (typeValue) {
            case "monto":
                element = CreditoSolaFirmaPageObject.MONTO;
                break;
            case "capital":
                element = CreditoSolaFirmaPageObject.CAPITAL;
                break;
        }

        String stringValue = getText(element);
        return widthNumberIsCorrect(stringValue);
    }

    private boolean widthNumberIsCorrect(String stringValue) {
        boolean isCorrect = false;
        stringValue = stringValue.replace(".", "");
        String[] arrStringValue = stringValue.split("\\,");
        String soloEnteros = arrStringValue[0].substring(1);
        String soloDecimales = arrStringValue[1];
        Integer cantidadEnteros = soloEnteros.length();
        Integer cantidadDecimales = soloDecimales.length();

        //Si la cantidad de numeros enteros es mayor o igual a 1 y menor o igual a 10 y la cantidad de decimales es igual a 2 (e.g. 1.234.567.891,25)
        if((cantidadEnteros >=  1 && cantidadEnteros <=  10) && cantidadDecimales ==  2){ isCorrect = true; }
        return isCorrect;
    }
}