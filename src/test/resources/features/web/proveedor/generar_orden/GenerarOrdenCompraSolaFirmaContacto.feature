@payments @proveedor @generarOrdenCompraSolaFirmaContacto @yailin
Feature: Generar Orden de Compra. Información del Contacto. Confimación.

  Background:
    Given Se navega al portal Nera proveedor
    And Usuario logueado en el portal Nera
    When El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Linea Base Vto Abril 2023
    And El proveedor hace click en el botón Simular Crédito
    And Recuperar datos de servicios api bff con ruta simulation con body bff_simulation_SF.txt
      | provider.name                 |
      | farmer.cuit                   |
      | amount                        |
      | financing_type                |
      | installment_cuantity          |
      | farmer.tna                    |
      | cft                           |
      | installments.interest_nominal |
      | installments.vat_interest     |
      | total_amount                  |
    And El proveedor hace click en el botón Confirmar medio de pago

  @TEST_ID_AG-1390 @TEST_ID_AG-1391 @TEST_ID_AG-1393 @TEST_ID_AG-1394 @TEST_ID_AG-1395
  @TEST_ID_AG-1396 @regression
  Scenario: Proveedor - Generar Orden de Compra - Datos de contacto - Validar pantalla Información de contacto
  Proveedor - Generar Orden de Compra - Datos de contacto - Validar habilitación del botón Continuar
  Proveedor - Generar Orden de Compra - Datos de contacto - Validar largo del campo Nombre y Apellido
  Proveedor - Generar Orden de Compra - Datos de contacto - Validar máscara del Correo electrónico
  Proveedor - Generar Orden de Compra - Datos de contacto - Validar largo del campo Cód de área
  Proveedor - Generar Orden de Compra - Datos de contacto - Validar largo del campo Número de celular
    Then Se muestra la pantalla para ingresar los datos del contacto Datos de contacto de quien recibirá y aprobará la orden de pago.
    And Se muestra la pantalla para ingresar los datos del contacto Nombre y Apellido
    And Se muestra la pantalla para ingresar los datos del contacto Correo electrónico
    And Se muestra la pantalla para ingresar los datos del contacto Cód de área
    And Se muestra la pantalla para ingresar los datos del contacto Número de celular
    And El proveedor visualiza el botón Continuar Deshabilitado
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor visualiza el botón Continuar Habilitado
#    Comprobando la long de los campos
    And El proveedor no puede ingresar más de 30 caracteres en el campo Nombre y Apellido
    And El proveedor no puede ingresar más de 4 caracteres en el campo Cód de área
    And El proveedor no puede ingresar más de 8 caracteres en el campo Número de celular
    And Se llena el campo Correo electrónico con valor Inválido
    And El proveedor visualiza el botón Continuar Deshabilitado

  @TEST_ID_AG-1499 @TEST_ID_AG-1500 @regression
  Scenario: Proveedor - Generar Orden de Compra - Resumen - Validar pantalla de Confirmación
  Proveedor - Generar Orden de Compra - Resumen - Verificar botón volver
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor hace click en el botón Continuar
    Then Se visualiza el título Revisá que la solicitud esté completa
    And Se visualiza el título Detalles de la orden
    And Se visualiza el título Información de contacto
    And Se visualiza el título Medio de pago
    And Se muestra la pantalla confirmacion datos del contacto
      | provider.name                 |
      | farmer.cuit                   |
      | amount                        |
      | financing_type                |
      | installment_cuantity          |
      | farmer.tna                    |
      | cft                           |
      | installments.interest_nominal |
#      | installments.vat_interest     |ya no aparece
      | total_amount                  |
    And El proveedor visualiza el botón Enviar orden Habilitado
    And El proveedor hace click sobre botón Volver
    And El proveedor no visualiza el botón Enviar orden

  @TEST_SET_ID_AG-2153 @regression
  Scenario: Proveedor - Generar Orden de Compra - Formato del mail incorrecto - Validar error formato del mail incorrecto
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Inválido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    Then Se visualiza mensaje de error

  