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
