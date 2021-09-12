package cucumberdemo.testdata.provider;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumberdemo.configuration.ConfigurationProperties;
import cucumberdemo.marshalling.WatchListEntryMarshalling;
import cucumberdemo.restapi.model.watchlistentry.WatchListEntry;

public class WatchListEntryTestDataProvider {
	private static String jsonTestDataPath = ConfigurationProperties.getTestDataPath() + "/watchlist-entry.json";

	// Returns all test data in a List<WatchListEntry>
	public static List<WatchListEntry> getAll() {
		return Arrays.asList(WatchListEntryMarshalling.unmarshalJson(new File(jsonTestDataPath)));
	}

	// Returns a WatchListEntry after searching by first name and last name
	public static WatchListEntry getByName(String firstName, String lastName) {
		for (WatchListEntry x : getAll()) {
			if (x.getFirstName().equals(firstName) && x.getLastName().equals(lastName)) {
				return x;
			}
		}
		return null;
	}

}