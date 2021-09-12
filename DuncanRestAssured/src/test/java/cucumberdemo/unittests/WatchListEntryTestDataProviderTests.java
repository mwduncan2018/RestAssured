package cucumberdemo.unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import cucumberdemo.testdata.model.post.PostRoot;
import cucumberdemo.testdata.model.post.PostWatchListEntry;
import cucumberdemo.testdata.provider.PostWatchListEntryProvider;

class PostWatchListEntryProviderTests {

	@Test
	void serializePostWatchListEntries() {
		PostWatchListEntry x = new PostWatchListEntry();
		x.setFirstName("Jennifer");
		x.setLastName("Jackson");
		x.setBounty("1000");
		PostWatchListEntry y = new PostWatchListEntry();
		y.setFirstName("Lisa");
		y.setLastName("Lucky");
		y.setBounty("2000");
		PostWatchListEntry z = new PostWatchListEntry();
		z.setFirstName("Samantha");
		z.setLastName("Sanders");
		z.setBounty("3000");
		List<PostWatchListEntry> list = new ArrayList<>();
		list.add(x);
		list.add(y);
		list.add(z);
		PostRoot root = new PostRoot();
		root.setPostWatchListEntries(list);
		String result = PostWatchListEntryProvider.marshalJson(root);
		System.out.println(result);
	}

}
