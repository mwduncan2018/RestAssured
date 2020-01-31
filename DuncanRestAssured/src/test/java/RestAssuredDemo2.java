import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.IntStream;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//import org.hamcrest.matchers;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class RestAssuredDemo2 {

	@BeforeEach
	void beforeEach() {
		RestAssured.baseURI = "http://localhost:60030/api";
	}

	@AfterEach
	void afterEach() {
		System.out.println("");
	}

	@Test
	@Ignore
	void test2() {
		// RestAssured.given().when().get("/watchlist").then().assertThat().body("",
		// hasSize(6));

	}

	@SuppressWarnings("unchecked")
	@Test
	void postToServer_wait_DeleteFromServer() {
		JSONObject requestParameters = new JSONObject();
		requestParameters.put("FirstName", "Travis");
		requestParameters.put("LastName", "Duncan");
		requestParameters.put("Bounty", 2);

		RequestSpecification request;
		Response response;

		// Post the Watch List Entry to the Web API
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.body(requestParameters.toJSONString());
		response = request.post("/watchlist/post");
		System.out.println(response.getStatusCode());

		// Check it manually
		IntStream.range(0, 3).forEachOrdered(n -> {
			System.out.println(n);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		});

		// Delete the Watch List Entry from the Web API
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.queryParam("firstName", "Travis");
		request.queryParam("lastName", "Duncan");
		response = request.delete("/watchlist/delete");
		System.out.println(response.getStatusCode());

	}

}
