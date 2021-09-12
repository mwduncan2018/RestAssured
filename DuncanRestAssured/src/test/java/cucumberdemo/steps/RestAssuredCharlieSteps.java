package cucumberdemo.steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import cucumberdemo.context.ScenarioContext;
import cucumberdemo.marshalling.WatchListEntryMarshalling;
import cucumberdemo.restapi.model.watchlistentry.WatchListEntry;
import cucumberdemo.testdata.provider.WatchListEntryTestDataProvider;

public class RestAssuredCharlieSteps {
	private ScenarioContext scenarioContext;

	public RestAssuredCharlieSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("a watchlist entry for Jennifer is posted")
	public void aWatchlistEntryForJenniferIsPosted() {
		WatchListEntry watchListEntry = WatchListEntryTestDataProvider.getByName("Jennifer", "Jackson");
		String jsonPayload = WatchListEntryMarshalling.marshalJson(watchListEntry);

		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.body(jsonPayload)
			.post("/watchlist/post");

		scenarioContext.getContextBag().put("response", response);
		scenarioContext.getContextBag().put("jsonPayload", jsonPayload);
		scenarioContext.getContextBag().put("watchListEntry", watchListEntry);
	}

	@Then("verify the watchlist entry for Jennifer was saved")
	public void verifyTheWatchlistEntryForJenniferWasSaved() throws JsonMappingException, JsonProcessingException {
		WatchListEntry watchListEntry = (WatchListEntry) scenarioContext.getContextBag().get("watchListEntry");
		
		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.get("/watchlist");
		
		String json = response.body().asString();
		List<WatchListEntry> watchListEntries = Arrays.asList(WatchListEntryMarshalling.unmarshalJson(json));

		assertTrue(watchListEntries.stream().anyMatch(x -> 
				x.getFirstName().equals(watchListEntry.getFirstName()) && 
				x.getLastName().equals(watchListEntry.getLastName()) && 
				x.getBounty().equals(watchListEntry.getBounty())));
	}

	@And("delete the watchlist entry for Jennifer")
	public void deleteTheWatchlistEntryForJennifer() {
		WatchListEntry watchListEntry = (WatchListEntry) scenarioContext.getContextBag().get("watchListEntry");
		
		RestAssured.given().header("Content-Type", "application/json")
				.queryParam("firstName", watchListEntry.getFirstName())
				.queryParam("lastName", watchListEntry.getLastName())
				.delete("/watchlist/delete")
				.then().assertThat().statusCode(200);
	}
}
