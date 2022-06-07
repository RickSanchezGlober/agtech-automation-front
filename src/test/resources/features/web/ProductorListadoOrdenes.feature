@payments @listadoOrdenes
Feature:  Productor - Listado de órdenes

  @TEST_ID_AG-275 @regression
  Scenario:  Productor - Listado de órdenes - Verifico pantalla Empty State
    Given Se ingresa al portal Galicia Rural
    And Se hace click sobre el boton Logueate
    And Se loguea con usuario cristian.duque+04@globant.com y password Colombia123
    And Se hace click sobre el boton Iniciar sesión
    When Se hace click en el boton Ver órdenes
    Then Se visualiza icono en la pantalla Empty State
    And Se visualiza Todavía no tenés órdenes de compra en la pantalla Empty State
    And Se visualiza Cuando las tengas vas a ver tus órdenes de compra acá. en la pantalla Empty State
    #En Firtma no esta el boton volver, pero deberia estar
    And Se visualiza Botón Volver en la pantalla Empty State

  @TEST_ID_AG-276 @regression
  Scenario:  Productor - Listado de órdenes - Verifico listado de órdenes recientes
    Given Se ingresa al portal Galicia Rural
    And Se hace click sobre el boton Logueate
    And Se loguea con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el boton Iniciar sesión
    When Se hace click en el boton Ver órdenes
    #Cuando podamos pegarle al servicio que trae las ordenes lo idea seria hacer e2e
    Then Se muestra un listado de 5 órdenes
    And Se muestra Nombre del productor de las 5 órdenes
    And Se muestra Número de orden de las 5 órdenes
    And Se muestra Fecha y hora de generación de las 5 órdenes
    And Se muestra Descripción de las 5 órdenes

  @TEST_ID_AG-277 @regression
  Scenario:  Productor - Listado de órdenes - Verifico orden del listado de las órdenes
    Given Se ingresa al portal Galicia Rural
    And Se hace click sobre el boton Logueate
    And Se loguea con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el boton Iniciar sesión
    When Se hace click en el boton Ver órdenes
    Then Se muestra un listado de órdenes ascendente
