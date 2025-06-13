Feature: Selecting one option from many
  As a user
  I want to be able to select one option from many
  So that I can just select one of these.
  @SmokeTest
  Scenario: User can select only one option from a list of options
    Given I am on the Automation Practice home page
    When I select one radio option
    Then I can see that the option is selected
    And the rest of the options are not selected