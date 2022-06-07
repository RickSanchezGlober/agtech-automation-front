Feature: Login

  @regression
  Scenario: Verificar login exitoso
    Given Se navega al portal Galicia Rural
    And Se hace click sobre el botón Logueate
    When Se ingresa con usuario cristian.duque@globant.com y password Colombia123
    And Se hace click sobre el botón Iniciar sesión
    Then Se visualiza el mensaje Te damos la bienvenida a Agtech