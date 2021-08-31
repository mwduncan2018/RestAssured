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