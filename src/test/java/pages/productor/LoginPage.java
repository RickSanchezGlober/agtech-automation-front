package pages.productor;

import org.openqa.selenium.By;
import pageobjects.productor.LoginPageObject;
import pages.BasePage;

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
            case "Cerrar sesión":
                element = LoginPageObject.CERRAR_SESION_BUTTON;
        }
        explicitWait(element);
        click(element);
    }

    public void loginWithUserPasswd(String user, String password) {
        explicitWait(LoginPageObject.LOGUEATE_BUTTON);
        clickOnButtonByName("Logueate");
        explicitWait(LoginPageObject.USERNAME_INPUT);
        write(LoginPageObject.USERNAME_INPUT, user);
        write(LoginPageObject.PASSWORD_INPUT, password);
        click(LoginPageObject.INICIAR_SESION_BUTTON);
    }

    public boolean verifyMessageIsdisplayed(String action,String message) {
        switch (action) {
            case "logueo":
                return verifyVisibleText(LoginPageObject.MENSAJE_BIENVENIDA_TEXT, message);

            case "deslogueo":
                return verifyVisibleText(LoginPageObject.MENSAJE_INICIO_SESION_TEXT, message);
        }
        return false;
    }

    public boolean verifyAccesDenied(String reason) {
      switch (reason) {
          case "credenciales_incorrectas":
              String message = "Algunos de los datos que escribiste no coincide.\n" +
                      "Intentá nuevamente.";
              return verifyVisibleText(LoginPageObject.MENSAJE_ERROR_LOGIN,message);

          case "boton_deshabilitado":
              String attribute = "disabled";
              return isAttributePresent(LoginPageObject.INICIAR_SESION_BUTTON,attribute);
      }
        return false;
    }


}
