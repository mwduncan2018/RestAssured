@RestAssuredAlpha
Feature: RESTAssured demo with Cucumber alpha

  Scenario: Watchlist endpoint returns 200 status
    Given the watchlist endpoint is queried
	Then verify a status code of 200 is returned

  Scenario: DoesNotExist endpoint returns 404
    Given the doesnotexist endpoint is queried
	Then verify a status code of 404 is returned
	