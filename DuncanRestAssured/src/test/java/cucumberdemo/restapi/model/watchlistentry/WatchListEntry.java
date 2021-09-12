package cucumberdemo.restapi.model.watchlistentry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "FirstName", "LastName", "Bounty" })
public class WatchListEntry {
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Bounty")
	private String bounty;

	public WatchListEntry() {
	}
	
	public WatchListEntry(String firstName, String lastName, String bounty) {
		setFirstName(firstName);
		setLastName(lastName);
		setBounty(bounty);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBounty() {
		return bounty;
	}

	public void setBounty(String bounty) {
		this.bounty = bounty;
	}

}
