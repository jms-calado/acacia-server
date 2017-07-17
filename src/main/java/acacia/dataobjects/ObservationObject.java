package acacia.dataobjects;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ObservationObject {
	@NotEmpty
	private String Observation_ID;
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
	//@NotEmpty
	private String Teacher;
	//@NotEmpty
	private String Sensory_Component;
	
	@JsonCreator
	public ObservationObject(@JsonProperty(value = "Date_Time", required = true) String Date_Time, 
							@JsonProperty(value = "Duration", required = true) String Duration, 
							@JsonProperty(value = "Observation_ID", required = true) String Observation_ID, 
							@JsonProperty(value = "Scenario", required = true) String Scenario, 
							@JsonProperty(value = "Session", required = true) String Session, 
							@JsonProperty(value = "Student", required = true) String Student, 
							@JsonProperty(value = "Teacher", required = false) String Teacher, 
							@JsonProperty(value = "Sensory_Component", required = false) String Sensory_Component)
	{
		this.Date_Time = Date_Time;
		this.Duration = Duration;
		this.Observation_ID = Observation_ID;
		this.Scenario = Scenario;
		this.Session = Session;
		this.Student = Student;
		this.Teacher = Teacher;
		this.Sensory_Component = Sensory_Component;
	}

	public ObservationObject(){		
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

	@JsonProperty("Observation_ID")
	public String getObservation_ID(){
		return Observation_ID;
	}
	@JsonProperty("Observation_ID")
	public void setObservation_ID(String Observation_ID){
		this.Observation_ID = Observation_ID;
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

	@JsonProperty("Sensory_Component")
	public String getSensory_Component(){
		return Sensory_Component;
	}
	@JsonProperty("Sensory_Component")
	public void setSensory_Component(String Sensory_Component){
		this.Sensory_Component = Sensory_Component;
	}
}