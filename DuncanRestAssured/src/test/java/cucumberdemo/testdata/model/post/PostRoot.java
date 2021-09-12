package cucumberdemo.testdata.model.post;

import java.util.List;

public class PostRoot {
	private List<PostWatchListEntry> postWatchListEntries;

	public List<PostWatchListEntry> getPostWatchListEntries() {
		return postWatchListEntries;
	}

	public void setPostWatchListEntries(List<PostWatchListEntry> postWatchListEntries) {
		this.postWatchListEntries = postWatchListEntries;
	}

}
