package acacia.dataobjects;

import java.util.Map;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IssueObject {
	@NotEmpty
	private Map<String, String> Issue;
	@NotEmpty
	private String Duration;
	@NotEmpty
	private String Date_Time;
	@NotEmpty
	private String Student;
	@NotEmpty
	private String Scenario;
	@NotEmpty
	private String Session;
	@NotEmpty
	private String Teacher;
	
	@JsonCreator
	public IssueObject(@JsonProperty(value = "Date_Time", required = true) String Date_Time, 
						@JsonProperty(value = "Duration", required = true) String Duration, 
						@JsonProperty(value = "Scenario", required = true) String Scenario, 
						@JsonProperty(value = "Session", required = true) String Session, 
						@JsonProperty(value = "Student", required = true) String Student, 
						@JsonProperty(value = "Teacher", required = true) String Teacher, 
						@JsonProperty(value = "Issue", required = true) Map<String, String> Issue)
	{
		this.Date_Time = Date_Time;
		this.Duration = Duration;
		this.Scenario = Scenario;
		this.Session = Session;
		this.Student = Student;
		this.Teacher = Teacher;
		this.Issue = Issue;
	}

	public IssueObject(){		
	}
	
	@JsonProperty("Date_Time")
	public String getDate_Time(){
		return Date_Time;
	}
	@JsonProperty("Date_Time")
	public void setDate_Time(String Date_Time){
		this.Date_Time = Date_Time;
	}

	@JsonProperty("Duration")
	public String getDuration(){
		return Duration;
	}
	@JsonProperty("Duration")
	public void setDuration(String Duration){
		this.Duration = Duration;
	}

	@JsonProperty("Student")
	public String getStudent(){
		return Student;
	}
	@JsonProperty("Student")
	public void setStudent(String Student){
		this.Student = Student;
	}

	@JsonProperty("Scenario")
	public String getScenario(){
		return Scenario;
	}
	@JsonProperty("Scenario")
	public void setScenario(String Scenario){
		this.Scenario = Scenario;
	}

	@JsonProperty("Session")
	public String getSession(){
		return Session;
	}
	@JsonProperty("Session")
	public void setSession(String Session){
		this.Session = Session;
	}
	
	@JsonProperty("Teacher")
	public String getTeacher(){
		return Teacher;
	}
	@JsonProperty("Teacher")
	public void setTeacher(String Teacher){
		this.Teacher = Teacher;
	}
	
	@JsonProperty("Issue")
	public Map<String, String> getIssue(){
		return Issue;
	}
	@JsonProperty("Issue")
	public void setIssue(Map<String, String> Issue){
		this.Issue = Issue;
	}
}