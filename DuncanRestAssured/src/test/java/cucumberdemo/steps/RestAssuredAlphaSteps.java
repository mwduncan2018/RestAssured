package cucumberdemo.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import cucumberdemo.context.ScenarioContext;

public class RestAssuredAlphaSteps {
	private ScenarioContext scenarioContext;

	public RestAssuredAlphaSteps(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	@Given("the watchlist endpoint is queried")
	public void theWatchlistEndpointIsQueried() {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.request(Method.GET, "/watchlist");
		scenarioContext.getContextBag().put("response", response);
	}

	@Given("the doesnotexist endpoint is queried")
	public void theDoesNotExistEndpointIsQueried() {
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.request(Method.GET, "/doesnotexist");
		scenarioContext.getContextBag().put("response", response);
	}

	@Then("verify a status code of 200 is returned")
	public void verifyAStatusCodeOf200IsReturned() {
		Response response = (Response) scenarioContext.getContextBag().get("response");
		assertEquals(200, response.getStatusCode(), "Correct status code of 200 was returned");
	}

	@Then("verify a status code of 201 is returned")
	public void verifyAStatusCodeOf201IsReturned() {
		Response response = (Response) scenarioContext.getContextBag().get("response");
		assertEquals(201, response.getStatusCode(), "Correct status code of 201 was returned");
	}

	@Then("verify a status code of 404 is returned")
	public void verifyAStatusCodeOf404IsReturned() {
		Response response = (Response) scenarioContext.getContextBag().get("response");
		assertEquals(404, response.getStatusCode(), "Correct status code of 404 was returned");
	}

	@Then("display all header info")
	public void displayAllHeaderInfo() {
		Response response = (Response) scenarioContext.getContextBag().get("response");
		Headers headers = response.getHeaders();
		System.out.println("\t=== ALL HEADER INFO ===");
		for (Header header : headers) {
			System.out.println("\t\t" + header.getName() + "..." + header.getValue());
		}
	}

}
