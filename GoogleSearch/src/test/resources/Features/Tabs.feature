Feature: Switching Tabs
  As a user
  I want to switch between open tabs
  So that I can work on multiple tabs

  @SmokeTest
  Scenario: User can switch between multiple open windows
    Given I am on the Automation Practice home page
    When I click on the button "Open Tab"
    Then I can see that a new tab is opened
    And I can switch to the new tab