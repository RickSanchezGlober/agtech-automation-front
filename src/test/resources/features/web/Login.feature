@onboarding @regression
Feature:  Login

  @loginSuccessful
  Scenario:  Verificar login exitoso
    Given Se ingresa al portal Galicia Rural
    And Se hace click sobre el boton Logueate
    When Se loguea con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el boton Iniciar sesión
    Then El usuario pudo loguearse