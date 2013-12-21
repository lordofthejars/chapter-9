Feature: Retrieve information about a given flight

  Scenario: Find flight details by flight number
    Given I need to know the details of flight number FH-101
    When I request the details about this flight
    Then I should receive the following:
    | flightNumber | departure | destination  | time   |
    | FH-101       | MEL       | SYD          | 06:00  |


  Scenario: Should return flight details in JSON form
    Given I need to know the details of flight number FH-102
    When I request the details about this flight in JSON format
    Then I should receive:
    """
    {
      "flightNumber":"FH-102",
      "departure":"SYD",
      "destination":"MEL",
      "time":"06:15"
    }
    """
