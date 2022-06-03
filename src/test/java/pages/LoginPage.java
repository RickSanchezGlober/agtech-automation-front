package pages;


import org.openqa.selenium.By;
import pageobjects.LoginPageObject;

public class LoginPage extends BasePage {


    public void clickOnButton(String buttonName) {
        By element = null;
        explicitWait(LoginPageObject.NEW_LOGO);
        switch (buttonName) {
            case "Logueate":
                element = LoginPageObject.LOGUEATE_BUTTON;
                break;
            case "Registrate":
                element = LoginPageObject.REGISTRATE_BUTTON;
                break;
            case "Iniciar sesión":
                element = LoginPageObject.INICIAR_SESION_BUTTON;
                break;
        }
        click(element);
    }

    public void loginWithUserPasswd(String user, String password) {
        write(LoginPageObject.USERNAME_INPUT, user);
        write(LoginPageObject.PASSWORD_INPUT, password);
    }

    public boolean verifyProductorNameIsdisplayed() {
        explicitWait(LoginPageObject.NEW_LOGO);
        return verifyVisibleText(LoginPageObject.NOMBRE_PRODUCTOR_TEXT, "¡Hola Productor S.A!");
    }
}