Feature: Cart functionality

  Scenario: User adds an item to the cart
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user adds an item to the cart
    Then the cart should contain 1 item

  Scenario: User removes an item from the cart
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user adds an item to the cart
    And the user goes to the cart
    And the user removes the item from the cart
    
    Scenario: User checks out
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    And the user adds an item to the cart
    When the user initiates the checkout process
    When the user completes the checkout with first name "ABDULLAH", last name "CHEEMA", and postal code "12345"
    Then the checkout should be confirmed with a success message
    
