Feature: User Login to Sauce Labs Demo App

  # Scenario 1: Valid Login
  Scenario: Valid user is able to log in with valid credentials
    Given the user is on the Sauce Labs Demo App login page
    When the user enters a valid username "standard_user"
    And the user enters a valid password "secret_sauce"
    And clicks the "LOGIN" button
    Then the user should be redirected to the products page

  # Scenario 2: Invalid Login
  Scenario: Invalid user is not able to log in with incorrect credentials
    Given the user is on the Sauce Labs Demo App login page
    When the user enters an invalid username "invalid_user"
    And the user enters an invalid password "invalid_pass"
    And clicks the "LOGIN" button
    Then an error message "Username and password do not match" should be displayed

  # Scenario 3: Empty Username
  Scenario: Error message is displayed when username is empty
    Given the user is on the Sauce Labs Demo App login page
    When the user leaves the username field empty
    And the user enters a valid password "secret_sauce"
    And clicks the "LOGIN" button
    Then an error message "Username is required" should be displayed

  # Scenario 4: Empty Password
  Scenario: Error message is displayed when password is empty
    Given the user is on the Sauce Labs Demo App login page
    When the user enters a valid username "standard_user"
    And the user leaves the password field empty
    And clicks the "LOGIN" button
    Then an error message "Password is required" should be displayed

  # Scenario 5: Logout Functionality
  Scenario: User can log out after logging in
    Given the user is on the Sauce Labs Demo App login page
    When the user enters a valid username "standard_user"
    And the user enters a valid password "secret_sauce"
    And clicks the "LOGIN" button
    Then the user should be redirected to the products page
    When the user clicks on the menu icon
    And the user selects the "Logout" option
    Then the user should be redirected to the login page
