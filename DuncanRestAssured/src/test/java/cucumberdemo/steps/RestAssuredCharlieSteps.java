package cucumberdemo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.But;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cucumberdemo.context.ScenarioContext;
import cucumberdemo.testdata.provider.PostWatchListEntryProvider;

public class RestAssuredCharlieSteps {
	private ScenarioContext scenarioContext;

	public RestAssuredCharlieSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("a watchlist entry for Jennifer is posted")
	public void aWatchlistEntryForJenniferIsPosted() {
		String jsonPayload = PostWatchListEntryProvider.marshalJson(
				PostWatchListEntryProvider.getByName("Jennifer", "Jackson"));

		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.body(jsonPayload)
			.post("/watchlist/post");

		scenarioContext.getContextBag().put("response", response);
		scenarioContext.getContextBag().put("jsonPayload", jsonPayload);
	}

	@Then("verify the watchlist entry for Jennifer was saved")
	public void verifyTheWatchlistEntryForJenniferWasSaved() {
		
		Response response = RestAssured.given()
			.header("Content-Type", "application/json")
			.get("/watchlist");

		String json = response.body().asString();

		System.out.println(".......................................................");
		System.out.println(json);
		System.out.println(".......................................................");

	}

	@And("delete the watchlist entry for Jennifer")
	public void deleteTheWatchlistEntryForJennifer() {

	}
}
