@payments @listadoOrdenes
Feature:  Productor - Listado de órdenes

  Background:
    Given Se navega al portal Galicia Rural
    And Se hace click sobre el botón Logueate

  @TEST_ID_AG-275 @regression
  Scenario:  Productor - Listado de órdenes - Verifico pantalla Empty State
    When Se ingresa con usuario cristian.duque+04@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And Se hace click en el botón Ver órdenes
    Then Se visualiza icono en la pantalla Empty State
    And Se visualiza Todavía no tenés órdenes de compra en la pantalla Empty State
    And Se visualiza Cuando las tengas vas a ver tus órdenes de compra acá. en la pantalla Empty State
    #En Firtma no esta el boton volver, pero deberia estar
    And Se visualiza Botón Volver en la pantalla Empty State

  @TEST_ID_AG-276 @TEST_ID_AG-277 @TEST_ID_AG-278 @regression
  Scenario:  Productor - Listado de órdenes - Verifico listado de órdenes recientes
             Productor - Listado de órdenes - Verifico orden del listado de las órdenes
             Productor - Listado de órdenes - Verifico pantalla de Error State
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And Se hace click en el botón Ver órdenes
    And La conexion con el MS orders/producer se realiza correctamente
    #Cuando podamos pegarle al servicio que trae las ordenes lo idea seria hacer e2e
    Then Se muestra un listado de 5 órdenes
    And Se muestra Nombre del productor de las 5 órdenes
    And Se muestra Número de orden de las 5 órdenes
    And Se muestra Fecha y hora de generación de las 5 órdenes
    And Se muestra Descripción de las 5 órdenes
    And Se muestra un listado de órdenes ascendente