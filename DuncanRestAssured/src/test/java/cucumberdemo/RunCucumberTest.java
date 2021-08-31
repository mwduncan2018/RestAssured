package cucumberdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import io.restassured.RestAssured;
import cucumberdemo.context.TestRunContext;
import cucumberdemo.context.ScenarioContext;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/Destination", "json:target/cucumber.json" },
		// "html:target/Destination" puts the HTML report in 'target/Destination'
		// "json:target/cucumber.json" puts the JSON report in 'target/cucumber.json'
		monochrome = true, snippets = SnippetType.UNDERSCORE, features = "src/test/resources")
// features = "classpath:features")
public class RunCucumberTest {

	@BeforeClass
	public static void beforeClass() {
		System.out.println("**************************************************************************************");
		System.out.println("Before Cucumber Test Run");
		System.out.println("**************************************************************************************");

		TestRunContext.getContextBag().put("numberOfScenarios", 0);
		RestAssured.baseURI = "http://localhost:60030/api";

		// For REST testing, points of verification include:
		// - Response Status Code
		// - Response Header
		// - Response Body
		
		// The Response object provides a way to read Status, Headers, and Body.
		
		// W3 Status Code Definitions
		// https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html
		
		// Status Line
		// - HTTP protocol version (HTTP/1.1)
		// - Status Code (200)
		// - Status Code's string value (OK)
		// Example: HTTP/1.1 200 OK
		
		// Headers
		// Every response from the server contains zero or more headers.
		// The server sends extra info in the header.
		// This extra info is known as META DATA.
		// Each header entry is a key:value pair.
		// Example: "Content-Type" tell how to interpret what is in the Body
		// Example: "Content-Type" of "application/json" is for a JSON Body
		// Example: "Content-Type" of "application/xml" is for an XML Body
		
		// What have I accomplished?
		// Cucumber & RESTAssured together
		// POST
		// DELETE with query parameters
		// GET
		// analyze JSON with JsonPath
		// headers
		
		// https://www.javadoc.io/doc/io.rest-assured/json-path/3.0.0/io/restassured/path/json/JsonPath.html 
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("**************************************************************************************");
		System.out.println("After Cucumber Test Run");
		System.out.println("**************************************************************************************");

		Integer numberOfScenarios = (Integer) TestRunContext.getContextBag().get("numberOfScenarios");
		System.out.println("Number Of Scenarios = " + numberOfScenarios);
	}
}
