@payments @proveedor @generarOrdenCompraSolaFirma
Feature: Generar Orden de Compra. Identificacion del cliente. Detalles de la orden. Medios de pago.

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
    When El proveedor hace click en el botón Crear Orden

  @TEST_ID_AG-529 @TEST_ID_AG-530 @TEST_SET_ID_AG-2078 @regression

  Scenario: Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar pantalla nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar ingreso de CUIT nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar busqueda de productor asociado al CUIT valido
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar Error State búsqueda de CUIT
    Then El proveedor visualiza el boton Buscar Deshabilitado
    And El proveedor visualiza Ingresá el CUIT en la pantalla Identificación de cliente
    And El proveedor visualiza Escribí 11 números en la pantalla Identificación de cliente
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And Se lee el cuit en formato correcto
    And El proveedor visualiza el boton Buscar Habilitado
    And El proveedor hace click en el botón Buscar
    And Verificar pantalla de error si la conexion con el MS customer-validation no se realiza correctamente
    And Recuperar datos de servicios api bff con ruta customer-validation/ y guardar variables abajo
      | cuit_teradata |
      | name          |
      | clean_loan    |
      | forward_loan  |
      | is_mipyme     |
    And El proveedor puede ver Datos del Productor Asociado
      | name          |
      | cuit_teradata |

  @TEST_ID_AG-533 @TEST_ID_AG-534 @TEST_ID_AG-535 @TEST_ID_AG-536 @regression
  Scenario: Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar pantalla de Descripcion de la orden
  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar largo del campo alfanumerico descripcion
  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar habilitacion del boton Continuar
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    Then El proveedor visualiza Describe el detalle de la orden de pago para el productor en la pantalla detalle de la orden
    And El proveedor visualiza Descripción en la pantalla detalle de la orden
    And El proveedor visualiza Hasta 40 caracteres en la pantalla detalle de la orden
    #en este paso se intentan ingresar 41 o 42 carateres aleatorios
    And El proveedor no puede ingresar mas de 40 caracteres
    And El proveedor visualiza el boton Continuar Habilitado

  @TEST_ID_AG-537 @TEST_ID_AG-538 @regression
  Scenario: Proveedor - Generar Orden de Compra - Medios de Pago - Validar pantalla de Medios de pago
  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar convenios asociados al producto (Crédito a Sola Firma)
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    Then El proveedor visualiza Elegí el medio de pago con el cual el productor va a pagar la orden. en la pantalla detalle de la orden
    And El proveedor observa Medios de Pagos Disponibles
    And El proveedor observa Nombre de Medio de pago
    And El proveedor observa Descripción del Medio de pago
    And El proveedor observa Logo de la entidad bancaria
    And El proveedor observa Nombre de la entidad bancaria

  @TEST_ID_AG-1277 @TEST_ID_AG-1278 @TEST_ID_AG-1280 @TEST_ID_AG-1281 @TEST_ID_AG-539
  @TEST_ID_AG-540 @TEST_ID_AG-541 @regression
  Scenario: Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Validar pantalla simular crédito
  Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Verifico modificación en el campo monto
  Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Verifico monto actualizado en la simulación
  Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Verifico convenio actualizado en la simulación
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar pantalla Monto de la Orden
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar habilitacion del boton Simular Crédito
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar deshabilitacion del boton Simular Crédito
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    Then El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation.txt
      | TNA del crédito            | farmer.tna                    |
      | CFT                        | cft                           |
      | Interés                    | installments.interest_nominal |
      | Total Crédito a sola firma | amount                   |
      | Cuota única, vencimiento:  | due_date                      |
    And El proveedor visualiza el boton Confirmar medio de pago Habilitado
    #usando la respuesta del servicio custumer-validation usado en el caso @TEST_ID_AG-529
    And Validar Nombre del Productor
      | name |
    And Se borra el campo Ingresá el monto del crédito
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor no visualiza el boton Confirmar medio de pago


  @TEST_ID_AG-1279 @TEST_ID_AG-1270 @regression
  Scenario: Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Verifico modificación en el campo convenio
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar Monto con decimales
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And Se permite ingresar hasta 2 decimales
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And El proveedor cambia en subsidio de tasa opcion Linea Base Vto Julio 2023
    And El proveedor visualiza el boton Simular Crédito Habilitado
    Then El proveedor no visualiza el boton Confirmar medio de pago

  @TEST_ID_AG-542 @TEST_ID_AG-622 @TEST_ID_AG-1379 @regression
  Scenario: Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar largo del campo numerico Monto
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar Monto negativo
  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar botón cerrar
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And No se puede ingresar mas de 12 digitos en el campo Ingresá el monto del crédito
    Then Imposible escribir caracteres especiales en el campo Ingresá el monto del crédito
    And El proveedor hace click en el botón X
    And El proveedor no visualiza el boton Simular Crédito

  @TEST_ID_AG-2049 @TEST_ID_AG-2089 @regression
  Scenario: Proveedor - Generar Orden de Compra - Identificación de Cliente (Formato NO válido) - Validar error CUIT formato no valido
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar CUIT de productor con requisitos insuficientes
    And El proveedor ingresa CUIT Inválido en el campo Ingresá el CUIT
    #El mensaje debe ser "CUIT no válido", bug reportado
    Then El proveedor ve el mensaje de error El número de CUIT es incorrecto
    And El proveedor ingresa cuit no autorizado en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And Se visualiza pantalla de error Cuit no autorizado

  @TEST_ID_AG-2145 @regression
  Scenario: Proveedor - Generar Orden de Compra - Selección del Medio de Pago - Validar Error State en Selección del Medio de Pago
  Proveedor - Generar Orden de Compra - Selección del Medio de Pago - Validar el botón "Intentar nuevamente"
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    Then Verificar pantalla de error si la conexion con el MS agreement no se realiza correctamente

  @TEST_SET_ID_AG-2102 @regression
  Scenario: Proveedor - Generar Orden de Compra - Simulación NO exitosa - Validar Error State Simulación no exitosa
  Proveedor - Generar Orden de Compra - Simulación NO exitosa - Validar el botón "Intentar nuevamente"
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And Consumir api bff con ruta simulation con body bff_simulation.txt
    Then Verificar pantalla de error si la conexion con el MS simulacion no se realiza correctamente
    And Se hace click en el boton Intentar nuevamente de la pantalla de error
    And Se puede ver el botón Simular Crédito

  @TEST_SET_ID_AG-2113 @regression
  Scenario: Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Validar productor sin margen superior al monto
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto grande en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And Consumir api bff con ruta simulation con body bff_simulation_monto_grande.txt
    Then Verificar pantalla de error productor sin margen superior al monto


  @TEST_SET_ID_AG-2135 @regression
  Scenario: Proveedor - Generar Orden de Compra - Confirmar Orden - Validar Error cuenta embargada
  Proveedor - Generar Orden de Compra - Confirmar Orden - Validar el botón "Ir a órdenes"
    And El proveedor ingresa cuit cuenta embargada en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And El proveedor hace click en el botón Confirmar medio de pago
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor hace click en el botón Continuar
    And El proveedor hace click en el botón Enviar orden
    Then Ocurre un error. Cliente con cuenta embargada
    And El proveedor hace click en el botón Ir a órdenes
    And El proveedor vuelve a la Home

    #Cambio este caso para aca pq tuve a cambiar el cuit a 30568143120
  @TEST_SET_ID_AG-1543 @TEST_SET_ID_AG-2123 @regression
  Scenario: Proveedor - Generar Orden de Compra - Confirmar - Verificar Loader
  Proveedor - Generar Orden de Compra - Confirmar - Verificar pantalla de éxito
  Proveedor - Generar Orden de Compra - Confirmar Orden - Validar Error State al Enviar la Orden
  Proveedor - Generar Orden de Compra - Confirmar Orden - Validar el botón "Ir a órdenes"
    And El proveedor ingresa cuit para confirmación en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And El proveedor hace click en el botón Confirmar medio de pago
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor hace click en el botón Continuar
    And El proveedor hace click en el botón Enviar orden
    #Coemnto este paso pq hay que pegarle a simulacion para volver a confirmar
#    And Consumir api que que confirma la creacion de orden bff con ruta orders/confirm y body bff_confirm.txt
    Then Se visualiza la pantalla de Orden generada y enviada exitosamente
    And El proveedor hace click en el botón Ir a órdenes
    And El proveedor vuelve a la Home