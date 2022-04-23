Feature: Account
  As an admin
  I want to get detail Account user
  So that I can manage the user account data

  Scenario Outline: POST - As an admin I have to be able to create new user
    Given I set an endpoint for POST new "<username>" with "<password>"
    When I request POST detail user
    Then I validate the status code is <status_code>
    And validate the "<message>" data detail user
    And get userId if "<message>" for other request
    Examples:
      | username  | password | status_code | message   |
      | new       | Valid!@312 | 201       | success   |
      |           | Valid!@312 | 400       | required  |
      | hakim1710 |            | 400       | required  |
      |           |            | 400       | required  |
      | same      | Valid!@312 | 406       | existed   |

Scenario: POST - As a user i have to be able to generate token
    Given I set an endpoint for POST generate token
    When I request POST generate token
    Then I validate the status code is 200
    And validate the data detail after generate token
    And get token for other request

  Scenario: POST - As a user i can not generate token invalid password
    Given I set an endpoint for POST generate token
    When I request POST generate token with invalid password
    Then I validate the status code is 200
    And validate the data detail after generate token failed with invalid password

  Scenario: POST - As a user i can not generate token empty password
    Given I set an endpoint for POST generate token
    When I request POST generate token with empty password
    Then I validate the status code is 400
    And validate the data detail after generate token failed with empty password

  Scenario: GET - As a user i have to be able to get detail user
    Given I set an endpoint for GET detail user
    When I request GET detail user
    Then I validate the status code is 200
    And validate the data detail userId

  Scenario: POST - As an admin i have to be able to authorized user
    Given I set an endpoint for POST authorized
    When I request POST user authorized
    Then I validate the status code is 200
    And validate the data detail for authorized

  Scenario: POST - As a user i can not authorized user with unregistered account
    Given I set an endpoint for POST authorized
    When I request POST user authorized with unregistered account
    Then I validate the status code is 404
    And validate the data detail after authorized failed with user unregistered

  Scenario: POST - As a user i can not authorized user with empty field
    Given I set an endpoint for POST authorized
    When I request POST user authorized with empty field
    Then I validate the status code is 400
    And validate the data detail after  authorized failed with empty field
