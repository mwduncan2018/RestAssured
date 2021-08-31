package cucumberdemo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

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

public class RestAssuredBetaSteps {
	private ScenarioContext scenarioContext;

	public RestAssuredBetaSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("a watchlist entry for Trav")
	public void aWatchlistEntryForTrav() {
		JSONObject travJson = new JSONObject();
		travJson.put("FirstName", "Trav");
		travJson.put("LastName", "Duncan");
		travJson.put("Bounty", 100);
		
		scenarioContext.getContextBag().put("watchListEntry", travJson);
	}

	@And("the watchlist entry is posted")
	public void theWatchlistEntryIsPosted() {
		JSONObject watchListEntry = (JSONObject) scenarioContext.getContextBag().get("watchListEntry");
		
		Response response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(watchListEntry.toJSONString())
				.post("/watchlist/post");

		scenarioContext.getContextBag().put("response", response);
	}

	@Then("verify the watchlist entry was saved")
	public void verifyTheWatchlistEntryWasSaved() {
		JSONObject watchListEntry = (JSONObject) scenarioContext.getContextBag().get("watchListEntry");
		
		String json = RestAssured.given()
				.header("Content-Type", "application/json")
				.get("/watchlist")
				.body().asString();
		System.out.println("\t.body().asString();");
		System.out.println(json);

		JsonPath jsonPath = JsonPath.with("{\"Object\": " + json + "}");
		List<Map> result = jsonPath
				.param("firstName", "Trav")
				.param("lastName", "Duncan")
				.get("Object.findAll { x -> x.FirstName == firstName && x.LastName == lastName }");

		assertEquals(1, result.size());
	}

	@And("delete the watchlist entry")
	public void deleteTheWatchlistEntry() {
		JSONObject watchListEntry = (JSONObject) scenarioContext.getContextBag().get("watchListEntry");

		Response response = RestAssured.given()
				.header("Content-Type", "application/json")
				.queryParam("firstName", watchListEntry.get("FirstName"))
				.queryParam("lastName", watchListEntry.get("LastName"))
				.delete("/watchlist/delete");
		
		scenarioContext.getContextBag().put("response", response);
	}

}
