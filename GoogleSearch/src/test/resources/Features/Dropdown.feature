Feature: Selecting a value from a Dropdown
  As a user
  I want to select one option from a Dropdown
  So that I can have numerous options in a list to choose from

  @SmokeTest
  Scenario: User can select an option from a Dropdown
    Given I am on the Automation Practice home page
    When I click on the Dropdown field
    Then I can select an option from the dropdown
