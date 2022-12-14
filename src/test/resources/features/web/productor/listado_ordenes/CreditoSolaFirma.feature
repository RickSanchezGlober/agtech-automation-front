@financing @productor @creditosolofirma
Feature: Visualizar Simulación de Pago c/Crédito a Sola Firma
  #Comentado hasta que tengamos algo mas de productor

  Background:
    Given Se navega al portal New Agro productor
    And Usuario logueado en el portal New Agro
    When Se hace click en el botón Órdenes
    And Se hace click sobre el detalle de una orden
    And Selecciona el medio de pago Credito a sola firma
    And Se presiona sobre el boton Continuar

  @regression @AG-44 @TEST_ID_AG-261 @TEST_ID_AG-283 @TEST_ID_AG-347 @TEST_ID_AG-348
  @TEST_ID_AG-285 @TEST_ID_AG-286 @TEST_ID_AG-313 @TEST_ID_AG-314 @TEST_ID_AG-315 @TEST_ID_AG-316
  @AG-101 @TEST_ID_AG-354
  Scenario: Productor - Simulación Crédito a sola firma - Visualización de confirmación de detalle de ordenes
            Productor - Simulación Credito a sola firma - Validar visual de boton deshabilitado
            Productor - Simulación Credito a sola firma - Validar visual de boton habilitado
            Productor - Simulación Crédito a sola firma - Validar boton de paginado hacia la ventana anterior
            Productor - Simulación Crédito a sola firma - Validar Cantidad de dígitos y decimales de los valores Monto y Capital
            Detalle Financiación: CFT pie de páginación - Visualización en un tamaño especial en la parte inferior de la pantalla
    Then El sistema redirecciona a la pantalla de Confirmación con todos los detalles de la orden
#    And Se muestra el titulo del detalle comento por remapeo
    And Se muestra el campo Orden No.
#    And Se muestra el campo Nombre del Proveedor comento por remapeo
#    And Se muestra el campo Descripción de la orden "comento por remapeo"
    And Se muestra el campo A financiar con que debe tener por debajo el valor Crédito a sola firma
    #Hacer e2e
    And Se muestra el campo Monto
    And Se muestra el campo Condiciones
    And Se muestra el campo TNA
    And Se muestra el campo CFT
    And Se muestra el campo CFT + IVA
    And Se muestra el campo Capital
    And Se muestra el campo Interés
    And Se muestra el campo IVA sobre interés
    And Se muestra el campo Sellado
    And Se muestra el campo Cuotas
    And Se muestra el campo Vencimiento del préstamo
    And Se visualiza el ancho correcto del valor monto en la pantalla de Confirmación con todos los detalles de la orden
    And Se visualiza el ancho correcto del valor capital en la pantalla de Confirmación con todos los detalles de la orden
    And Se muestra el check de terminos y condiciones
    And Se muestra el label terminos y condiciones
    And Se muestra el link terminos y condiciones
    And Se muestra el campo CFT en un tamaño especial
    And Se muestra el boton Confirmar Pago Deshabilitado
    And Se presiona sobre el check terminos y condiciones
    And Se muestra el boton Confirmar Pago Habilitado
    And El sistema redirecciona a la pantalla de Confirmación con todos los detalles de la orden
    And Se presiona sobre el enlace en la navegación superior de regreso
    And Verificar datos de servicio api que lista todas las ordenes bff con ruta orders/producer/as768dfa2s-22dada2-awe2da-2sdi79
    #Método Payments

#  @TEST_ID_AG-44 @TEST_ID_AG-272
#  Scenario: Productor - Simulación Crédito a sola firma - Productor no tiene ubicación de cuenta corriente radicada en CABA
#  No se cuenta con usuario logueado con cuenta corriente del productor estando radicada en CABA

#  @TEST_ID_AG-44 @TEST_ID_AG-274
#  Scenario: Productor - Simulación Crédito a sola firma - Productor no tiene ubicación de cuenta corriente radicada en CABA
#  No se cuenta con usuario logueado sin cuenta corriente del productor estando radicada en CABA


#  @TEST_ID_AG-44 @TEST_ID_AG-349
#  Scenario: Productor - Simulación Crédito a sola firma - Validar boton anclado en versión mobile
#  Pendiente validación en versión mobile