@regression @addLaptopToCart
Feature: By Articles

  Background: To add an item to the shopping cart you need to be registered.
    Given Navigate to Demoblaze web
    And Click on the button Sign up
    And Write Username
    And Write Password
    And Click on the button Sign Up
    And The Sign up successful alert is displayed
    And Click on the button Log in
    And The user complete the fields with the correct data for the login
    And Click on the button Log In
    And Verify the username is displayed


  @addLaptopToCartSuccessful
  Scenario: To add a laptop to the shopping cart
    When Click on the button Laptops
    And Select the laptop Sony vaio i5
    And Click on the button Add to cart
    Then The Product added alert is displayed
    And Click on the button Cart
    And Verify the laptop Sony vaio i5 is displayed