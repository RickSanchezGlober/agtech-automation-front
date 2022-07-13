package pageobjects.proveedor;

import org.openqa.selenium.By;

public class GenerarOrdenCompraSolaFirmaContactoPageObject {
    public static final By.ByClassName DATOS_CONTACTO_TITLE = new By.ByClassName("brief_description");
    public static final By.ByClassName DATOS_CONTACTO_LABEL_CONTAINER = new By.ByClassName("input__container__label_");
    public static final By.ByClassName CONTINUAR_BUTTON = new By.ByClassName("button__container_primary");
    public static final By.ById NOMBRE_APELLIDO_INPUT = new By.ById("name");
    public static final By.ById CORREO_ELECTRONICO_INPUT = new By.ById("email");
    public static final By.ById CODIGO_AREA_INPUT = new By.ById("areaCode");
    public static final By.ById NUMERO_CELULAR_INPUT = new By.ById("phoneNumber");
}