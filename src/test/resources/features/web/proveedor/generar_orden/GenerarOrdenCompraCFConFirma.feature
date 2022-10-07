@financing @proveedor @OrdenCompraCesionForward
Feature: Generar Orden de Compra con Método de Pago Cesión Forward

  Background:
    Given Se navega al portal Nera proveedor
    And Se ingresa con usuario cjfranzin@gmail.com y password Test123+
    And Se selecciona el proveedor PROFERTIL SA
    When El proveedor hace click en el botón Crear Orden


  @TEST_ID_AG-3283 @TEST_ID_AG-3284 @regression
  Scenario: Proveedor - FIX - Validación de Condiciones - Validar Identificación de CUIT
  Proveedor - FIX - Validación de Condiciones - Verifico validación Cesión Forward
    And El proveedor visualiza el boton Buscar Deshabilitado
    And El proveedor visualiza Ingresá el CUIT en la pantalla Identificación de cliente
    And El proveedor visualiza Escribí 11 números en la pantalla Identificación de cliente
    And El proveedor ingresa 30637300764 en el campo Ingresá el CUIT
    And Se lee el cuit en formato correcto
    And El proveedor visualiza el boton Buscar Habilitado
    And El proveedor hace click en el botón Buscar
    Then Recuperar datos de servicios api bff con ruta customer-validation/ y guardar variables abajo
      | cuit_teradata |
      | name          |
      | ok_bank       |
      | is_mipyme     |
    And El proveedor puede ver Datos del Productor Asociado
      | name          |
      | cuit_teradata |
    And El proveedor hace click en el botón del Productor encontrado
    And El proveedor ingresa Descripción Válida en el campo Descripción
    And El proveedor hace click en el botón Continuar
    And El proveedor seleciona medio de pago Cesión de forward
    And El proveedor ingresa monto mayor a $1.500.000 en el campo Ingresá el monto del crédito
    And El proveedor selecciona en Tipo de convenio opcion Fwd Enero 2023 sin subsidio
    And El proveedor selecciona en Gestión del forward opcion Zeni
    And El proveedor hace click en el botón Simular Crédito
    And Validar datos de servicios api bff con ruta simulation con body bff_simulation_CF.txt
      | TNA del crédito           | farmer.tna                    |
      | CFT                       | cft                           |
      | Interés                   | installments.interest_nominal |
      | Monto                     | amount                        |
      | Cuota única, vencimiento: | due_date                      |
    And El proveedor hace click en el botón Confirmar medio de pago
    And Se llena el campo Nombre y Apellido con valor Válidos
    And Se llena el campo Correo electrónico con valor Válido
    And Se llena el campo Cód de área con valor Válido
    And Se llena el campo Número de celular con valor Válido
    And El proveedor hace click en el botón Continuar
    And El proveedor hace click en el botón Enviar orden
    And Se visualiza la pantalla de Orden generada y enviada exitosamente
    And El proveedor hace click en el botón Ir a órdenes
    And El proveedor vuelve a la Home