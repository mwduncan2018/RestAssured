package cucumberdemo.testdata.provider;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cucumberdemo.configuration.ConfigurationProperties;
import cucumberdemo.testdata.model.post.PostRoot;
import cucumberdemo.testdata.model.post.PostWatchListEntry;

public class PostWatchListEntryProvider {
	private static String jsonTestDataPath = ConfigurationProperties.getTestDataPath() + "/post-watchlist-entry.json";

	public static List<PostWatchListEntry> getAll() {
		PostRoot root = unmarshalJson();
		return root.getPostWatchListEntries();
	}

	public static PostWatchListEntry getByName(String firstName, String lastName) {
		PostRoot root = unmarshalJson();
		for (PostWatchListEntry x : root.getPostWatchListEntries()) {
			if (x.getFirstName().equals(firstName) && x.getLastName().equals(lastName)) {
				return x;
			}
		}
		return null;		
	}

	public static String marshalJson(PostWatchListEntry postWatchListEntry) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(postWatchListEntry);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String marshalJson(List<PostWatchListEntry> postWatchListEntries) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(postWatchListEntries);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String marshalJson(PostRoot root) {
		try {
			return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(root);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	private static PostRoot unmarshalJson() {
		PostRoot root = null;
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);
			File file = new File(jsonTestDataPath);
			root = objectMapper.readValue(file, PostRoot.class);
		} catch (IOException e) {
			System.out.println("An exception occurred while extracting JSON test data");
			e.printStackTrace();
		}
		return root;
	}
}
