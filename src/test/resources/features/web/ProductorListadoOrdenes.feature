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
