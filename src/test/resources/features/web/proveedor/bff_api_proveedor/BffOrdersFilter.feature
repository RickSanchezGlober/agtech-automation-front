@proveedor @payments @bffOrdersFilter @regression
Feature: Bff Orders Filters.

  @HISTORY_ID_AG-3267
  Scenario: API - Postman - Validar Consultar Órdenes Filtradas BFF
    Given Se obtienen ordenes con servicio bff con ruta orders/filter y parametros
      | skip   | 0                                                                                                                                                                                                                 |
      | count  | 10                                                                                                                                                                                                                |
      | where  | provider._id:2,status:Pendiente                                                                                                                                                                                   |
      | fields | user_email,create_date,producer,payment_methods.financial_entity,payment_methods.financial_line,payment_methods.expiry_date,status,payment_methods.conditions.loan_amount,payment_methods.product_id,provider._id |
    When El servicio Proveedor responde status 200
    Then Se verifica el response body del servicio orders/filter