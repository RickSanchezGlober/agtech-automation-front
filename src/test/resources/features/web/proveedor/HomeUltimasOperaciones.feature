@payments @proveedor @home @homeUltimasOperaciones
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro


  @TEST_SET_ID_AG-1667 @regression
  Scenario: Proveedor - Home - Últimas Operaciones Realizadas - Validar pantalla Últimas Operaciones Realizadas
  Proveedor - Home - Últimas Operaciones Realizadas - Validar orden Últimas Operaciones Realizadas
  Proveedor - Home - Últimas Operaciones Realizadas - Validar máximas Operaciones mostradas
  Proveedor - Home - Últimas Operaciones Realizadas - Validar botón "Ver todas"
    When Validar datos de servicios api bff con ruta orders y parametros
      | fields    | id_order,create_date,producer,payment_methods.financial_entity,payment_methods.financial_line,payment_methods.expiry_date,status,payment_methods.conditions.loan_amount |
      | skip      | 0                                                                                                                                                                       |
      | count     | 4                                                                                                                                                                       |
      | type_sort | DESC                                                                                                                                                                    |
      | sort      | create_date                                                                                                                                                             |
    Then Se visualizan no más de 4 órdenes
    And Se viaualizan los elementos
      | título Últimas realizadas |
      | el boton Ver todas        |
    And El proveedor hace click en el boton Ver todas del home
    And Se visualiza el titulo Órdenes


  @TEST_SET_ID_AG-1851 @regression
  Scenario: Proveedor - Listado de Órdenes - CTA "Crear Orden" - Validar botón "Crear Orden"
    When El proveedor hace click en el boton Ver todas del home
    And El proveedor hace click en el boton Crear orden de la pantalla ordenes
    Then Se muestran los elementos de la pantalla Nueva Orden

  @TEST_SET_ID_AG-1674 @regression
  Scenario: Proveedor - Home - Operaciones Próximas a Vencer - Validar pantalla Operaciones próximas a vencer
  Proveedor - Home - Operaciones Próximas a Vencer - Validar botón ">"
    Then Se visualiza el titulo Órdenes próximas a vencer
    And Se visualiza el icono contador de Ordenes próximas a vencer
    And Se visualiza el icono >
    And Validar datos de servicio api bff con ruta orders/counter/nexttowin
    And El proveedor hace click en el boton > de Ordenes próximas a vencer del home
    And El botón > funciona correctamente