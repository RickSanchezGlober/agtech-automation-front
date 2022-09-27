@onboarding @proveedor @loginproveedor @prueba
Feature: Proveedor - Login

  Background:
    Given Se navega al portal Nera proveedor

  @regression
  Scenario: Verificar login exitoso proveedor
    Given El proveedor ingresa con usuario cristian.duque@globant.com y password Test123+
#    Then El proveedor visualiza el mensaje Hola MONSANTO ARGENTINA S.R.L.
    Then El proveedor visualiza el mensaje Hola

  @regresion
  Scenario Outline: Usuario no se puede autenticar con credenciales invalidas
    Given El proveedor ingresa con usuario <user> y password <password>
    Then Se deniegua el acceso con <reason>
#Se comenta las credenciales incorrectas, pq se bloquea el user
    Examples:
      |user                      |password     |reason|
      #|cristian.duque@globant.com|badpassword  |credenciales_incorrectas|
      |bob4@bob.com               |testing123  |credenciales_incorrectas|
      |cristian.duqye@globant.com|             |boton_deshabilitado     |
      |                          |testing123   |boton_deshabilitado     |
      |                          |             |boton_deshabilitado     |

  @regression
  Scenario: Usuario puede desloguearse del portal
    Given Usuario logueado en el portal Nera
#    When El proveedor visualiza el mensaje Hola MONSANTO ARGENTINA S.R.L.
    When El proveedor visualiza el mensaje Hola
    And Se hace click sobre el botón Cerrar sesión
    Then El proveedor se deslogueo y visualiza el mensaje Inicio de sesión

  @AG-592 @AG-1436 @AG-1437 @AG-1308
  Scenario Outline: Usuario ve pantalla de error cuando ocurre una falla en el proceso de login y registro
    Given Usuario logueado en el portal Nera
    And Se produce un error en el proceso de <process>
    Then  El proveedor debería ver la página de error del <process>
    And El proveedor después de darle click en el botón <button> Debería ser redirigido a la página del <process>
    Examples:
      |process |button          |
      |login   |Iniciar sesion  |
      |signup  |Registrarse     |
