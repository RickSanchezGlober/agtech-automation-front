@financing @proveedor @DetalleOrdenGenerada
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal New Agro proveedor
    And Usuario logueado en el portal New Agro
    When El proveedor hace click en el boton Ver todas del home
    And El proveedor hace click en el boton Filtrar de la pantalla ordenes

  @AG-1522 @TEST_ID_AG-1604 @regression @ordenSolaFirma
  Scenario: Proveedor - Validar pantalla Detalle Orden Enviada - Sola Firma
    And Se selecciona el filtro Pendiente
    And Se selecciona el filtro A sola Firma
    And El proveedor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El proveedor hace click en el boton Cerrar del modal Filtros de ordenes
    And Se verifica que existan ordenes en status Pendiente y se hace click en listado para validar el detalle
      | el enlace con forma cruz para cerrar                                  |
      | el texto N° de Orden con su valor                                     |
      | el Nombre del cliente con el valor del cuit asociado por el proveedor |
      | la descripción con su valor por debajo                                |
      | el subtitulo Información de contacto                                  |
      | Nombre y Apellido                                                     |
      | el Correo electrónico            |
      | Número de celular            |
      | el subtitulo Sola Firma            |
      | el estado de la orden            |
      | el logo de Banco Galicia            |
      | descripción del estado de la orden            |
      | la cantidad de cuotas            |
      | el vencimiento            |
      | el TNA de crédito            |
      | el monto de la orden            |