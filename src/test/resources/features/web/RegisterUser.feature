@regression @registerUser
Feature: Register a user

  @registerUserSuccessful
  Scenario: Register a user Successful
    Given Navigate to Demoblaze web
    When Click on the button Sign up
    And Write Username
    And Write Password
    And Click on the button Sign Up
    Then The Sign up successful alert is displayed
