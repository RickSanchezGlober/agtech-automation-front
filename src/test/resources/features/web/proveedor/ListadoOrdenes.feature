@payments @proveedor @ordenes @listadoOrdenes
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    And El proveedor hace click en el boton Ver todas del home

  @TEST_SET_ID_AG-1667 @TEST_SET_ID_AG-1720 @regression
  Scenario: Proveedor - Listado de Órdenes - Proveedor - Validar pantalla Listado de Ordenes
    When Se visualiza el titulo Órdenes
    Then Se viaualizan los elementos de la pantalla de órdenes
      | el buscador Buscar CUIT o nombre de cliente |
      | el boton Exportar                           |
      | el boton Filtrar                            |
      | el boton Crear Orden                        |
      | la columna Creación                         |
      | la columna Cliente                          |
      | la columna Entidad                          |
      | la columna Medio de Pago                    |
      | la columna Monto total                      |
      | la columna Estado                           |
    #bug reportado pq solo se muestran 4 ordenes en Ver todas
    #agregar validaciones del front cuando se soluciones el bug
    And Verificar datos de servicio api que lista todas las ordenes bff con ruta orders y parámetros
      | fields    |
      | skip      |
      | type_sort |
      | sort      |