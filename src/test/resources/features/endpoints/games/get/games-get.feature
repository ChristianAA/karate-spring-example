@games
Feature: GAMES-CONTROLLER GET

  @getAll
  Scenario: Retrieve a list of all free-to-play games.
    Given call read('classpath:features/common_resources/games/get-all-games.feature') {expectedStatus: 200}
    * def game = read('classpath:features/validations/models/game.json')
    Then match response == '#[_ > 0]'
    And match each response[*] == game

  @platform
  Scenario Outline: Retrieve a list of all available games from a specific platform
    Given call read('classpath:features/common_resources/games/get-games-by-platform.feature') {platform: '<platform>', expectedStatus: 200}
    * def game = read('classpath:features/validations/models/game.json')
    Then match response == '#[_ > 0]'
    And match each response[*] == game
    * def result = karate.jsonPath(response, "$.[?(@.title == '" + <title> + "')]")
    And match result == '#[1]'

    Examples:
      | platform | title                 |
      | pc       | 'PUBG: BATTLEGROUNDS' |
      | browser  | 'Ultimate Pirates'    |

  @platform @invalid
  Scenario Outline: Retrieve a list of all available games from a specific platform [INVALID]
    Given call read('classpath:features/common_resources/games/get-games-by-platform.feature') {platform: '<platform>', expectedStatus: 404}
    Then call read('classpath:features/common_resources/common/assert-invalid-parameter-error-message.feature') {error_code: <error_code>, error_message: '<error_message>'}

    Examples:
      | platform | error_code | error_message                                           |
      | PC       | 0          | No platform found, please check the correct parameters. |
