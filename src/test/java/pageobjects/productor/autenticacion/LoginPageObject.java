package pageobjects.productor.autenticacion;

import org.openqa.selenium.By;

public class LoginPageObject {
    public static final By.ByCssSelector INGRESAR = new By.ByCssSelector(".button__container");
    public static final By.ByClassName NEW_LOGO = new By.ByClassName("header__container__logoname_firstword");
    public static final By.ById USERNAME_INPUT = new By.ById("email");
    public static final By.ById PASSWORD_INPUT = new By.ById("password");
    public static final By.ByXPath INICIAR_SESION_BUTTON = new By.ByXPath("//button[contains(text(),'Iniciar sesión')]");
    public static final By.ByClassName MENSAJE_BIENVENIDA_TEXT = new By.ByClassName("home__container__subtitle");
    public static final By.ByCssSelector MENSAJE_ERROR_LOGIN = new By.ByCssSelector("p.login__container__error_message_first_line");
    public static final By.ByXPath CERRAR_SESION_BUTTON = new By.ByXPath("//button[contains(text(),'Cerrar sesión')]");
    public static final By.ByClassName MENSAJE_INICIO_SESION_TEXT = new By.ByClassName("login__container__subtitle");
    public static final By.ByClassName MENSAJE_REGISTRO_TEXT = new By.ByClassName("register__container__subtitle");
    public static final By.ByCssSelector PAGINA_ERROR = new By.ByCssSelector(".confirmation_page__container__title");
    public static final By.ByCssSelector REGISTRARSE_BUTTON = new By.ByCssSelector("a.button__container.button__container_primary");
    public static final By.ByCssSelector HOME_MENU_BUTTON = new By.ByCssSelector(".profile_desktop__left__name");
    public static final By.ByCssSelector CAMBIAR_CONTRASEÑA_BUTTON = new By.ByCssSelector("li.profile_item__container:nth-child(1) > div");
}