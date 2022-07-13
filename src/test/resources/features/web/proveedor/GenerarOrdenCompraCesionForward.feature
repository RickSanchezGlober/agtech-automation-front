@financing @proveedor @OrdenCompraCesionForward
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    And Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    When El proveedor hace click en el botón Continuar


  @TEST_ID_AG-970 @regression
  Scenario: Proveedor - Generar Orden de Compra - Visualizar el medio de pago en la lista de los disponibles credito forward
    Then El proveedor verifica que se muestre Elegí el medio de pago con el cual el productor va a pagar la orden.
    And El proveedor verifica que se muestre Medios de Pagos Disponibles
    And El proveedor verifica que se muestre Nombre de Medio de pago
    And El proveedor verifica que se muestre Descripción del Medio de pago
    And El proveedor verifica que se muestre Logo de la entidad bancaria
    And El proveedor verifica que se muestre Nombre de la entidad bancaria
    And El proveedor seleciona medio de pago Cesión de forward
#    And El proveedor verifica que se muestre Tipo de Convenio
#    And El proveedor escoge en tipo de convenio la opcion Sub 8% Vto Julio 2022

  @TEST_ID_AG-788 @regression
  Scenario: Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado
    Then El proveedor seleciona medio de pago Cesión de forward
#    And El proveedor percibe el boton Simular Crédito Deshabilitado
#    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
#    And El proveedor escoge en tipo de convenio la opcion Sub 8% Vto Julio 2022
#    And El proveedor escoge en gestión del forward la opcion Intagro
#    And El proveedor percibe el boton Simular Crédito Habilitado
#    And El proveedor presiona el botón Simular Crédito
