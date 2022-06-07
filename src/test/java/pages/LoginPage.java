package pages;

import org.openqa.selenium.By;
import pageobjects.LoginPageObject;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        explicitWait(LoginPageObject.NEW_LOGO);
        switch (buttonName) {
            case "Logueate":
                element = LoginPageObject.LOGUEATE_BUTTON;
                break;
            case "Iniciar sesión":
                element = LoginPageObject.INICIAR_SESION_BUTTON;
                break;
        }
        click(element);
    }

    public void loginWithUserPasswd(String user, String password) {
        explicitWait(LoginPageObject.USERNAME_INPUT);
        write(LoginPageObject.USERNAME_INPUT, user);
        write(LoginPageObject.PASSWORD_INPUT, password);
    }

    public boolean verifyMessageIsdisplayed(String message) {
        explicitWait(LoginPageObject.NEW_LOGO);
        return verifyVisibleText(LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);
    }
}
