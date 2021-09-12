package cucumberdemo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import io.restassured.specification.RequestSpecification;
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
		String jsonPayload = WatchListEntryMarshalling.marshalJson(
				WatchListEntryTestDataProvider.getByName("Jennifer", "Jackson"));

		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.body(jsonPayload)
			.post("/watchlist/post");

		scenarioContext.getContextBag().put("response", response);
		scenarioContext.getContextBag().put("jsonPayload", jsonPayload);
	}

	@Then("verify the watchlist entry for Jennifer was saved")
	public void verifyTheWatchlistEntryForJenniferWasSaved() throws JsonMappingException, JsonProcessingException {
		
		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.get("/watchlist");
		
		String json = response.body().asString();
		WatchListEntry[] watchListEntryArray = WatchListEntryMarshalling.unmarshalJson(json);
		
		for (WatchListEntry entry : watchListEntryArray) {
			System.out.println(entry.getFirstName() + " -- " + entry.getLastName() + " -- " + entry.getBounty());
		}
	}

	@And("delete the watchlist entry for Jennifer")
	public void deleteTheWatchlistEntryForJennifer() {

	}
}
