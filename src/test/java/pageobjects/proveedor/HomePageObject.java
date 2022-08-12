package pageobjects.proveedor;

import org.openqa.selenium.By;

public class HomePageObject {
    public static final By.ById ACTUAL_PASSWORD_INPUT = new By.ById("password");
    public static final By.ByCssSelector VALIDATE_PASSWORD_BUTTON = new By.ByCssSelector(".button__container");
    public static final By.ById NEW_PASSWORD_INPUTA = new By.ById("passwordA");
    public static final By.ById NEW_PASSWORD_INPUTB = new By.ById("passwordB");
    public static final By.ByCssSelector CHANGE_PASSWORD_BUTTON = new By.ByCssSelector(".button__container");
    public static final By.ByCssSelector MENSAJE_CAMBIO_CONTRASENA = new By.ByCssSelector(".confirmation_page__container__title");
    public static final By.ByCssSelector LOGIN_BUTTON = new By.ByCssSelector(".button__container");
}
