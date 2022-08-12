@onboarding @proveedor @cambiocontraseñaproductor
  Feature: Proveedor -Cambio de Contraseña

    Background:
      Given Se navega al portal New Agro proveedor

    @regression 
    Scenario: Verificar cambio de contraseña exitoso
      Given Usuario logueado en el portal New Agro
      When El proveedor visualiza el mensaje Hola Cencosud S.A.
      And Se hace click sobre el botón Cambiar contraseña
      And Ingresa la contraseña actual Colombia123
      And Ingresa una nueva contraseña valida Colombia123
      Then Se visualiza el mensaje Listo, tu contraseña ha sido actualizada
      And Se loguea con usuario cristian.duque@globant.com y password Colombia123
      Then El proveedor visualiza el mensaje Hola Cencosud S.A.
