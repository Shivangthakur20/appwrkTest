
Feature: Test 404 links

  Scenario: Test AppWrk 404 error links
    Given user open the Url "https://appwrk.com/"
    Then verify that all the links with tag "a" are returning "200" status code