import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
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

	@AfterEach
	void afterEach() {
		System.out.println("");
	}

	@Test
	void abc() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RestAssured.get("/watchlist").then().statusCode(200).assertThat().body("", Matchers.comparesEqualTo(35));
		//then().statusCode(200).assertThat().body("", Matchers.equalTo(35));
	}
	
	@Test
	void sendRequest() {
		RestAssured.baseURI = "http://localhost:60030/api";
		
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/watchlist");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
	}

	@Test
	void validateStatus200() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/watchlist");
		assertEquals(200, response.getStatusCode(), "Correct status code of 200 returned");
	}

	@Test
	void validateStatus404() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/none");
		assertEquals(404, response.getStatusCode());
	}

	@Test
	void validateStatusLine() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/watchlist");
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
	}

	@Test
	void validateHeaderInfo() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/watchlist");

		String contentType = response.header("Content-Type");
		System.out.println("Content-Type..." + contentType);

		String server = response.header("Server");
		System.out.println("Server..." + server);

		String contentEncoding = response.header("Content-Encoding");
		System.out.println("Content-Encoding..." + contentEncoding);
	}

	@Test
	void validateAllHeaderInfo() {
		RestAssured.baseURI = "http://localhost:60030/api";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/watchlist");

		Headers headers = response.headers();
		System.out.println("=== ALL HEADER INFO ===");
		for (Header header : headers) {
			System.out.println(header.getName() + "..." + header.getValue());
		}

	}

}
