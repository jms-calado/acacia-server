package acacia.dataobjects;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LearningProfileObject {

	@NotEmpty
	private String Active_Reflective;
	@NotEmpty
	private String Sensing_Intuitive;
	@NotEmpty
	private String Sequential_Global;
	@NotEmpty
	private String Visual_Verbal;
	@NotEmpty
	private String User;
	
	@JsonCreator
	public LearningProfileObject(
			@JsonProperty(value = "Sensing_Intuitive", required = true) String Sensing_Intuitive, 
			@JsonProperty(value = "Active_Reflective", required = true) String Active_Reflective, 
			@JsonProperty(value = "Visual_Verbal", required = true) String Visual_Verbal, 
			@JsonProperty(value = "Sequential_Global", required = true) String Sequential_Global, 
			@JsonProperty(value = "User", required = false) String User)
	{
		this.Sensing_Intuitive = Sensing_Intuitive;
		this.Active_Reflective = Active_Reflective;
		this.Visual_Verbal = Visual_Verbal;
		this.Sequential_Global = Sequential_Global;
		this.User = User;
	}

	public LearningProfileObject(){		
	}
	
	@JsonProperty("Sensing_Intuitive")
	public String getSensing_Intuitive(){
		return Sensing_Intuitive;
	}
	@JsonProperty("Sensing_Intuitive")
	public void setSensing_Intuitive(String Sensing_Intuitive){
		this.Sensing_Intuitive = Sensing_Intuitive;
	}

	@JsonProperty("Active_Reflective")
	public String getActive_Reflective(){
		return Active_Reflective;
	}
	@JsonProperty("Active_Reflective")
	public void setActive_Reflective(String Active_Reflective){
		this.Active_Reflective = Active_Reflective;
	}
	
	@JsonProperty("Sequential_Global")
	public String getSequential_Global(){
		return Sequential_Global;
	}
	@JsonProperty("Sequential_Global")
	public void setSequential_Global(String Sequential_Global){
		this.Sequential_Global = Sequential_Global;
	}

	@JsonProperty("Visual_Verbal")
	public String getVisual_Verbal(){
		return Visual_Verbal;
	}
	@JsonProperty("Visual_Verbal")
	public void setVisual_Verbal(String Visual_Verbal){
		this.Visual_Verbal = Visual_Verbal;
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
