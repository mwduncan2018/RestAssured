package functionalinterface;

public class Step {

	public static void attempt(ThrowingConsumer<?> actions, ThrowingConsumer<?> reset, Integer maxAttempts) throws Exception {
		Boolean complete = false;
		Integer numberOfAttempts = 0;
		while (!complete && numberOfAttempts < maxAttempts) {
			try {
				numberOfAttempts++;
				actions.acceptThrows(null);
				complete = true;
			} catch(Exception e) {
				//e.printStackTrace();
				if (numberOfAttempts == maxAttempts)
					throw new Exception(e);
				reset.acceptThrows(null);
			}
		}
	}
}
