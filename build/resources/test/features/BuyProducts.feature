#language: en

Feature: buy products on the website

  Scenario Outline: check product purchase
    Given the user access to the web site
    And user login
      | user   | password   |
      | <user> | <password> |
    When user adds the products to the cart
      | amount   |
      | <amount> |
    Then user can view the products added to the shopping cart
      | amount   |
      | <amount> |
    And user fills out the purchase form
      | firstName   | lastName   | postalCode   |
      | <firstName> | <lastName> | <postalCode> |
    And user can Checkout until confirmation
      | confirmationMessage   |
      | <confirmationMessage> |

    Examples:
      | user          | password     | amount | firstName | lastName | postalCode | confirmationMessage       |
      #@externaldata@jdbc:sqlite:dataDriven.sqlite@test_data@*@true
      | standard_user | secret_sauce | 2      | Josue     | Presley  | 5105       | Thank you for your order! |



