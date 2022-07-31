@financing @proveedor @OrdenCompraCesionForward
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
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
    And El proveedor verifica que se muestre Ingresá el Monto del Crédito
    And El proveedor verifica que se muestre Tipo de Convenio

  @TEST_ID_AG-788 @regression
  Scenario: Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado Cesion de Forward
    Then El proveedor seleciona medio de pago Cesión de forward
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    And La plataforma no permite ingresar ningún valor que sea distinto a numérico
    And La plataforma no permite ingresar monto menor a 1.500.001
    And La plataforma no permite ingresar monto mayor a 12 digitos incluídos 2 decimales
    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    #Obtener el Id del proveedor de otro endpoint
    And Obtener datos de endpoint en bff con ruta agreement?payment_method=2&provider_id=116 y guardar valores en variables
      | agreement_type_desc |
    And El proveedor escoge en tipo de convenio la opcion agreement_type_desc
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    And Obtener datos de endpoint en bff con ruta organization/broker y guardar valores en variables
      | name |
    And El proveedor escoge en gestión del forward la opcion name
    And El proveedor percibe el boton Simular Crédito Habilitado


    @TEST_ID_AG-1119 @regression
  Scenario: Proveedor - Generar Orden de Compra - Visualizar campo desplegable Gestión del Forward al ingresar con usuario sin/con negocio directo
    #Se valida que con este usuario <Si contenga> la opción de negocio directo - Sin Corredor
    Then El proveedor seleciona medio de pago Cesión de forward
    And El proveedor presiona el campo desplegable de Gestion del forward y se muestra la opción Sin corredor
    #Se valida que con este usuario <No contenga> la opción de negocio directo - Sin Corredor
    Then Se navega al portal New Agro proveedor
    And Se ingresa con usuario ronaldinho@yopmail.com y password Brasil123
    And El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Cesión de forward
    And El proveedor presiona el campo desplegable de Gestion del forward y no se muestra la opción Sin Corredor
    #Cuando se desarrolle el MS que devuelva los datos del usuario hay que verificar el valor withbroker que indica true si tiene negocio directo

#  @TEST_ID_AG-877 @regression
#  Scenario: Proveedor - Generar Orden de Compra - Respuesta a la Consulta de Márgenes Cesión de Forward (Paso 3)
#    Then El proveedor seleciona medio de pago Cesión de forward
#    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
#    And El proveedor escoge en tipo de convenio la opcion Sub 8% Vto Julio 2022
#    And El proveedor escoge en gestión del forward la opcion Intagro
#    And El proveedor presiona el botón Simular Crédito