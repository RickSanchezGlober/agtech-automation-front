@payments @regression
Feature:  Productor - Detalle de Órdenes

  @loginSuccessful
  Scenario:  Verifico Pantalla de Detalle de órdenes
    Given Se ingresa al portal Galicia Rural
    And Se hace click sobre el boton Logueate
    When Se loguea con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el boton Iniciar sesión
    And Se hace click en el boton Ver órdenes
    And Se hace click sobre el detalle de una orden
    Then Se visualiza Fecha y Hora del detalle en Sidesheet
    And Se visualiza Número de orden del detalle en Sidesheet
    And Se visualiza Nombre del Proveedor del detalle en Sidesheet
    And Se visualiza Descripción de la Orden del detalle en Sidesheet
    And Se visualiza Precio del detalle en Sidesheet
    And Se visualiza TNA del detalle en Sidesheet
    And Se visualiza Fecha Vencimiento del detalle en Sidesheet
    And Se visualiza Medio de pago del detalle en Sidesheet
    And Se visualiza Medio de pago disponible del detalle en Sidesheet
    And Se visualiza Botón Confirmar del detalle en Sidesheet
