package acacia.dataobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MusicalProfileObject {

	public enum Musical_Instrument {Keys, Percussion, String, Wind};
	@NotNull
	private Musical_Instrument Musical_Instrument;
	@NotEmpty
	private String User;
	
	@JsonCreator
	public MusicalProfileObject(
			@JsonProperty(value = "Musical_Instrument", required = true) Musical_Instrument Musical_Instrument, 
			@JsonProperty(value = "BasicUser", required = false) String User)
	{
		this.Musical_Instrument = Musical_Instrument;
		this.User = User;
	}

	public MusicalProfileObject(){		
	}
	
	@JsonProperty("Musical_Instrument")
	public Musical_Instrument getMusical_Instrument(){
		return Musical_Instrument;
	}
	@JsonProperty("Musical_Instrument")
	public void setMusical_Instrument(Musical_Instrument Musical_Instrument){
		this.Musical_Instrument = Musical_Instrument;
	}
	
	@JsonProperty("BasicUser")
	public String getUser(){
		return User;
	}
	@JsonProperty("BasicUser")
	public void setUser(String User){
		this.User = User;
	}
	
}
