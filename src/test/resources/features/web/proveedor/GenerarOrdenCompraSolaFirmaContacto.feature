@payments @proveedor @generarOrdenCompra
Feature: Generar Orden de Compra. Información del Contacto

  Background:
    Given Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And El proveedor hace click en el botón Crear Orden
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Crédito a sola firma
    And El proveedor ingresa monto mayor a $1.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en subsidio de tasa opcion Sub 5% Vto Septiembre 2022
    And El proveedor hace click en el botón Simular Crédito
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