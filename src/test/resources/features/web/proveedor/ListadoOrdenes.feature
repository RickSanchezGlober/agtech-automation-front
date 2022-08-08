@payments @proveedor @ordenes @listadoOrdenesProveedor
Feature:  Home Proveedor - Últimas Operaciones Realizadas

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
      | skip   | 0                                                                                                                                                                       |
      | count  | 8                                                                                                                                                                       |
      | where  | status                                                                                                                                                                  |
      | like   | producer.name,producer.cuit                                                                                                                                             |
      | fields | id_order,create_date,producer,payment_methods.financial_entity,payment_methods.financial_line,payment_methods.expiry_date,status,payment_methods.conditions.loan_amount |
    And Se visualizan un máximo de 8 órdenes