@financing @proveedor @DetalleOrdenGenerada
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    When El proveedor hace click en el boton Ver todas de la sección Home
    And El proveedor hace click en el boton Filtrar de la sección Ordenes

  @AG-1522 @TEST_ID_AG-1604 @regression @ordenSolaFirma
  Scenario: Proveedor - Validar Detalle Orden Enviada - Todos los Status - Sola Firma
    Then Se selecciona el filtro Pendiente
    And Se selecciona el filtro A sola Firma
    And El proveedor hace click en el boton Aplicar Filtros de la sección modal Filtros de ordenes
    And El proveedor hace click en el boton Cerrar de la sección modal Filtros de ordenes
    And Se verifica que existan ordenes en status Pendiente y se verifica el Detalle de la Orden Enviada
      | N° de orden:                         |
      | Cliente                              |
      | Descripción                          |
      | Información de contacto              |
#      | Nombre y Apellido                    |
      | Correo electrónico                   |
#      | Número de celular                    |
      | A sola firma                         |
      | Pendiente                            |
      | Banco Galicia                        |
      | El productor debe confirmar la orden |
      | Cantidad de cuotas                   |
      | Vencimiento                          |
      | TNA del crédito                      |
      | Monto                                |
    And Obtener datos de endpoint -GET- en bff con ruta orders/detail/