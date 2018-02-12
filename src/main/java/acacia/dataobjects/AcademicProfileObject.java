package acacia.dataobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AcademicProfileObject {

	@NotEmpty
	private String Education_Degree;
	@NotEmpty
	private String Area_of_Degree;
	@NotEmpty
	private String Average_Course_Grade;
	@NotEmpty
	private String Income_Class;
	@NotEmpty
	private String University;
	public enum Student_Status {Athlete, Normal, Other, Parent, Worker};
	@NotNull
	private Student_Status Student_Status;
	@NotEmpty
	private String User;
	
	@JsonCreator
	public AcademicProfileObject(
			@JsonProperty(value = "Area_of_Degree", required = true) String Area_of_Degree, 
			@JsonProperty(value = "Education_Degree", required = true) String Education_Degree, 
			@JsonProperty(value = "Income_Class", required = true) String Income_Class, 
			@JsonProperty(value = "Average_Course_Grade", required = true) String Average_Course_Grade, 
			@JsonProperty(value = "University", required = true) String University, 
			@JsonProperty(value = "Student_Status", required = true) Student_Status Student_Status, 
			@JsonProperty(value = "User", required = true) String User)
	{
		this.Area_of_Degree = Area_of_Degree;
		this.Education_Degree = Education_Degree;
		this.Income_Class = Income_Class;
		this.Average_Course_Grade = Average_Course_Grade;
		this.University = University;
		this.Student_Status = Student_Status;
		this.User = User;
	}

	public AcademicProfileObject(){		
	}
	
	@JsonProperty("Area_of_Degree")
	public String getArea_of_Degree(){
		return Area_of_Degree;
	}
	@JsonProperty("Area_of_Degree")
	public void setArea_of_Degree(String Area_of_Degree){
		this.Area_of_Degree = Area_of_Degree;
	}

	@JsonProperty("Education_Degree")
	public String getEducation_Degree(){
		return Education_Degree;
	}
	@JsonProperty("Education_Degree")
	public void setEducation_Degree(String Education_Degree){
		this.Education_Degree = Education_Degree;
	}
	
	@JsonProperty("Average_Course_Grade")
	public String getAverage_Course_Grade(){
		return Average_Course_Grade;
	}
	@JsonProperty("Average_Course_Grade")
	public void setAverage_Course_Grade(String Average_Course_Grade){
		this.Average_Course_Grade = Average_Course_Grade;
	}

	@JsonProperty("Income_Class")
	public String getIncome_Class(){
		return Income_Class;
	}
	@JsonProperty("Income_Class")
	public void setIncome_Class(String Income_Class){
		this.Income_Class = Income_Class;
	}
	
	@JsonProperty("University")
	public String getUniversity(){
		return University;
	}
	@JsonProperty("University")
	public void setUniversity(String University){
		this.University = University;
	}

	@JsonProperty("Student_Status")
	public Student_Status getStudent_Status(){
		return Student_Status;
	}
	@JsonProperty("Student_Status")
	public void setStudent_Status(Student_Status Student_Status){
		this.Student_Status = Student_Status;
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
