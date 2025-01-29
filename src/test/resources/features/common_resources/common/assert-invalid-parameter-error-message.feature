Feature: Assert invalid parameter error response

  @ignore
  Scenario: Assert invalid parameter error response
  """
    input(must be defined before calling this feature): response, error_code, error_message
    output: error
    """
    * def error = read('classpath:features/validations/errors/invalid-parameter.json')
    * set error.status = error_code
    * set error.status_message = error_message
    Then match response == error