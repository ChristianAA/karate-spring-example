Feature: Javascript Utils/Helpers

@ignore
Scenario: Javascript Utils/Helpers

    * def generateRandomInt =
    """
    function(max) {
        return Math.floor(Math.random() * (max - 1)) + 1;
    }
    """