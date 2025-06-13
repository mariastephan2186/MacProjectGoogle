Feature: Selecting and Deselecting options
  As a user
  I want to be able to select more than one option at the same time
  So that I can have multiple options to select from
@SmokeTest
  Scenario: User can select or deselect multiple options provided
    Given I am on the Automation Practice home page
    When I select more than one option
    Then I can see multiple options can be selected
    And I can also deselect the options