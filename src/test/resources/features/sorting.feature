@sorting
Feature: Product Sorting Functionality
  As a standard user of SauceDemo
  I want to sort products by different criteria
  So that I can easily find the items I am looking for

  Background:
    Given the user is on the SauceDemo login page
    And the user logs in as standard_user

  @uc-1
  Scenario: UC-1 Sort by Name (A to Z)
    When the user selects "Name (A to Z)" in sorting dropdown
    Then items should be sorted in alphabetical order from A to Z

  @uc-2
  Scenario: UC-2 Sort by Name (Z to A)
    When the user selects "Name (Z to A)" in sorting dropdown
    Then items should be sorted in reverse alphabetical order from Z to A
