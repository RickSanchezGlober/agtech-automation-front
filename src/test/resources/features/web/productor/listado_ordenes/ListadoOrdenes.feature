@payments @productor @listadoOrdenes @yailin
Feature:  Productor - Listado de órdenes

  Background:
    Given Se navega al portal New Agro productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+

  @TEST_SET_ID_AG-2355 @regression
  Scenario:  Productor - Listado de Órdenes Productor - Validar pantalla Listado de Ordenes
  Productor - Listado de Órdenes Productor - Validar orden del Listado de Ordenes
    When Se hace click en el botón Órdenes
    And La conexion con el MS orders/producer se realiza correctamente
    Then Verificar datos de servicio api que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | farmer.cuit:,status                                                                                                                                         |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se muestra un listado de órdenes descendente
    And El productor visualiza los elementos de la HomePage
