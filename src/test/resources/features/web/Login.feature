@regression @loginLogOut
Feature: Login y Logout

  Background: To log in you need to be registered
    Given Navigate to Demoblaze web
    Given Click on the button Sign up
    And Write Username
    And Write Password
    And Click on the button Sign Up
    And The Sign up successful alert is displayed

  @loginLogOutSuccessful
  Scenario: Login Successful
    When Click on the button Log in
    And The user complete the fields with the correct data for the login
    And Click on the button Log In
    Then Verify the username is displayed
    And Click on the button Log out
    And Verify the username is not displayed
