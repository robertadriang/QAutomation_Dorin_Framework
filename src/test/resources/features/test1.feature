Feature: Test cases for owner endpoint

  Scenario Outline: Verify that user can create a new owner 2
    When The user creates owner with firstName <firstName> and lastName <lastName>
    Then The <firstName> <lastName> owner is in the response
    And The <lastName> owner is created
    Examples:
      | firstName | lastName |
      | Cristina  | Pichiu   |

  Scenario Outline: Verify that user can create a new owner 232432
    When The user creates owner with firstName <firstName> and lastName <lastName>
    Then The <firstName> <lastName> owner is in the response
    And The <lastName> owner is created
    Examples:
      | firstName | lastName |
      | Cristina  | Pichiu   |
