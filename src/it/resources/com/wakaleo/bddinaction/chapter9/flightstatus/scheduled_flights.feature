Feature: Listing scheduled flights

  Scenario: Listing scheduled flights from a given destination
    Given I want to know the flights out of SYD
    When I request the International flights
    Then I should see the following flights:
      | flightNumber | Departure | Destination | time  |
      | FH-223       | SYD       | LAX         | 06:00 |
      | FH-305       | MEL       | SFO         | 07:15 |
      | FH-234       | SYD       | LHR         | 09:25 |
      | FH-403       | SYD       | DBX         | 14:05 |


  Scenario: Should return scheduled flights in JSON form
    Given the following flights have been scheduled:
      | flightNumber | Departure | Destination | time  |
      | FH-101       | SYD       | MEL         | 06:15 |
      | FH-102       | MEL       | SYD         | 06:30 |
      | FH-223       | SYD       | LAX         | 06:00 |
      | FH-305       | MEL       | SFO         | 07:15 |
      | FH-234       | SYD       | LHR         | 09:25 |
      | FH-403       | SYD       | DBX         | 14:05 |
    When I request the International flights in JSON form
    Then I should receive the following flights:
    """
    [
      {"flightNumber":"FH-223","departure":"SYD","destination":"LAX","time":"06:00"},
      {"flightNumber":"FH-305","departure":"MEL","destination":"SFO","time":"07:15"},
      {"flightNumber":"FH-234","departure":"SYD","destination":"LHR","time":"09:25"},
      {"flightNumber":"FH-403","departure":"SYD","destination":"DBX","time":"14:05"}
    ]
    """