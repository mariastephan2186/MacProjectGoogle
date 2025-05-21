Feature: Search
  As a user
  I want to search the internet
  So that results appropriate to my interests are returned

  @SmokeTest
  Scenario: Relevant search results are returned to the user
    Given I am on the Google UK homepage
    When I enter a search term "BBC news"
    Then results relevant to the search term are returned
  @SmokeTest
  Scenario: Browser is redirected to a URL containing Search Term
    Given I am on the Google UK homepage
    When I tap on Feeling lucky button
    Then Browser redirects to URL containing search term