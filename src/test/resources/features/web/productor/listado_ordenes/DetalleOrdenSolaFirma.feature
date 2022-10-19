@financing @productor @dalia
Feature:  Sola Firma Productor: Simulación del Crédito c/Mejoras

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    When El productor hace click en el boton Ver todas del home

   @regression @AG-2992
  Scenario: Productor - Verificar detalle de la orden - A sola firma
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    And El productor selecciona el filtro Pendiente
    And El productor selecciona el filtro A sola firma
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El productor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And Se verifica que existan ordenes en status Pendiente y se verifica que el Detalle de la Orden esté correcta
     | Orden:                     |
     | Estado:                    |
     | Proveedor                  |
     | Medio de pago              |
     | Financiación A sola firma  |
     | Entidad                    |
     | Banco Galicia              |
     | Monto                      |
     | TNA                        |
     | Vencimiento del crédito    |
     | Detalles del crédito       |
     | Monto a financiar          |
     | Interés                    |
     | IVA sobre interés          |
     | Sellado                    |
     | Total a pagar *            |
     | Costo sobre el capital     |
    And Obtener datos de endpoint -GET PRODUCTOR- en bff con ruta farmer/orders/detail/