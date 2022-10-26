@payments @proveedor @ordenes @listadoOrdenesProveedorExportar @yailin
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
    When El proveedor hace click en el boton Ver todas del home
    And Se elimina el archivo Tusordenes_Nera.xlsx si existe

# Este caso solo funciona con el modo setHeadless(false), con lo que en el Job no funciona
# Correrlo local
# Saco el caso de exportar todas las ordenes pq demora mucho en Exportar
  @TEST_ID_AG-1822
  Scenario: Proveedor - Listado de Órdenes - CTA "Exportar" - Validar botón "Exportar"
  Proveedor - Listado de Órdenes - CTA "Exportar" - Validar botón "Exportar" con filtros aplicados
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes
    And Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El proveedor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And El proveedor hace click en el boton Exportar de la pantalla ordenes
    Then Consumir api que filtra las ordenes bff con ruta orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | provider._id:2,status:Pendiente,payment_methods.financial_line_id                                                                                           |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    Then Se descarga Tusordenes_Nera.xlsx exitosamente