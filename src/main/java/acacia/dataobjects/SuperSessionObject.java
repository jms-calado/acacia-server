package acacia.dataobjects;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SuperSessionObject {
	
	private String Duration;
	@NotEmpty
	private String Date_Time;
	@NotEmpty
	private String[] VLO;	
	@NotEmpty
	private String[] Sensory_Component;
	@NotEmpty
	private String Student;

	@JsonCreator
	public SuperSessionObject(
				@JsonProperty(value = "Date_Time", required = true) String Date_Time, 
				@JsonProperty(value = "Duration", required = false) String Duration, 
				@JsonProperty(value = "Sensory_Component", required = true) String[] Sensory_Component, 
				@JsonProperty(value = "Student", required = true) String Student,
				@JsonProperty(value = "VLO", required = true) String[] VLO)
	{
		this.Date_Time = Date_Time;
		this.Duration = Duration;
		this.Sensory_Component = Sensory_Component;
		this.Student = Student;
		this.VLO = VLO;
	}
	
	public SuperSessionObject(){		
	}

	@JsonProperty("VLO")
	public String[] getVLO() {
		return VLO;
	}
	@JsonProperty("VLO")
	public void setVLO(String[] VLO) {
		this.VLO = VLO;
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

	@JsonProperty("Sensory_Component")
	public String[] getSensory_Component(){
		return Sensory_Component;
	}
	@JsonProperty("Sensory_Component")
	public void setSensory_Component(String[] Sensory_Component){
		this.Sensory_Component = Sensory_Component;
	}
}