package cucumberdemo.testdata.provider;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumberdemo.configuration.ConfigurationProperties;
import cucumberdemo.restapi.model.watchlistentry.WatchListEntry;

public class WatchListEntryTestDataProvider {
	private static String jsonTestDataPath = ConfigurationProperties.getTestDataPath() + "/watchlist-entries.json";

	// Returning test data should not be in the same file as converting JSON to object.
	// Make a new class for conversion between JSON and object.
	
	// Returns all test data
	// Returns a List<WatchListEntry> 
	public static List<WatchListEntry> getAll() {
		return Arrays.asList(unmarshalJson());
	}

	// Returns test data
	// Returns a WatchListEntry by first name and last name
	public static WatchListEntry getByName(String firstName, String lastName) {
		for (WatchListEntry x : getAll()) {
			if (x.getFirstName().equals(firstName) && x.getLastName().equals(lastName)) {
				return x;
			}
		}
		return null;
	}

	// Converts one WatchListEntry to JSON
	public static String marshalJson(WatchListEntry watchListEntry) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(watchListEntry);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Converts an array of WatchListEntry to JSON
	public static String marshalJson(WatchListEntry[] watchListEntryArray) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(watchListEntryArray);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Converts a list of WatchListEntry to JSON
	public static String marshalJson(List<WatchListEntry> watchListEntries) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(watchListEntries);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Converts JSON to an array of WatchListEntry
	public static WatchListEntry[] unmarshalJson(String json) {
		WatchListEntry[] watchListEntryArray = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			watchListEntryArray = objectMapper.readValue(json, WatchListEntry[].class);
		} catch (IOException e) {
			System.out.println("An exception occurred while extracting JSON test data");
			e.printStackTrace();
		}
		return watchListEntryArray;
	}

	// Reads a file and converts the JSON to an array of WatchListEntry
	private static WatchListEntry[] unmarshalJson() {
		WatchListEntry[] watchListEntryArray = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			File file = new File(jsonTestDataPath);
			watchListEntryArray = objectMapper.readValue(file, WatchListEntry[].class);
		} catch (IOException e) {
			System.out.println("An exception occurred while extracting JSON test data");
			e.printStackTrace();
		}
		return watchListEntryArray;
	}
}