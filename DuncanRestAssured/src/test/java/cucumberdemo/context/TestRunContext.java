package cucumberdemo.context;

import java.util.concurrent.ConcurrentHashMap;

public class TestRunContext {
	private static ConcurrentHashMap<String, Object> contextBag = new ConcurrentHashMap<>();

	public static ConcurrentHashMap<String, Object> getContextBag() {
		return contextBag;
	}
}