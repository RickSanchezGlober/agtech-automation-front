package pageobjects;

import org.openqa.selenium.By;

public class LoginPageObject {
    public static final By.ByLinkText LOGUEATE_BUTTON = new By.ByLinkText("Logueate");
    public static final By.ByClassName NEW_LOGO = new By.ByClassName("header__container__logoname_firstword");
    public static final By.ById USERNAME_INPUT = new By.ById("email");
    public static final By.ById PASSWORD_INPUT = new By.ById("password");
    public static final By.ByXPath INICIAR_SESION_BUTTON = new By.ByXPath("//button[contains(text(),'Iniciar sesión')]");
    public static final By.ByClassName MENSAJE_BIENVENIDA_TEXT = new By.ByClassName("home__container__subtitle");

}