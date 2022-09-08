@onboarding @productor @login @loginProductor
Feature: Login

  Background:
    Given Se navega al portal New Agro productor

  @regression
  Scenario: Verificar login exitoso productor
    Given Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    Then Se logueo y visualiza el mensaje Te damos la bienvenida a Agtech

  @regression
  Scenario Outline: Usuario no se puede autenticar con credenciales invalidas
    Given Se ingresa con usuario <user> y password <password>
    Then Se espera que se deniegue el acceso con <reason>
#Se comenta las credenciales incorrectas, pq se bloquea el user
    Examples:
      | user                       | password   | reason                   |
#    |cristian.duque@globant.com|badpassword  |credenciales_incorrectas|
      | bob4@bob.com               | testing123 | credenciales_incorrectas |
      | cristian.duqye@globant.com |            | boton_deshabilitado      |
      |                            | testing123 | boton_deshabilitado      |
      |                            |            | boton_deshabilitado      |

  @regression
  Scenario: Usuario puede desloguearse del portal
    Given Usuario logueado en el portal New Agro
    When Se hace click sobre el botón Cerrar sesión
    Then Se deslogueo y visualiza el mensaje Inicio de sesión

  @AG-592 @AG-1436 @AG-1437 @AG-1308
  Scenario Outline: Usuario ve pantalla de error cuando ocurre una falla en el proceso de login y registro
    Given Usuario logueado en el portal New Agro
    And Ocurre un error en el proceso de <process>
    Then  Debería ver la página de error del <process>
    And Después de darle click en el botón <button> Debería ser redirigido a la página del <process>
    Examples:
      | process | button         |
      | login   | Iniciar sesion |
      | signup  | Registrarse    |



