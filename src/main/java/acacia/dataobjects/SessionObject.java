package acacia.dataobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionObject {
	private String Observation_Sample_Rate;
	private String Duration;
	private String Date_Time;
	private String Student;
	private String Scenario;
	private String Teacher;
	private String Sensory_Component;
	
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