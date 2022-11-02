@payments @proveedor @ordenes @listadoOrdenesProveedor
Feature: Proveedor - Listado de Órdenes

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
    And El proveedor hace click en el boton Ver todas del home

  @TEST_SET_ID_AG-1667 @TEST_SET_ID_AG-1720 @regression
  Scenario: Proveedor - Listado de Órdenes - Proveedor - Validar pantalla Listado de Ordenes
    When Se visualiza el titulo Órdenes
    Then Se viaualizan los elementos de la pantalla de órdenes
      | el buscador Buscar CUIT o nombre de cliente |
      | el boton Exportar                           |
      | el boton Filtrar                            |
      | el boton Crear Orden                        |
      | los botones >                               |
      | la columna Creación                         |
      | la columna Cliente                          |
      | la columna Entidad                          |
      | la columna Medio de Pago                    |
      | la columna Monto                            |
      | la columna Estado                           |
    And Comprobar datos de servicio api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se visualizan un máximo de 10 órdenes

  @TEST_SET_ID_AG-1748 @regression
  Scenario: Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón buscar
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón Enter
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con CUIT valido
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con Razón Social valida
  oveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Verifico búsqueda parcial
    And Consumir api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se hace una busqueda por CUIT existente
    Then Se verifica que se muestren resultados correctos
    And Se hace una busqueda por nombre del cliente existente
    And Se verifica que se muestren resultados correctos
    And Se hace una busqueda por CUIT parcial existente
    And Se verifica que se muestren resultados correctos
    And Se hace una busqueda por CUIT inexistente
    And Se muestra la pantalla de órdenes vacias

  @TEST_SET_ID_AG-1839 @regression
  Scenario: Proveedor - Listado de Órdenes - Paginado - Validar paginado en pantalla Listado de Órdenes
  Proveedor - Listado de Órdenes - Paginado - Validar botón desplegable "1-10" del paginado
  Proveedor - Listado de Órdenes - Paginado - Validar botón "1-50" del paginado
  Proveedor - Listado de Órdenes - Paginado - Validar botón "1-100" del paginado
  Proveedor - Listado de Órdenes - Paginado - Validar deshabilitación botón ">" del paginado
  Proveedor - Listado de Órdenes - Paginado - Validar botón "<" del paginado
    And Consumir api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    Then Se visualiza el botón < Deshabilitado
    #Ahora esta apareciendo el boeton > habilitado
#    And Si el proveedor tiene mas de 10 ordenes se visualiza el boton > Habilitado
    And Si el proveedor tiene mas de 10 ordenes se hace click en el boton >
    And Se visualizan el resto de las ordenes
    And Si el proveedor tiene mas de 10 ordenes se hace click en el boton <
    And Se visualizan las primeras 10 ordenes
    And Se puede seleccionar el paginado
    And Se selecciona el paginado 50
    And Comprobar datos de servicio api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 50                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se selecciona el paginado 100
    And Comprobar datos de servicio api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 100                                                                                                                                                         |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |