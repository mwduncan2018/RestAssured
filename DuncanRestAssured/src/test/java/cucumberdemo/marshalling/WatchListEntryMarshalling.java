package cucumberdemo.marshalling;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumberdemo.restapi.model.watchlistentry.WatchListEntry;

public class WatchListEntryMarshalling {
	
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
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			return objectMapper.readValue(json, WatchListEntry[].class);
		} catch (IOException e) {
			System.out.println("An exception occurred while extracting JSON test data");
			e.printStackTrace();
		}
		return null;
	}
	
	// Reads a file and converts the JSON to an array of WatchListEntry
	public static WatchListEntry[] unmarshalJson(File jsonFile) {
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			return objectMapper.readValue(jsonFile, WatchListEntry[].class);
		} catch (IOException e) {
			System.out.println("An exception occurred while extracting JSON test data");
			e.printStackTrace();
		}
		return null;
	}
}
