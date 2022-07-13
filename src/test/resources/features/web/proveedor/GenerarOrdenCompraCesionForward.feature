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
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado

  @TEST_ID_AG-970 @TEST_ID_AG-1177 @regression
  Scenario: Proveedor - Generar Orden de Compra - Visualizar el medio de pago en la lista de los disponibles credito forward
    And El proveedor visualiza el boton Buscar Habilitado
