package acacia.dataobjects;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionObject {
	@NotEmpty
	private String Observation_Sample_Rate;
	@NotEmpty
	private String Duration;
	@NotEmpty
	private String Date_Time;
	@NotEmpty
	private String Scenario;
	@NotEmpty
	private String[] Teacher;
	@NotEmpty
	private String[] Sensory_Component;
	@NotEmpty
	private String[] Student;

	@JsonCreator
	public SessionObject(@JsonProperty(value = "Date_Time", required = true) String Date_Time, 
							@JsonProperty(value = "Duration", required = true) String Duration, 
							@JsonProperty(value = "Observation_Sample_Rate", required = true) String Observation_Sample_Rate, 
							@JsonProperty(value = "Scenario", required = true) String Scenario, 
							@JsonProperty(value = "Sensory_Component", required = false) String[] Sensory_Component, 
							@JsonProperty(value = "Teacher", required = false) String[] Teacher, 
							@JsonProperty(value = "Student", required = false) String[] Student)
	{
		this.Date_Time = Date_Time;
		this.Duration = Duration;
		this.Observation_Sample_Rate = Observation_Sample_Rate;
		this.Scenario = Scenario;
		this.Sensory_Component = Sensory_Component;
		this.Teacher = Teacher;
		this.Student = Student;
	}
	
	public SessionObject(){		
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

	@JsonProperty("Observation_Sample_Rate")
	public String getObservation_Sample_Rate(){
		return Observation_Sample_Rate;
	}
	@JsonProperty("Observation_Sample_Rate")
	public void setObservation_Sample_Rate(String Observation_Sample_Rate){
		this.Observation_Sample_Rate = Observation_Sample_Rate;
	}

	@JsonProperty("Student")
	public String[] getStudent(){
		return Student;
	}
	@JsonProperty("Student")
	public void setStudent(String[] Student){
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
	
	@JsonProperty("Teacher")
	public String[] getTeacher(){
		return Teacher;
	}
	@JsonProperty("Teacher")
	public void setTeacher(String[] Teacher){
		this.Teacher = Teacher;
	}

	@JsonProperty("Sensory_Component")
	public String[] getSensory_Component(){
		return Sensory_Component;
	}
	@JsonProperty("Sensory_Component")
	public void setSensory_Component(String[] Sensory_Component){
		this.Sensory_Component = Sensory_Component;
	}
}