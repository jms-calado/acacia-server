package acacia.dataobjects;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClassObject {

	@NotEmpty
	private String Subject;
	@NotEmpty
	private String[] Teacher;
	
	private String Description;
	@NotEmpty
	private String[] Student;

	@JsonCreator
	public ClassObject(@JsonProperty(value = "Subject", required = true) String Subject, 
						@JsonProperty(value = "Description", required = false) String Description, 
						@JsonProperty(value = "Teacher", required = true) String[] Teacher, 
						@JsonProperty(value = "Student", required = true) String[] Student)
	{
		this.Subject = Subject;
		this.Description = Description;
		this.Teacher = Teacher;
		this.Student = Student;
	}
	
	public ClassObject(){		
	}
	
	@JsonProperty("Subject")
	public String getSubject(){
		return Subject;
	}
	@JsonProperty("Subject")
	public void setSubject(String Subject){
		this.Subject = Subject;
	}

	@JsonProperty("Description")
	public String getDescription(){
		return Description;
	}
	@JsonProperty("Description")
	public void setDescription(String Description){
		this.Description = Description;
	}
	
	@JsonProperty("Teacher")
	public String[] getTeacher(){
		return Teacher;
	}
	@JsonProperty("Teacher")
	public void setTeacher(String[] Teacher){
		this.Teacher = Teacher;
	}

	@JsonProperty("Student")
	public String[] getStudent(){
		return Student;
	}
	@JsonProperty("Student")
	public void setStudent(String[] Student){
		this.Student = Student;
	}

}
