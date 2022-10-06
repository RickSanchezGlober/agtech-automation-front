@financing @proveedor @DetalleOrdenGenerada @testing
Feature: Validar el detalle de la Orden desde el listado de Ordenes

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    When El proveedor hace click en el boton Ver todas de la sección Home
    And El proveedor hace click en el boton Filtrar de la sección Ordenes


  @AG-1522 @regression
  Scenario: Proveedor - Validar Detalle Orden Enviada - Status Rechazada
    Then Se selecciona el filtro Rechazada
    And El proveedor hace click en el boton Aplicar Filtros de la sección modal Filtros de ordenes
    And El proveedor hace click en el boton Cerrar de la sección modal Filtros de ordenes
    And Se verifica que existan ordenes en status Rechazada y se verifica el Detalle de la Orden Enviada
      | N° de orden:                         |
      | Cliente                              |
      | Descripción                          |
      | Información de contacto              |
      | Nombre y Apellido                    |
      | Correo electrónico                   |
      | Número de celular                    |
      | Rechazada                            |
      | Banco Galicia                        |
      | Cantidad de cuotas                   |
      | Vencimiento                          |
      | TNA del crédito                      |
      | Monto                                |
    And Obtener datos de endpoint -GET- en bff con ruta orders/detail/ y tipo detalle de la Orden

  @AG-1522 @regression
  Scenario: Proveedor - Validar Detalle Orden Enviada - Status Vencida
    Then Se selecciona el filtro Vencida
    And El proveedor hace click en el boton Aplicar Filtros de la sección modal Filtros de ordenes
    And El proveedor hace click en el boton Cerrar de la sección modal Filtros de ordenes
    And Se verifica que existan ordenes en status Vencida y se verifica el Detalle de la Orden Enviada
      | N° de orden:                         |
      | Cliente                              |
      | Descripción                          |
      | Información de contacto              |
      | Nombre y Apellido                    |
      | Correo electrónico                   |
      | Número de celular                    |
      | Vencida                              |
      | Banco Galicia                        |
      | Cantidad de cuotas                   |
      | Vencimiento                          |
      | TNA del crédito                      |
      | Monto                                |
    And Obtener datos de endpoint -GET- en bff con ruta orders/detail/ y tipo detalle de la Orden

  @AG-1522 @regression
  Scenario: Proveedor - Validar Detalle Orden Enviada - Status Pendiente
    Then Se selecciona el filtro Pendiente
    And El proveedor hace click en el boton Aplicar Filtros de la sección modal Filtros de ordenes
    And El proveedor hace click en el boton Cerrar de la sección modal Filtros de ordenes
    And Se verifica que existan ordenes en status Pendiente y se verifica el Detalle de la Orden Enviada
      | N° de orden:                         |
      | Cliente                              |
      | Descripción                          |
      | Información de contacto              |
      | Nombre y Apellido                    |
      | Correo electrónico                   |
      | Número de celular                    |
      | Pendiente                            |
      | Banco Galicia                        |
      | Cantidad de cuotas                   |
      | Vencimiento                          |
      | TNA del crédito                      |
      | Monto                                |
    And Obtener datos de endpoint -GET- en bff con ruta orders/detail/ y tipo detalle de la Orden