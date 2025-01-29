Feature: Get All Games

  @ignore
  Scenario: Get All Games
  """
    input(must be defined before calling this feature): expectedStatus
    output: response
    """
    Given url BASE_URL
    And path global.games_contoller
    When method get
    Then match responseStatus == expectedStatus