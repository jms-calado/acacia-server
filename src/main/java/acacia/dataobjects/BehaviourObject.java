package acacia.dataobjects;

//import java.lang.reflect.Field;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BehaviourObject {

	private String Active_Participation;
	private String Attention;
	private String Disengaged;
	private String Engaged;
	private String Inactive_Participation;
	private String Off_Task;
	private String On_Task;
	private String Other_Behaviour;
	private String Other_Behaviour_Name;
	@NotEmpty
	private String ObservationID;
	
	@JsonProperty("Active_Participation")
	public String getActive_Participation(){
		return Active_Participation;
	}
	@JsonProperty("Active_Participation")
	public void setActive_Participation(String Active_Participation){
		this.Active_Participation = Active_Participation;
	}
	@JsonProperty("Attention")
	public String getAttention(){
		return Attention;
	}
	@JsonProperty("Attention")
	public void setAttention(String Attention){
		this.Attention = Attention;
	}
	@JsonProperty("Disengaged")
	public String getDisengaged(){
		return Disengaged;
	}
	@JsonProperty("Disengaged")
	public void setDisengaged(String Disengaged){
		this.Disengaged = Disengaged;
	}
	@JsonProperty("Engaged")
	public String getEngaged(){
		return Engaged;
	}
	@JsonProperty("Engaged")
	public void setEngaged(String Engaged){
		this.Engaged = Engaged;
	}
	@JsonProperty("Inactive_Participation")
	public String getInactive_Participation(){
		return Inactive_Participation;
	}
	@JsonProperty("Inactive_Participation")
	public void setInactive_Participation(String Inactive_Participation){
		this.Inactive_Participation = Inactive_Participation;
	}
	@JsonProperty("Off_Task")
	public String getOff_Task(){
		return Off_Task;
	}
	@JsonProperty("Off_Task")
	public void setOff_Task(String Off_Task){
		this.Off_Task = Off_Task;
	}
	@JsonProperty("On_Task")
	public String getOn_Task(){
		return On_Task;
	}
	@JsonProperty("On_Task")
	public void setOn_Task(String On_Task){
		this.On_Task = On_Task;
	}
	@JsonProperty("Other_Behaviour")
	public String getOther_Behaviour(){
		return Other_Behaviour;
	}
	@JsonProperty("Other_Behaviour")
	public void setOther_Behaviour(String Other_Behaviour){
		this.Other_Behaviour = Other_Behaviour;
	}
	@JsonProperty("Other_Behaviour_Name")
	public String getOther_Behaviour_Name(){
		return Other_Behaviour_Name;
	}
	@JsonProperty("Other_Behaviour_Name")
	public void setOther_Behaviour_Name(String Other_Behaviour_Name){
		this.Other_Behaviour_Name = Other_Behaviour_Name;
	}
	@JsonProperty("ObservationID")
	public String getObservationID(){
		return ObservationID;
	}
	@JsonProperty("ObservationID")
	public void setObservationID(String ObservationID){
		this.ObservationID = ObservationID;
	}
	/*
	public boolean hasAtLeastOneNonEmpty() {
	    Class<? extends EmotionObject> class1 = this.getClass();
	    Field[] fields = class1.getDeclaredFields();
	    for (Field field : fields) {
	        try {
	            if (field.get(this) != null) {
	                return true;
	            }
	        } catch (IllegalArgumentException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    return false;
	}
	*/
}
