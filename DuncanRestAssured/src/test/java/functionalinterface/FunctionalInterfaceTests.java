package functionalinterface;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class FunctionalInterfaceTests {

	@Test
	void throwingConsumer1Test() throws Exception {
		Step.attempt(actions -> {
			Integer rand = new Random().nextInt(100);
			if (rand > 10) {
				System.out.println("ERROR: " + rand + " is greater than 10!");
				throw new Exception("Error in demo.");
			}
			else
				System.out.println("SUCCESS: " + rand + " is less than 10 :)");
		}, reset -> {
			System.out.println("Re-initializing...");
		}, 5);
	}

}
