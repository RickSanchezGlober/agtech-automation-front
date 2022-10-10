@payments @productor @ordenes @listadoOrdenesProductorExportar
Feature:  Home Proveedor - Últimas Operaciones Realizadas

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    When El proveedor hace click en el boton Ver todas del home
    And Se elimina el file Tusordenes_Nera.xlsx si existe
    And El proveedor hace click en el boton Exportar de la pantalla ordenes

# Este caso solo funciona con el modo setHeadless(false), con lo que en el Job no funciona
# Correrlo local
  @TEST_ID_AG-2398
  Scenario: Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar"
  Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar" con filtros aplicados
  Productor - Listado de Órdenes Productor - Descarga Excel - Validar botón "Exportar" con búsqueda previa
    Then Se descarga el file Tusordenes_Nera.xlsx exitosamente
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes
    And Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El proveedor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And Se elimina el archivo Tusordenes_Nera.xlsx si existe
    And El proveedor hace click en el boton Exportar de la pantalla ordenes
    #Agregar vrificacion de la cantidad de ordenes, debe exportar todas las ordenes Pendientes
    And Se descarga Tusordenes_Nera.xlsx exitosamente