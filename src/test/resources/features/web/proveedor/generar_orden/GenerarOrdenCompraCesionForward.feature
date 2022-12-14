@financing @proveedor @OrdenCompraCesionForward
Feature: Generar Orden de Compra con Método de Pago Cesión Forward
#Se debe agregar tag @regression para que sea nuevamente incluído en la ejecución del pipeline
#Se espera por actualización de data para proveedor - convenios - broker
  Background:
    Given Se navega al portal Nera proveedor
    And Se ingresa con usuario cjfranzin@gmail.com y password Test123+
    And El proveedor presiona el botón Escoger Proveedor
    And El proveedor presiona el botón Proveedor Profertil
    And El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30568143120 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    When El proveedor hace click en el botón Continuar

  @AG-970 @TEST_ID_AG_1177 @TEST_ID_AG_1178 @TEST_ID_AG_754
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

  @AG-788 @TEST_ID_AG_1135 @TEST_ID_AG_1139 @TEST_ID_AG_1140 @TEST_ID_AG_1141 @TEST_ID_AG_1151 @TEST_ID_AG_1152
  @TEST_ID_AG_1153 @TEST_ID_AG_1155
  Scenario: Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado Cesion de Forward
    Then El proveedor seleciona medio de pago Cesión de forward
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    And La plataforma no permite ingresar ningún valor que sea distinto a numérico
    And La plataforma no permite ingresar monto menor a 1.500.001
    And La plataforma no permite ingresar monto mayor a 12 digitos incluídos 2 decimales
    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    #Obtener el Id del proveedor de otro endpoint
    And Obtener datos de endpoint en bff con ruta agreement?payment_method=2&provider_id=32 y guardar valores en variables
      | agreement_type_desc |
    And El proveedor escoge en tipo de convenio la opcion agreement_type_desc
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    And Obtener datos de endpoint en bff con ruta provider/organization/broker y guardar valores en variables
      | name |
    And El proveedor escoge en gestión del forward la opcion name
    And El proveedor percibe el boton Simular Crédito Habilitado
    #Pendiente boton simular TEST_ID_AG_1156


    @AG-1119 @TEST_ID_AG_1205 @TEST_ID_AG_1204
  Scenario: Proveedor - Generar Orden de Compra - Visualizar campo desplegable Gestión del Forward al ingresar con usuario sin/con negocio directo
    #Se valida que con este usuario <Si contenga> la opción de negocio directo - Sin Corredor
    Then El proveedor seleciona medio de pago Cesión de forward
    #Cuando se desarrolle el MS que devuelva los datos del usuario hay que verificar el valor withbroker que indica true si tiene negocio directo
    And El proveedor presiona el campo desplegable de Gestion del forward y se muestra la opción Sin corredor
    #Se valida que con este usuario <No contenga> la opción de negocio directo - Sin Corredor
#    Then Se navega al portal New Agro proveedor
#    And Se ingresa con usuario ronaldinho@yopmail.com y password Brasil123
#    And El proveedor hace click en el botón Crear Orden
#    And El proveedor ingresa 30568143120 en el campo Ingresá el CUIT
#    And El proveedor hace click en el botón Buscar
#    And El proveedor hace click en el botón del Productor encontrado
#    And El proveedor ingresa Descripción Válida en el campo Descripción
#    And El proveedor hace click en el botón Continuar
#    And El proveedor seleciona medio de pago Cesión de forward
#    And El proveedor presiona el campo desplegable de Gestion del forward y no se muestra la opción Sin Corredor


    ### Pendiente @TEST_ID_AG_1038

  @AG-877 @TEST_ID_AG_1462
  Scenario: Proveedor - Generar Orden de Compra - Respuesta a la Consulta de Márgenes Cesión de Forward (Paso 3)
    Then El proveedor seleciona medio de pago Cesión de forward
    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
    And El proveedor escoge en tipo de convenio la opcion Sub 15% Vto Julio 2023 (FORWARD)
    And El proveedor escoge en gestión del forward la opcion Intagro
    And El proveedor presiona el botón Simular Crédito
    # PENDIENTE: Esperar que esté listo endpoint y comunicación con el banco para e2e
    And El proveedor percibe el boton Confirmar medio de pago Habilitado
    # PENDIENTE: Validar campos del cuadro de simulación con BFF
    And El proveedor borra el contenido del campo Ingresá el monto del crédito
    And El proveedor percibe el boton Simular Crédito Deshabilitado
    # Se espera por resolución de BUG https://ag-tech.atlassian.net/browse/AG-1516
    # And El proveedor no percibe el boton Confirmar medio de pago

  @AG-1148 @TEST_ID_AG_1485
  Scenario: Proveedor - Generar Orden de Compra - Resumen/Simulación P.D. Crédito c/Cesión de Forward (Paso 5)
    Then El proveedor seleciona medio de pago Cesión de forward
    And El proveedor introduce monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
    And El proveedor escoge en tipo de convenio la opcion Sub 15% Vto Julio 2023 (FORWARD)
    And El proveedor escoge en gestión del forward la opcion Intagro
    And El proveedor presiona el botón Simular Crédito
    And El proveedor presiona el botón Confirmar medio de pago
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor hace click en el botón Continuar
    Then Se visualiza el título Revisá que la solicitud esté completa
    And Se visualiza el título Detalles de la orden
    And Se visualiza el título Información de contacto
    And Se visualiza el título Medio de pago
    #Esperar por Servicio para hacer validación de datos e2e
    And El proveedor percibe el boton Enviar orden Habilitado
    And El proveedor hace click sobre botón Volver
    And El proveedor no visualiza el botón Enviar orden
