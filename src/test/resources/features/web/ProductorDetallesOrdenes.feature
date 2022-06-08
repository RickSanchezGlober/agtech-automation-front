@payments @detallesOrdenes
Feature:  Productor - Detalle de Órdenes

  Background:
    Given Se navega al portal Galicia Rural
    And Se hace click sobre el botón Logueate
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    When Se hace click en el botón Ver órdenes

  @TEST_ID_AG-279 @TEST_ID_AG-280 @regression
     #reportdado bug https://ag-tech.atlassian.net/browse/AG-763 para @TEST_ID_AG-280
  Scenario:  Productor - Detalle de Órdenes -Verifico Pantalla de Detalle de órdenes
             Productor - Detalle de Órdenes - Verifico Detalle de medios de pago
    When Se hace click sobre el detalle de una orden
    Then Se visualiza Fecha y Hora del detalle en Sidesheet
    And Se visualiza Número de orden del detalle en Sidesheet
    And Se visualiza Nombre del Proveedor del detalle en Sidesheet
    And Se visualiza Descripción de la Orden del detalle en Sidesheet
    And Se visualiza Monto del detalle en Sidesheet
    And Se visualiza TNA del detalle en Sidesheet
    And Se visualiza Fecha Vencimiento del detalle en Sidesheet
    And Se visualiza Medio de pago del detalle en Sidesheet
    And Se visualiza Medio de pago disponible del detalle en Sidesheet
    And Se visualiza Botón Confirmar del detalle en Sidesheet
    And Se visualiza Descripción de medio de pago disponible del detalle en Sidesheet