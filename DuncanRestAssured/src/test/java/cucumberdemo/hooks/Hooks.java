package cucumberdemo.hooks;

import cucumberdemo.context.TestRunContext;
import cucumberdemo.context.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	private ScenarioContext scenarioContext;

	public Hooks(ScenarioContext scenarioContext) {
		this.scenarioContext = scenarioContext;
	}

	// Before order starts at 1 and goes up
	@Before(order = 99)
	public void beforeScenario() {
		Integer numberOfScenarios = (Integer) TestRunContext.getContextBag().get("numberOfScenarios");
		TestRunContext.getContextBag().put("numberOfScenarios", ++numberOfScenarios);
	}

	// After order counts down to 1
	@After(order = 1)
	public void afterScenario() {
		
	}
}