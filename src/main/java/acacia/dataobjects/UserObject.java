package acacia.dataobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserObject {
	public enum Gender {Male, Female};
	@NotNull
	private Gender Gender;
	//@NotEmpty
	//private String Gender;
	//@NotEmpty
	private String Name;
	@NotEmpty
	private String Age;
	//@NotEmpty
	//private String ID;
	@NotEmpty
	private String Race_Ethnicity;
	
	@JsonCreator
	public UserObject(
				@JsonProperty(value = "Gender", required = true) Gender Gender, 
				@JsonProperty(value = "Name", required = true) @NotEmpty String Name, 
				@JsonProperty(value = "Age", required = true) String Age, 
				//@JsonProperty(value = "ID", required = true) String ID, 
				@JsonProperty(value = "Race_Ethnicity", required = true) String Race_Ethnicity){
		this.Gender = Gender;
		this.Name = Name;
		this.Age = Age;
		//this.ID = ID;
		this.Race_Ethnicity = Race_Ethnicity;
	}
	public UserObject(){		
	}

	@JsonProperty("Gender")
	public Gender getGender(){
		return Gender;
	}
	@JsonProperty("Gender")
	public void setGender(Gender Gender){
		this.Gender = Gender;
	}
	
	@JsonProperty("Name")
	public String getName(){
		return Name;
	}
	@JsonProperty("Name")
	public void setName(String Name){
		this.Name = Name;
	}

	@JsonProperty("Age")
	public String getAge(){
		return Age;
	}
	@JsonProperty("Age")
	public void setAge(String Age){
		this.Age = Age;
	}

	/*@JsonProperty("ID")
	public String getID(){
		return ID;
	}
	@JsonProperty("ID")
	public void setID(String ID){
		this.ID = ID;
	}*/

	@JsonProperty("Race_Ethnicity")
	public String getRace_Ethnicity(){
		return Race_Ethnicity;
	}
	@JsonProperty("Race_Ethnicity")
	public void setRace_Ethnicity(String Race_Ethnicity){
		this.Race_Ethnicity = Race_Ethnicity;
	}
	
}