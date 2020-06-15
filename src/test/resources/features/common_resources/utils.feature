Feature: COMMON UTILS

@ignore
Scenario: Javascript Utils/Helpers

    * def generateRandomInt =
    """
    function(max) {
        return Math.floor(Math.random() * (max - 1)) + 1;
    }
    """