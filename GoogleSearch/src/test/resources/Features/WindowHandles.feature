Feature: Handling multiple Windows
  As a user
  I want to switch between multiple open windows
  So that I can work on multiple windows

  @SmokeTest
  Scenario: User can switch between multiple open windows
    Given I am on the Automation Practice home page
    When I click on the CTA "Open Window"
    Then I can see that a new window is opened
    And I can switch to the new window