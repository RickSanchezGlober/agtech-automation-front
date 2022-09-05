@payments @proveedor @ordenes @listadoOrdenesProveedorFiltrar
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    When El proveedor hace click en el boton Ver todas del home
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes

  @TEST_ID_AG-1800 @TEST_ID_AG-1801 @TEST_ID_AG-1802 @TEST_ID_AG-1804
  Scenario: Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar pantalla Filtros de ordenes
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar habilitación del botón "Aplicar filtros"
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar habilitación del botón "Limpiar filtros"
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar botón "Limpiar filtros"
    Then Se viaualizan los elementos de la pantalla de Filtros de órdenes
    #Faltan algunos elementos que faltan, bug reportado
      | el titulo Filtros de órdenes            |
      | el titulo Fecha de creación de la orden |
      | los campos Desde, Hasta                 |
      | el titulo Estado                        |
      | el check Pendiente                      |
      | el check Pagada                         |
      | el check Rechazada                      |
      | el check Vencida                        |
      | el titulo Medio de pago                 |
      | el check Cesión de forward              |
      | el check A sola firma                   |
      | el botón Aplicar filtros                |
    #El boton Aplicar filtros debe esta Deshabilitado, bug reportado
#    And Se visualiza el boton Aplicar filtros Deshabilitado
    #El boton Limpiar filtros no esta
#    And Se visualiza el boton Limpiar filtros Deshabiltado
    And Se selecciona el filtro Pendiente
    And Se visualiza el boton Aplicar filtros Habilitado
    #El boton Limpiar filtros no esta
#    And Se visualiza el boton Limpiar filtros Habilitado
#    And El proveedor hace click en el boton Limpiar filtros de la pantalla ordenes
#    And Se visualiza el boton Aplicar filtros Deshabilitado

  #Falata agregar las demas combinaciones, agregar cuando este el boton limpiar filtros
  @TEST_ID_AG-1803 @TEST_ID_AG-1808 @TEST_ID_AG-1809 @regression
  Scenario: Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar botón "Aplicar filtros"
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar filtros de Estado
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar filtros de Medio de Pago
    And Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    Then Comprobar datos de servicio api que filtra todas las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status:Pendiente,payment_methods.financial_line_id                                                                                           |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |

  #Los campos de fecha no estan, bug reportado
  @TEST_ID_AG-1805 @TEST_ID_AG-1806 @TEST_ID_AG-1807 @TEST_ID_AG-1810 @TEST_ID_AG-1811
  Scenario: Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar campo Desde
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar campo Hasta
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar campo Hasta con fecha default
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar formato fecha Desde
  Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar formato fecha Hasta
#    Then Se visualiza el campo Hasta con la fecha actual por defecto
    And Se selecciona el filtro Desde
    And Se selecciona el filtro Hasta
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And  Se muestra listado de órdenes en el rango seleccionado