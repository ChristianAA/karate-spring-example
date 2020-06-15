Feature: GET ALL RESOURCES

  Background:
    * url BASE_URL

  Scenario Outline: Get all <basePath>
    Given path <basePath>
    When method get
    Then status 200

    Examples:
    | basePath              |
    | global.civilizations  |
    | global.units          |
    | global.structures     |
    | global.technologies   |