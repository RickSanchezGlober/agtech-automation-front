@payments @productor @ordenes @listadoOrdenesProductorExportar
Feature:  Home Productor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    When El proveedor hace click en el boton Ver todas del home
    And Se elimina el file Tusordenes_Nera.xlsx si existe

# Este caso solo funciona con el modo setHeadless(false), con lo que en el Job no funciona
# Correrlo local
# Saco el caso de exportar todas las ordenes pq demora mucho en Exportar
  @TEST_ID_AG-2398
  Scenario: Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar"
  Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar" con filtros aplicados
  Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar" con búsqueda previa
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes
    And Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El proveedor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And El proveedor hace click en el boton Exportar XLS de la pantalla ordenes
    Then Consumir api productor que filtra las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | farmer.cuit:30707633197,status                                                                                                                              |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And Se descarga el file Tusordenes_Nera.xlsx exitosamente