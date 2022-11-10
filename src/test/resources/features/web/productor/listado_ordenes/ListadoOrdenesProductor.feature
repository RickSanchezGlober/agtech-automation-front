@payments @productor @ordenes @listadoOrdenesProductor
Feature: Productor - Listado de Órdenes

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    And El productor hace click en el boton Ver todas las órdenes del home

  @TEST_SET_ID_AG-3486 @regression
  Scenario: Productor - Listado de Órdenes - Proveedor - Validar pantalla Listado de Ordenes
    When El productor visualiza el titulo Tus órdenes
    Then El productor viaualiza los elementos de la pantalla de órdenes
      | el buscador Buscar CUIT o nombre de cliente |
      | el boton Exportar XLS                       |
      | el boton Filtrar                            |
      | los botones >                               |
      | la columna Creación                         |
      | la columna Proveedor                        |
      | la columna Entidad                          |
      | la columna Medio de Pago                    |
      | la columna Monto                            |
      | la columna Estado de la orden               |
    And Comprobar datos de servicio api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | farmer.cuit:,status,payment_methods.financial_line_id                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor visualiza un máximo de 10 órdenes

  @TEST_SET_ID_AG-3476 @regression
  Scenario: Productor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón buscar
  Productor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón Enter
  Productor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con CUIT valido
  Productor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con Razón Social valida
  Productor - Listado de Órdenes - Buscador CUIT y Razón Social - Verifico búsqueda parcial
    And Consumir api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor hace una busqueda por CUIT existente
    #el buscador no muestra el cuit de del farmer en la UI
    Then El productor visualiza los resultados correctos
    And El productor hace una busqueda por nombre del cliente existente
    And El productor visualiza los resultados correctos
    And El productor hace una busqueda por CUIT parcial existente
    And El productor visualiza los resultados correctos
    And El productor hace una busqueda por CUIT inexistente
    And El productor visualiza la pantalla de órdenes vacias

  @TEST_SET_ID_AG-3477 @TEST_SET_ID_AG-3478 @TEST_SET_ID_AG-3479 @TEST_SET_ID_AG-3480
  @TEST_SET_ID_AG-3481 @TEST_SET_ID_AG-3482 @regression
  Scenario: Productor - Listado de Órdenes - Paginado - Validar paginado en pantalla Listado de Órdenes
  Productor - Listado de Órdenes - Paginado - Validar botón desplegable "1-10" del paginado
  Productor - Listado de Órdenes - Paginado - Validar botón "1-50" del paginado
  Productor - Listado de Órdenes - Paginado - Validar botón "1-100" del paginado
  Productor - Listado de Órdenes - Paginado - Validar deshabilitación botón ">" del paginado
  Productor - Listado de Órdenes - Paginado - Validar botón "<" del paginado
    And Consumir api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    Then El productor visualiza el botón < Deshabilitado
    And Si el productor tiene mas de 10 ordenes se visualiza el boton > Habilitado
    And Si el productor tiene mas de 10 ordenes se hace click en el boton >
    And El productor visualiza el resto de las ordenes
    And Si el productor tiene mas de 10 ordenes se hace click en el boton <
    And El productor visualiza las primeras 10 ordenes
    And El productor puede seleccionar el paginado
    And El productor selecciona el paginado 50
    And Comprobar datos de servicio api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 50                                                                                                                                                          |
      | where     | farmer.cuit:,status,payment_methods.financial_line_id                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor selecciona el paginado 100
    And Comprobar datos de servicio api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 100                                                                                                                                                         |
      | where     | farmer.cuit:,status,payment_methods.financial_line_id                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |