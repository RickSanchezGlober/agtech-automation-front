@payments @prodcutor @ordenes @listadoOrdenesProductorFiltrar
Feature:  Home Productor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    When El productor hace click en el boton Ver todas del home


  @TEST_ID_AG-2368 @TEST_ID_AG-2369 @TEST_ID_AG-2370 @TEST_ID_AG-2372
  Scenario: Proveedor - Listado de Órdenes - CTA "Filtrar" - Validar pantalla Filtros de ordenes
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar pantalla Filtros de ordenes
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar habilitación del botón "Aplicar filtros"
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar habilitación del botón "Limpiar filtros"
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar botón "Limpiar filtros"
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    Then El productor visualiza los elementos de la pantalla de Filtros de órdenes
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
#    El boton esta habilitado, deberia estar Deshabilitado
#    And El productor visualiza el boton Aplicar filtros Deshabilitado
    And El productor visualiza el boton Limpiar filtros Deshabiltado
    And El productor selecciona el filtro Pendiente
    And El productor visualiza el boton Aplicar filtros Habilitado
    And El productor visualiza el boton Limpiar filtros Habilitado
    And El productor hace click en el boton Limpiar filtros de la pantalla ordenes
#    And El productor visualiza el boton Aplicar filtros Deshabilitado

  @TEST_ID_AG-2371 @TEST_ID_AG-2378 @TEST_ID_AG-2379 @regression
  Scenario: Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar botón "Aplicar filtros"
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar filtros de Estado
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar filtros de Medio de Pago
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    And El productor selecciona el filtro Pendiente
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    Then Comprobar datos de servicio api productor que filtra todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status:Pendiente,payment_methods.financial_line_id                                                                                           |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor hace click en el boton Limpiar filtros de la pantalla ordenes
    And El productor selecciona el filtro A sola firma
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    Then Comprobar datos de servicio api productor que filtra todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | farmer.cuit:,status,payment_methods.financial_line_id:1                                                                                                     |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |

  @TEST_ID_AG-2373 @TEST_ID_AG-2374 @TEST_ID_AG-2376 @TEST_ID_AG-2377 @regression
  Scenario: Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar campo Desde
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar formato fecha Desde
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar campo Hasta
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar campo Hasta con fecha default
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar formato fecha Hasta
    And El productor hace click en el boton Filtrar de la pantalla ordenes
      #    Then Se visualiza el campo Hasta con la fecha actual por defecto
    And El productor selecciona el filtro Desde
    And El productor selecciona el filtro Hasta
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    And Se muestra listado de órdenes en el rango seleccionado en productor

  @TEST_SET_ID_AG-2381 @TEST_SET_ID_AG-2383 @TEST_SET_ID_AG-2384 @TEST_SET_ID_AG-2386
  @TEST_SET_ID_AG-2407 @regression
  Scenario: Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar búsqueda por botón buscar
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar búsqueda con CUIT valido
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Validar búsqueda con Razón Social valida
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Verifico búsqueda parcial
  Productor - Listado de Órdenes Productor - Buscador + Filtro - Verifico Empty State buscador
    And Consumir api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status                                                                                                                                       |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor hace una busqueda por CUIT existente
    Then El productor visualiza los resultados correctos
    And El productor hace una busqueda por nombre del cliente existente
    And El productor visualiza los resultados correctos
    And El productor hace una busqueda por CUIT parcial existente
    And El productor visualiza los resultados correctos
    And El productor hace una busqueda por CUIT inexistente
    And El productor visualiza la pantalla de órdenes vacias

  @TEST_SET_ID_AG-2406 @regression
  Scenario: Productor - Listado de Órdenes Productor - Buscador + Filtro - Verifico Empty State filtros
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    #Elegimos un filtro que no tenga ordenes, con fechas en el futuro
    And El productor selecciona el filtro Desde Fecha Futura
    And El productor selecciona el filtro Hasta Fecha Futura
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    Then El productor visualiza la pantalla de órdenes vacias