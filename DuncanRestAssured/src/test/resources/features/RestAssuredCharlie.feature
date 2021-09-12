@RestAssuredCharlie
@Regression
Feature: RESTAssuredCharlie with Jackson

  Scenario: Post watchlist entry Alpha
    Given a watchlist entry for Jennifer is posted
	Then verify a status code of 201 is returned
	Then verify the watchlist entry for Jennifer was saved
	And delete the watchlist entry for Jennifer
	
