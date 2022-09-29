@financing @proveedor @DetalleOrdenGenerada
Feature: Validar el detalle de la Orden desde el listado de Ordenes

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    When El proveedor hace click en el boton Ver todas de la sección Home
    And El proveedor hace click en el boton Filtrar de la sección Ordenes

  @AG-1522 @TEST_ID_AG-1604 @regression @ordenSolaFirma @dalia
  Scenario: Proveedor - Validar Detalle Orden Pagada - Exportar
    Then Se selecciona el filtro Pagada
    And El proveedor hace click en el boton Aplicar Filtros de la sección modal Filtros de ordenes
    And El proveedor hace click en el boton Cerrar de la sección modal Filtros de ordenes
    And Se verifica que existan ordenes en status Pagada y se verifica el Detalle de la Orden Enviada
      | N° de orden:                         |
      | Cliente                              |
      | Descripción                          |
      | Información de contacto              |
      | Nombre y Apellido                    |
      | Correo electrónico                   |
      | Número de celular                    |
      | A sola firma                         |
      | Pagada                               |
      | Banco Galicia                        |
      | Cantidad de cuotas                   |
      | Vencimiento                          |
      | TNA del crédito                      |
      | Monto                                |
    #And Obtener datos de endpoint -GET- en bff con ruta orders/detail/

  And Se valida que el boton Descargar detalle exista