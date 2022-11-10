@payments @proveedor @generarOrdenSolaFirmaLinea12M
Feature: Generar Orden de Compra. Identificacion del cliente. Detalles de la orden. Medios de pago.

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
#    When Se hace click en el botón Órdenes
    And El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30568143120 en el campo Ingresá el CUIT
    And Recuperar datos de servicios api bff con ruta customer-validation/ y guardar variables abajo
      | cuit_teradata |
      | name          |
      | ok_bank       |
      | is_mipyme     |
    And El proveedor hace click en el botón Buscar
    And El proveedor puede ver Datos del Productor Asociado
      | name          |
      | cuit_teradata |
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito

  @TEST_ID_AG-2888 @regression
  Scenario: Proveedor - Generar Orden - Simular Crédito A sola firma (Línea 12 meses) - Validar pantalla simular crédito mensual
    Then El proveedor selecciona en subsidio de tasa opcion Mensual Con Subsidio
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation_SF_Linea12MMensual.txt
      | Monto                           | amount                      |
      | TNA del crédito                 | farmer.tna                  |
      | CFT                             | cft                         |
      | Periodicidad de pago de interés | cotization_type_description |
      | No. cuotas para pago interés    | installment_cuantity        |
      | Fecha de pago del capital       | due_date                    |
    And El proveedor visualiza el boton Confirmar medio de pago Habilitado
    And Validar Nombre del Productor
      | name |
    And Se borra el campo Ingresá el monto del crédito
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor no visualiza el boton Confirmar medio de pago

  @TEST_ID_AG-2889 @regression
  Scenario: Proveedor - Generar Orden - Simular Crédito A sola firma (Línea 12 meses) - Validar pantalla simular crédito trimestral
    Then El proveedor selecciona en subsidio de tasa opcion Trimestral Con Subsidio
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation_SF_Linea12MTrimestral.txt
      | Monto                           | amount                      |
      | TNA del crédito                 | farmer.tna                  |
      | CFT                             | cft                         |
      | Periodicidad de pago de interés | cotization_type_description |
      | No. cuotas para pago interés    | installment_cuantity        |
      | Fecha de pago del capital       | due_date                    |
    And El proveedor visualiza el boton Confirmar medio de pago Habilitado
    And Validar Nombre del Productor
      | name |
    And Se borra el campo Ingresá el monto del crédito
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor no visualiza el boton Confirmar medio de pago

  @TEST_ID_AG-2890 @regression
  Scenario: Proveedor - Generar Orden - Simular Crédito A sola firma (Línea 12 meses) - Validar pantalla simular crédito semestral
    Then El proveedor selecciona en subsidio de tasa opcion Semestral Con Subsidio
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation_SF_Linea12MSemestral.txt
      | Monto                           | amount                      |
      | TNA del crédito                 | farmer.tna                  |
      | CFT                             | cft                         |
      | Periodicidad de pago de interés | cotization_type_description |
      | No. cuotas para pago interés    | installment_cuantity        |
      | Fecha de pago del capital       | due_date                    |
      | Fecha de pago del capital       | due_date                    |
    And El proveedor visualiza el boton Confirmar medio de pago Habilitado
    And Validar Nombre del Productor
      | name |
    And Se borra el campo Ingresá el monto del crédito
    And El proveedor visualiza el boton Simular Crédito Deshabilitado
    And El proveedor no visualiza el boton Confirmar medio de pago

  @TEST_ID_AG-2891 @3582 @regression
  Scenario: Proveedor - Generar Orden - Resumen (Línea 12 meses) - Validar pantalla Resumen Linea 12 meses
    And El proveedor selecciona en subsidio de tasa opcion Semestral Con Subsidio
    And El proveedor hace click en el botón Simular Crédito
    And Recuperar datos de servicios api bff con ruta simulation con body bff_simulation_SF_Linea12MSemestral.txt
      | provider.name                      |
      | farmer.cuit                        |
      | amount                             |
      | financing_type                     |
      | farmer.tna                         |
      | cft                                |
      | installments[0].total_vat_interest |
      | installments[1].total_vat_interest |
      | installments[0].interest_nominal   |
      | installments[1].interest_nominal   |
      | installments[0].amount             |
      | installments[1].amount             |
      | total_amount                       |
      | installments[0].stamp_taxes        |
      | installments[1].stamp_taxes        |
      | installments[0].due_date           |
      | installments[1].due_date           |
      | installments[1].amortization       |
    And El proveedor hace click en el botón Confirmar medio de pago
    Then Se muestra la pantalla para ingresar los datos del contacto Datos de contacto de quien recibirá y aprobará la orden de pago.
    And Se muestra la pantalla para ingresar los datos del contacto Nombre y Apellido
    And Se muestra la pantalla para ingresar los datos del contacto Correo electrónico
    And Se muestra la pantalla para ingresar los datos del contacto Cód de área
    And Se muestra la pantalla para ingresar los datos del contacto Número de celular
    And El proveedor visualiza el botón Continuar Deshabilitado
    #    Comprobando la long de los campos
    And El proveedor no puede ingresar más de 30 caracteres en el campo Nombre y Apellido
    And El proveedor no puede ingresar más de 4 caracteres en el campo Cód de área
    And El proveedor no puede ingresar más de 8 caracteres en el campo Número de celular
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor visualiza el botón Continuar Habilitado
    And El proveedor hace click en el botón Continuar
    And Se visualiza el título Revisá que la solicitud esté completa
    And Se visualiza el título Detalles de la orden
    And Se visualiza el título Información de contacto
    And Se visualiza el título Medio de pago
    And El proveedor hace click en el botón Detalle del pago
    And Se muestra la pantalla confirmacion datos del contacto
      | provider.name                      |
      | farmer.cuit                        |
      | amount                             |
      | financing_type                     |
      | farmer.tna                         |
      | cft                                |
      | installments[0].total_vat_interest |
      | installments[1].total_vat_interest |
      | installments[0].interest_nominal   |
      | installments[1].interest_nominal   |
      | installments[0].amount             |
      | installments[1].amount             |
      | total_amount                       |
      | installments[0].stamp_taxes        |
      | installments[1].stamp_taxes        |
      | installments[0].due_date           |
      | installments[1].due_date           |
      | installments[1].amortization       |
    And El proveedor visualiza el botón Enviar orden Habilitado
    And El proveedor hace click en el botón Enviar orden
    And Se visualiza la pantalla de Orden generada y enviada exitosamente
    And El proveedor hace click en el botón Ir a órdenes
    And El proveedor vuelve a la Home