@payments @proveedor
Feature: Generar Orden de Compra

  Background:
    Given Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    And  Se navega al portal Galicia Rural proveedor
    And Se hace click sobre el botón Logueate
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    And El proveedor hace click en el botón Crear Orden

  @TEST_ID_AG-529 @TEST_ID_AG-530 @regression
  Scenario: Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar pantalla nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar ingreso de CUIT nueva orden de compra
  Proveedor - Generar Orden de Compra - Identificación de Cliente - Validar busqueda de productor asociado al CUIT valido
    Then El proveedor visualiza el boton Buscar Deshabilitado
    And El proveedor visualiza Ingresá el CUIT en la pantalla Identificación de cliente
    And El proveedor visualiza Escribí 11 números en la pantalla Identificación de cliente
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And Se lee el cuit en formato correcto
    And El proveedor visualiza el boton Buscar Habilitado
    And El proveedor hace click en el botón Buscar
    And Recuperar datos de servicios api con ruta customer-validation/ y guardar variables abajo
    #Comentado hasta que se muestre el nombre y demas datos del productor
#      | Nombre            | name         |
#      | Sin Deuda         | clean_loan   |
#      | Sin Deuda forward | forward_loan |
    And El proveedor visualiza Productor Asociado en la pantalla Identificación de cliente

  @TEST_ID_AG-533 @TEST_ID_AG-534 @TEST_ID_AG-535 @TEST_ID_AG-536 @regression
  Scenario: Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar pantalla de Descripcion de la orden
            Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar largo del campo alfanumerico descripcion
            Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar habilitacion del boton Continuar
            Proveedor - Generar Orden de Compra - Descripción de la Orden - Validar deshabilitacion del boton Continuar
    And El proveedor ingresa 30597962793 en el campo Ingresá el CUIT
    And El proveedor hace click en el botón Buscar
    And El proveedor hace click en el botón del Productor encontrado
    Then El proveedor visualiza Describe el detalle de la orden de pago para el productor en la pantalla detalle de la orden
    And El proveedor visualiza Descripción en la pantalla detalle de la orden
    And El proveedor visualiza Hasta 40 caracteres en la pantalla detalle de la orden
    And El proveedor visualiza el boton Continuar Deshabilitado
    #en este paso se intentan ingresar 41 o 42 carateres aleatorios
    And El proveedor no puede ingresar mas de 40 caracteres
    And El proveedor visualiza el boton Continuar Habilitado
