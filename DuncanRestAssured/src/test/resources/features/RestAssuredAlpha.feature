@RestAssuredAlpha
Feature: RESTAssuredAlpha demo with Cucumber

  Scenario: Watchlist endpoint returns 200 status
    Given the watchlist endpoint is queried
	Then verify a status code of 200 is returned

  Scenario: DoesNotExist endpoint returns 404
    Given the doesnotexist endpoint is queried
	Then verify a status code of 404 is returned

  Scenario: Display all header info
    Given the watchlist endpoint is queried
	Then display all header info
