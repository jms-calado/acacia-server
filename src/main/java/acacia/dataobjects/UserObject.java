package acacia.dataobjects;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserObject {
	public enum Gender {Male, Female};
	@NotEmpty
	private Gender Gender;
	@NotEmpty
	private String Name;
	@NotEmpty
	private String Age;
	@NotEmpty
	private String ID;
	@NotEmpty
	private String Education_Degree;
	
	@JsonCreator
	public UserObject(@JsonProperty(value = "Gender", required = true) Gender Gender, 
					@JsonProperty(value = "Name", required = true) String Name, 
					@JsonProperty(value = "Age", required = true) String Age, 
					@JsonProperty(value = "ID", required = true) String ID, 
					@JsonProperty(value = "Education_Degree", required = true) String Education_Degree){
		this.Gender = Gender;
		this.Name = Name;
		this.Age = Age;
		this.ID = ID;
		this.Education_Degree = Education_Degree;
	}
	public UserObject(){		
	}
	
	@JsonProperty("Name")
	public String getName(){
		return Name;
	}
	@JsonProperty("Name")
	public void setName(String Name){
		this.Name = Name;
	}

	@JsonProperty("Gender")
	public Gender getGender(){
		return Gender;
	}
	@JsonProperty("Gender")
	public void setGender(Gender Gender){
		this.Gender = Gender;
	}

	@JsonProperty("Age")
	public String getAge(){
		return Age;
	}
	@JsonProperty("Age")
	public void setAge(String Age){
		this.Age = Age;
	}

	@JsonProperty("ID")
	public String getID(){
		return ID;
	}
	@JsonProperty("ID")
	public void setID(String ID){
		this.ID = ID;
	}

	@JsonProperty("Education_Degree")
	public String getEducation_Degree(){
		return Education_Degree;
	}
	@JsonProperty("Education_Degree")
	public void setEducation_Degree(String Education_Degree){
		this.Education_Degree = Education_Degree;
	}
	
}