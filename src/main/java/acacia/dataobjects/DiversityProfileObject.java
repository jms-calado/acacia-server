package acacia.dataobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DiversityProfileObject {
	
	public enum Disability {Blind, Deaf, Hearing_Impaired, Other, Physical_Disability, Speech_Disorder, Visually_Impaired};
	@NotNull
	private Disability Disability;
	@NotEmpty
	private String User;
	
	@JsonCreator
	public DiversityProfileObject(
			@JsonProperty(value = "Disability", required = true) Disability Disability, 
			@JsonProperty(value = "User", required = false) String User)
	{
		this.Disability = Disability;
		this.User = User;
	}

	public DiversityProfileObject(){		
	}
	
	@JsonProperty("Disability")
	public Disability getDisability(){
		return Disability;
	}
	@JsonProperty("Disability")
	public void setDisability(Disability Disability){
		this.Disability = Disability;
	}
	
	@JsonProperty("User")
	public String getUser(){
		return User;
	}
	@JsonProperty("User")
	public void setUser(String User){
		this.User = User;
	}
	
}
