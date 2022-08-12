@payments @proveedor @ordenes @listadoOrdenesProveedor
Feature: Proveedor - Listado de Órdenes

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    And El proveedor hace click en el boton Ver todas del home

  @TEST_SET_ID_AG-1667 @TEST_SET_ID_AG-1720 @regression
  Scenario: Proveedor - Listado de Órdenes - Proveedor - Validar pantalla Listado de Ordenes
    When Se visualiza el titulo Órdenes
    #Sacaron el boton exportar
    Then Se viaualizan los elementos de la pantalla de órdenes
      | el buscador Buscar CUIT o nombre de cliente |
#      | el boton Exportar                           |
      | el boton Filtrar                            |
      | el boton Crear Orden                        |
      | los botones >                               |
      | la columna Creación                         |
      | la columna Cliente                          |
      | la columna Entidad                          |
      | la columna Medio de Pago                    |
      | la columna Monto total                      |
      | la columna Estado                           |
    And Comprobar datos de servicio api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | skip   | 0                                                                                                                                                     |
      | count  | 10                                                                                                                                                    |
      | where  | status                                                                                                                                                |
      | like   | producer.name,producer.cuit                                                                                                                           |
      | fields | provider,create_date,amount,producer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se visualizan un máximo de 10 órdenes

  @TEST_SET_ID_AG-1748 @regression
  Scenario: Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón buscar
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda por botón Enter
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con CUIT valido
  Proveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Validar búsqueda con Razón Social valida
  oveedor - Listado de Órdenes - Buscador CUIT y Razón Social - Verifico búsqueda parcial
    And Consumir api que lista todas las ordenes bff con ruta orders/filter y parámetros
      | skip   | 0                                                                                                                                                     |
      | count  | 10                                                                                                                                                    |
      | where  | status                                                                                                                                                |
      | like   | producer.name,producer.cuit                                                                                                                           |
      | fields | provider,create_date,amount,producer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se hace una busqueda por CUIT existente
    Then Se verifica que se muestren resultados correctos
    And Se hace una busqueda por nombre del cliente existente
    And Se verifica que se muestren resultados correctos
    And Se hace una busqueda por CUIT parcial existente