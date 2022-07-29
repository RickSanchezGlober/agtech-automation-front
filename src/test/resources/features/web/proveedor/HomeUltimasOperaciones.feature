@payments @proveedor @home
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro


  @TEST_SET_ID_AG-1667 @regression
  Scenario: Proveedor - Home - Últimas Operaciones Realizadas - Validar pantalla Últimas Operaciones Realizadas

  @TEST_SET_ID_AG-1851 @regression
  Scenario: Proveedor - Listado de Órdenes - CTA "Crear Orden" - Validar botón "Crear Orden"
    When El proveedor hace click en el boton Ver todas del home
    And El proveedor hace click en el boton Crear orden de la pantalla ordenes
    Then Se muestran los elementos de la pantalla Nueva Orden