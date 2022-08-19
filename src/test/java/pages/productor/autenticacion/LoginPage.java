package pages.productor.autenticacion;

import org.openqa.selenium.By;
import pageobjects.productor.autenticacion.LoginPageObject;
import pages.BasePage;

public class LoginPage extends BasePage {

    public LoginPage() {
        super();
    }

    public static String accessToken;

    public void clickOnButtonByName(String buttonName) {
        By element = null;
        explicitWait(LoginPageObject.NEW_LOGO);
        switch (buttonName) {
            case "Logueate":
                element = LoginPageObject.LOGUEATE_BUTTON;
                break;
            case "Iniciar sesion":
                element = LoginPageObject.INICIAR_SESION_BUTTON;
                break;
            case "Cerrar sesión":
                click(LoginPageObject.HOME_MENU_BUTTON);
                element = LoginPageObject.CERRAR_SESION_BUTTON;
                break;
            case "Registrarse":
                element = LoginPageObject.REGISTRARSE_BUTTON;
                break;
            case "Cambiar contraseña":
                click(LoginPageObject.HOME_MENU_BUTTON);
                element = LoginPageObject.CAMBIAR_CONTRASEÑA_BUTTON;
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

    public void loginWithUserPasswdAfter(String user, String password) {
        explicitWait(LoginPageObject.USERNAME_INPUT);
        write(LoginPageObject.USERNAME_INPUT, user);
        write(LoginPageObject.PASSWORD_INPUT, password);
        click(LoginPageObject.INICIAR_SESION_BUTTON);

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

    public boolean verifyErrorPageDisplayed(String process) {
        String message ="";
        switch (process) {
            case "login":
                message = "Algo sucedió y no pudimos completar tu login";
                return verifyVisibleText(LoginPageObject.PAGINA_ERROR,message);

            case "signup":
                message = "Algo sucedió y no pudimos completar tu registro";
                return verifyVisibleText(LoginPageObject.PAGINA_ERROR,message);
        }
        return false;
    }


}
