@payments @proveedor @ordenes @listadoOrdenesProveedorExportar
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
    When El proveedor hace click en el boton Ver todas del home
    And Se elimina el archivo Tusordenes_Nera.xlsx si existe
    And El proveedor hace click en el boton Exportar de la pantalla ordenes

# Este caso solo funciona con el modo setHeadless(false), con lo que en el Job no funciona
  @TEST_ID_AG-1822
  Scenario: Proveedor - Listado de Órdenes - CTA "Exportar" - Validar botón "Exportar"
  Proveedor - Listado de Órdenes - CTA "Exportar" - Validar botón "Exportar" con filtros aplicados
    Then Se descarga Tusordenes_Nera.xlsx exitosamente
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes
    And Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El proveedor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And Se elimina el archivo Tusordenes_Nera.xlsx si existe
    And El proveedor hace click en el boton Exportar de la pantalla ordenes
    And Se descarga Tusordenes_Nera.xlsx exitosamente