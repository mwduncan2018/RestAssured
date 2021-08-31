package cucumberdemo.context;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
	private Map<String, Object> contextBag;
	Integer count = 0;

	public ScenarioContext() {
		contextBag = new HashMap<String, Object>();
	}

	public Map<String, Object> getContextBag() {
		return contextBag;
	}

	public void increment() {
		count++;
	}

	public Integer getCount() {
		return count;
	}
}