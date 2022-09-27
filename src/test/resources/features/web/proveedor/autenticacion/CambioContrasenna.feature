@onboarding @proveedor @cambiocontraseñaproductor
Feature: Proveedor -Cambio de Contraseña

  Background:
    Given Se navega al portal Nera proveedor

  @regression
  Scenario: Verificar cambio de contraseña exitoso
    Given Usuario logueado en el portal Nera
    When El proveedor visualiza el mensaje Hola MONSANTO ARGENTINA S.R.L.
    And Se hace click sobre el botón Cambiar contraseña
#  A revisar por Jhonn
#    And Ingresa la contraseña actual Test123+
#    And Ingresa una nueva contraseña valida Test123+
#    Then Se visualiza el mensaje Listo, tu contraseña ha sido actualizada
#    And El proveedor se loguea con usuario cristian.duque@globant.com y password Test123+
#    Then El proveedor visualiza el mensaje Hola MONSANTO ARGENTINA S.R.L.
