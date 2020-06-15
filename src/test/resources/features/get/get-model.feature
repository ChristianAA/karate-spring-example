Feature: GET RESOURCE MODELS

  Background:
    * url BASE_URL

  Scenario Outline: Get <resource> Model [Found]
    Given path <resource> + '/1'
    When method get
    * def expected = read('classpath:features/get/models/' + <expectedJsonName> + '.json')
    Then match response == expected

    Examples:
      | resource             | expectedJsonName         |
      | global.civilization  | global.civilizationsJson |

  Scenario Outline: Get Random <resource> Model [Found]
    Given path <basePath>
    When method get
    * def length = karate.jsonPath(response,"$..id").length
    * def utils = call read('classpath:features/common_resources/utils.feature')
    * def random_id = utils.generateRandomInt(length)
    Given path <resource> + '/' + random_id
    When method get
    * def expected = read('classpath:features/get/models/' + <expectedJsonName> + '.json')
    Then match response == expected

    Examples:
      | basePath              | resource             | expectedJsonName          |
      | global.civilizations  | global.civilization  |  global.civilizationsJson |

  Scenario Outline: Check <resource> Model [Not Found]
    Given path <resource> + '/' + global.idNotFound
    When method get
    Then status 404
    * def expected = read('classpath:features/common_resources/resource-not-found.json')
    And match response == expected

    Examples:
      | resource             |
      | global.civilization  |
      | global.unit          |
      | global.structure     |
      | global.technology    |