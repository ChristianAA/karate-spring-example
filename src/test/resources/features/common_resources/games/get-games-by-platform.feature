Feature: Get Games By Platform

  @ignore
  Scenario: Get Games By Platform
  """
    input(must be defined before calling this feature): platform, expectedStatus
    output: response
    """
    Given url BASE_URL
    And path global.games_contoller
    And param platform = platform
    When method get
    Then match responseStatus == expectedStatus