@onboarding @proveedor
Feature: Login

  @regression @loginProveedor
  Scenario: Verificar login exitoso proveedor
    Given Se navega al portal New Agro proveedor
    Given Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    Then El proveedor visualiza el mensaje Hola Cencosud S.A.