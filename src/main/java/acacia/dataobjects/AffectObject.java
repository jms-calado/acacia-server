package acacia.dataobjects;

//import java.lang.reflect.Field;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AffectObject {

	private String Bored;
	private String Concentrated;
	private String Confused;
	private String Exited;
	private String Frustrated;
	private String Meditation;
	private String Neutral_Affect;
	private String Satisfaction;
	private String Other_Affect;
	private String Other_Affect_Name;
	@NotEmpty
	private String ObservationID;
	
	@JsonProperty("Bored")
	public String getBored(){
		return Bored;
	}
	@JsonProperty("Bored")
	public void setBored(String Bored){
		this.Bored = Bored;
	}
	@JsonProperty("Concentrated")
	public String getConcentrated(){
		return Concentrated;
	}
	@JsonProperty("Concentrated")
	public void setConcentrated(String Concentrated){
		this.Concentrated = Concentrated;
	}
	@JsonProperty("Confused")
	public String getConfused(){
		return Confused;
	}
	@JsonProperty("Confused")
	public void setConfused(String Confused){
		this.Confused = Confused;
	}
	@JsonProperty("Exited")
	public String getExited(){
		return Exited;
	}
	@JsonProperty("Exited")
	public void setExited(String Exited){
		this.Exited = Exited;
	}
	@JsonProperty("Frustrated")
	public String getFrustrated(){
		return Frustrated;
	}
	@JsonProperty("Frustrated")
	public void setFrustrated(String Frustrated){
		this.Frustrated = Frustrated;
	}
	@JsonProperty("Meditation")
	public String getMeditation(){
		return Meditation;
	}
	@JsonProperty("Meditation")
	public void setMeditation(String Meditation){
		this.Meditation = Meditation;
	}
	@JsonProperty("Neutral_Affect")
	public String getNeutral_Affect(){
		return Neutral_Affect;
	}
	@JsonProperty("Neutral_Affect")
	public void setNeutral_Affect(String Neutral_Affect){
		this.Neutral_Affect = Neutral_Affect;
	}
	@JsonProperty("Satisfaction")
	public String getSatisfaction(){
		return Satisfaction;
	}
	@JsonProperty("Satisfaction")
	public void setSatisfaction(String Satisfaction){
		this.Satisfaction = Satisfaction;
	}
	@JsonProperty("Other_Affect")
	public String getOther_Affect(){
		return Other_Affect;
	}
	@JsonProperty("Other_Affect")
	public void setOther_Affect(String Other_Affect){
		this.Other_Affect = Other_Affect;
	}
	@JsonProperty("Other_Affect_Name")
	public String getOther_Affect_Name(){
		return Other_Affect_Name;
	}
	@JsonProperty("Other_Affect_Name")
	public void setOther_Affect_Name(String Other_Affect_Name){
		this.Other_Affect_Name = Other_Affect_Name;
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
