@RestAssuredBeta
@Regression
Feature: RESTAssuredBeta demo with Cucumber

  Scenario: Post watchlist entry
    Given a watchlist entry for Trav
	And the watchlist entry is posted
	Then verify a status code of 201 is returned
	And verify the watchlist entry was saved
	And delete the watchlist entry
