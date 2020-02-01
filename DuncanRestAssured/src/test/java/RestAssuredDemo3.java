import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

class RestAssuredDemo3 {

	RequestSpecification request;
	Response response;
	JSONObject gailJson = new JSONObject();
	{
		gailJson.put("FirstName", "Gail");
		gailJson.put("LastName", "Duncan");
		gailJson.put("Bounty", 3);		
	}
	JSONObject travJson = new JSONObject();
	{
		travJson.put("FirstName", "Trav");
		travJson.put("LastName", "Duncan");
		travJson.put("Bounty", 4);
	}
	{
		RestAssured.baseURI = "http://localhost:60030/api";		
	}
	
	
	@AfterEach
	void afterEach() {
		// Delete the Watch List Entry for Gail
		RestAssured.given()
			.header("Content-Type", "application/json")
			.queryParam("firstName", gailJson.get("FirstName"))
			.queryParam("lastName", gailJson.get("LastName"))
		.when()
			.delete("/watchlist/delete");

		// Delete the Watch List Entry for Trav
		RestAssured.given()
			.header("Content-Type", "application/json")
			.queryParam("firstName", travJson.get("FirstName"))
			.queryParam("lastName", travJson.get("LastName"))
		.when()
			.delete("/watchlist/delete");
	}
	
	@Test
	void postWatchListEntryTrav_verifyWatchListEntryTrav() {
		// Post the Watch List Entry to the Web API
		RestAssured.given().header("Content-Type", "application/json").body(travJson.toJSONString()).when().post("/watchlist/post").then().assertThat().statusCode(201);

		// Verify the Watch List Entry has been added using the Web API
		String json = RestAssured.given().header("Content-Type", "application/json").when().get("/watchlist").getBody().asString();
		Map<String, ?> result = JsonPath.from("{\"Object\": " + json + "}").get("Object.find { x -> x.FirstName == 'Trav' && x.LastName == 'Duncan' }");
		assertNotNull(result);
		
		System.out.println(result.toString());
	}
	
	@Test
	void postWatchListEntryGail_verifyWatchListEntryGail() {
		// Post the Watch List Entry to the Web API
		RestAssured.given()
			.header("Content-Type", "application/json")
			.body(gailJson.toJSONString())
		.when()
			.post("/watchlist/post")
		.then()
			.assertThat()
			.statusCode(201);

		// Verify the Watch List Entry has been added using the Web API
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		response = request.when().get("/watchlist");
		response.then().body(Matchers.containsString("Gail"));
		
		System.out.println(response.getBody().asString());
		String mike = JsonPath.from(response.getBody().asString()).get("[0].FirstName");
		System.out.println(mike);
		
		List<String> gail = JsonPath.from("{\"Object\": " + response.getBody().asString() + "}").get("Object.findAll { x -> x.FirstName == 'Gail' }");
		System.out.println(gail);		
	}

}
