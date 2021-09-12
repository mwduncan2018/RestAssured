package cucumberdemo.unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import cucumberdemo.marshalling.WatchListEntryMarshalling;
import cucumberdemo.restapi.model.watchlistentry.WatchListEntry;
import cucumberdemo.testdata.provider.WatchListEntryTestDataProvider;

class WatchListEntryMarshallingTests {
	public static WatchListEntry x;
	public static WatchListEntry y;
	public static WatchListEntry z;

	@BeforeEach
	public void beforeEachTest() {
		x = new WatchListEntry("Jennifer", "Jackson", "1000");
		y = new WatchListEntry("Lisa", "Lucky", "2000");
		z = new WatchListEntry("Samantha", "Sanders", "3000");
	}
	
	@Test
	void marshalArray_WatchListEntry() {
		String arrayResult = WatchListEntryMarshalling.marshalJson(new WatchListEntry[] { x, y, z });
		System.out.println(arrayResult);
	}

	@Test
	@Disabled
	void marshalList_WatchListEntry() {
		String listResult = WatchListEntryMarshalling.marshalJson(new ArrayList<>(Arrays.asList(new WatchListEntry[] { x, y, z})));
		System.out.println(listResult);
	}

}
