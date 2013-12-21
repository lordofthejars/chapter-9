Feature: Application performance

  Scenario: Flight status update performance
    Given the flight status server is running
    When a production peak hour of updates is sent at 5 times production throughput
    Then 95% of the updates should be received within 30s
    And 100% of the updates should be received within 60s
    And 100% of the updates should be received successfully