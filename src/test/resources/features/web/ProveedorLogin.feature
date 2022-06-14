@onboarding @proveedor
Feature: Login

  @regression @loginProveedor
  Scenario: Verificar login exitoso proveedor
    Given Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    Then Se visualiza el mensaje Te damos la bienvenida a Agtech