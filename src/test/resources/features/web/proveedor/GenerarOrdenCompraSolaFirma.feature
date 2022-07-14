@payments @proveedor @generarOrdenCompra
Feature: Generar Orden de Compra. Identificacion del cliente. Detalles de la orden. Medios de pago.

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    And El proveedor hace click en el botón Crear Orden

  @TEST_ID_AG-529 @TEST_ID_AG-530 @regression
  Scenario: Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar pantalla nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar ingreso de CUIT nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar busqueda de productor asociado al CUIT valido
    Then El proveedor visualiza el boton Buscar Deshabilitado
    And El proveedor visualiza Ingresá el CUIT en la pantalla Identificación de cliente
    And El proveedor visualiza Escribí 11 números en la pantalla Identificación de cliente
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And Se lee el cuit en formato correcto
    And El proveedor visualiza el boton Buscar Habilitado
    And El proveedor hace click en el botón Buscar
    And Recuperar datos de servicios api bff con ruta customer-validation/ y guardar variables abajo
      | name |
#      | clean_loan   |
#      | forward_loan |
    And El proveedor visualiza CUIT del Productor Asociado en la pantalla Identificación de cliente
    And El proveedor puede ver Datos del Productor Asociado
      | name |
#      | clean_loan   |
#      | forward_loan |

#  @TEST_ID_AG-533 @TEST_ID_AG-534 @TEST_ID_AG-535 @TEST_ID_AG-536 @regression
#  Scenario: Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar pantalla de Descripcion de la orden
#  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar largo del campo alfanumerico descripcion
#  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar habilitacion del boton Continuar
#  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar deshabilitacion del boton Continuar
#    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
#    And El proveedor hace click en el botón Buscar
#    And El proveedor hace click en el botón del Productor encontrado
#    Then El proveedor visualiza Describe el detalle de la orden de pago para el productor en la pantalla detalle de la orden
#    And El proveedor visualiza Descripción en la pantalla detalle de la orden
#    And El proveedor visualiza Hasta 40 caracteres en la pantalla detalle de la orden
#    And El proveedor visualiza el boton Continuar Deshabilitado
#    #en este paso se intentan ingresar 41 o 42 carateres aleatorios
#    And El proveedor no puede ingresar mas de 40 caracteres
#    And El proveedor visualiza el boton Continuar Habilitado
#
#  @TEST_ID_AG-537 @TEST_ID_AG-538 @regression
#  Scenario: Proveedor - Generar Orden de Compra - Medios de Pago - Validar pantalla de Medios de pago
#  Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar convenios asociados al producto (Crédito a Sola Firma)
#    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
#    And El proveedor hace click en el botón Buscar
#    And El proveedor hace click en el botón del Productor encontrado
#    And El proveedor ingresa Descripción Válida en el campo Descripción
#    And El proveedor hace click en el botón Continuar
#    Then El proveedor visualiza Elegí el medio de pago con el cual el productor va a pagar la orden. en la pantalla detalle de la orden
#    And El proveedor observa Medios de Pagos Disponibles
#    And El proveedor observa Nombre de Medio de pago
#    And El proveedor observa Descripción del Medio de pago
#    And El proveedor observa Logo de la entidad bancaria
#    And El proveedor observa Nombre de la entidad bancaria

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
    And El proveedor selecciona en subsidio de tasa opcion Sub 5% Vto Septiembre 2022
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation.txt
      | TNA del crédito            | tna         |
      | CFT                        | cft         |
      | Interés                    | interest    |
      | Total Crédito a sola firma | loan_amount |
      | Cuota única, vencimiento:  | due_date    |
    And El proveedor visualiza el boton Confirmar medio de pago Habilitado
    #usando la respuesta del servicio custumer-validation usado en el caso @TEST_ID_AG-529
    And Validar Nombre del Productor
      | name |
    And Se borra el campo Ingresá el monto del crédito
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor no visualiza el boton Confirmar medio de pago


#  @TEST_ID_AG-1279 @TEST_ID_AG-1270
#  Scenario: Proveedor - Generar Orden de Compra - Simular Crédito a sola firma - Verifico modificación en el campo convenio
#  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar Monto con decimales
#    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
#    And El proveedor hace click en el botón Buscar
#    And El proveedor hace click en el botón del Productor encontrado
#    And El proveedor ingresa Descripción Válida en el campo Descripción
#    And El proveedor hace click en el botón Continuar
#    And El proveedor seleciona medio de pago Crédito a sola firma
#    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
#    And Se permite ingresar hasta 2 decimales
#    And El proveedor selecciona en subsidio de tasa opcion Sub 5% Vto Septiembre 2022
#    And El proveedor hace click en el botón Simular Crédito
#    And El proveedor cambia en subsidio de tasa opcion Sub 8% Vto Septiembre 2022
#    And El proveedor visualiza el boton Simular Crédito Habilitado
    Then El proveedor no visualiza el boton Confirmar medio de pago
#
#  @TEST_ID_AG-542 @TEST_ID_AG-622 @TEST_ID_AG-1379
#  Scenario: Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar largo del campo numerico Monto
#  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar Monto negativo
#  Proveedor - Generar Orden de Compra - Completar Info Medio de Pago Seleccionado - Validar botón cerrar
#    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
#    And El proveedor hace click en el botón Buscar
#    And El proveedor hace click en el botón del Productor encontrado
#    And El proveedor ingresa Descripción Válida en el campo Descripción
#    And El proveedor hace click en el botón Continuar
#    And El proveedor seleciona medio de pago Crédito a sola firma
#    #en este paso se intentan ingresar 13 o 14 digitos
#    # bug 1271 reportado
##    And No se puede ingresar mas de 12 digitos en el campo Ingresá el monto del crédito
#    Then Imposible escribir caracteres especiales en el campo Ingresá el monto del crédito
#    And El proveedor hace click en el botón X
#    And El proveedor no visualiza el boton Simular Crédito
