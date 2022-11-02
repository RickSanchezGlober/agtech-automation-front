@productor
Feature:  Sola Firma Productor: Simulación del Crédito c/Mejoras

  Background:
    Given Se navega al portal Nera productor
    And Se ingresa con usuario aut.prod@test.com y password Test123+
    When El productor hace click en el boton Ver todas del home

  @regression @financing @AG-2992
  Scenario: Productor - Verificar detalle de la orden - A sola firma
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    And El productor selecciona el filtro Pendiente
    And El productor selecciona el filtro A sola firma
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El productor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And Se verifica que existan ordenes en status Pendiente y se verifica que el Detalle de la Orden esté correcta
      | Orden:                  |
      | Estado:                 |
      | Proveedor               |
      | Medio de pago           |
      | A sola firma            |
      | Entidad                 |
      | Banco Galicia           |
      | Monto                   |
      | TNA                     |
      | Vencimiento del crédito |
      | Detalles del crédito    |
      | Monto a financiar       |
      | Interés                 |
      | IVA sobre interés       |
      | Sellado                 |
      | Total a pagar al Banco* |
      | Costo sobre el capital  |
    And Obtener datos de endpoint -GET PRODUCTOR- en bff con ruta farmer/orders/detail/
    And Botón Tooltip se visualiza Habilitado en detalle de orden
    And Botón Continuar se visualiza Habilitado en detalle de orden
    And El productor hace click sobre botón: Continuar
#    Se espera por bug AG-3546 para validar lo siguiente:
#    And Botón Confirmar Pago se visualiza Deshabilitado en detalle de orden
#    And El productor hace click sobre botón: Volver-Detalle de la Orden
#    And Botón Continuar se visualiza Habilitado en detalle de orden
#    And El productor hace click sobre botón: Volver-Ordenes
#    And Botón Filtrar se visualiza Habilitado en detalle de orden

  @regression @payments @HU_AG-2683 @HU_AG-2684
  Scenario: Productor - Detalle Orden PAGADA Crédito Sola Firma
    And El productor hace click en el boton Filtrar de la pantalla ordenes
    And El productor selecciona el filtro Pagada
    And El productor selecciona el filtro A sola firma
    And El productor hace click en el boton Aplicar filtros de la pantalla ordenes
    And El productor hace click en el boton Cerrar Filtros de la pantalla ordenes
    And Se hace click sobre el detalle de una orden
#    Aca le pegamos al servicio que filtra y al q trae el detalle de la orden
#    Verificamos que el detalle de la orden muestra lo que trae el servicio farmer/orders/detail/idOrder
    Then Verificar datos de api productor que lista todas las ordenes bff con ruta farmer/orders/filter y parámetros
      | type_sort | DESC                                                                                                                                                        |
      | sort      | order_date                                                                                                                                                  |
      | skip      | 0                                                                                                                                                           |
      | count     | 10                                                                                                                                                          |
      | where     | farmer.cuit:,status:Pagada,payment_methods.financial_line_id:1                                                                                              |
      | like      | farmer.name,farmer.cuit                                                                                                                                     |
      | fields    | provider,order_date,id_order,amount,farmer,payment_methods.financial_entity,payment_methods.financial_line_id,status,payment_methods.conditions.loan_amount |
    And El productor visualiza el boton Mostrar más detalle Habilitado
    And El productor hace click en el boton Mostrar más detalle de la pantalla ordenes
    And El productor hace click en la seccion Crédito de la pantalla Mas Detalle
    And El productor visualiza más detalles de la orden pagada