@regression @onboarding @productor @login @proveedor @loginProductor
Feature: Login

  Background:
  Given Se navega al portal New Agro productor

    @prueba
  Scenario: Verificar login exitoso productor
    Given Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    Then Se logueo y visualiza el mensaje Te damos la bienvenida a Agtech

  Scenario Outline: Usuario no se puede autenticar con credenciales invalidas
    Given Se ingresa con usuario <user> y password <password>
    Then Se espera que se deniegue el acceso con <reason>

    Examples:
    |user                      |password     |reason|
    |cristian.duque@globant.com|badpassword  |credenciales_incorrectas|
    |bob1@bob.com               |testing123  |credenciales_incorrectas|
    |cristian.duqye@globant.com|             |boton_deshabilitado     |
    |                          |testing123   |boton_deshabilitado     |
    |                          |             |boton_deshabilitado     |


  Scenario: Usuario puede desloguearse del portal
    Given Usuario logueado en el portal New Agro productor
    When Se hace click sobre el botón Cerrar sesión
    Then Se deslogueo y visualiza el mensaje Inicio de sesión

