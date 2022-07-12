@financing @proveedor @OrdenCompraCesionForward
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    And  Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    And Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And El proveedor hace click en el botón Crear Orden
    And El proveedor hace click en el botón Buscar

  @TEST_ID_AG-970 @regression
  Scenario: Proveedor - dalia
    And El proveedor visualiza el boton Buscar Habilitado
