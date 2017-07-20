package acacia.dataobjects;

//import java.lang.reflect.Field;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmotionObject {

	private String Anger;
	private String Contempt;
	private String Disgust;
	private String Fear;
	private String Happiness;
	private String Joy;
	private String Neutral_Emotion;
	private String Sadness;
	private String Surprise;
	@NotEmpty
	private String ObservationID;
	
	@JsonProperty("Anger")
	public String getAnger(){
		return Anger;
	}
	@JsonProperty("Anger")
	public void setAnger(String Anger){
		this.Anger = Anger;
	}
	@JsonProperty("Contempt")
	public String getContempt(){
		return Contempt;
	}
	@JsonProperty("Contempt")
	public void setContempt(String Contempt){
		this.Contempt = Contempt;
	}
	@JsonProperty("Disgust")
	public String getDisgust(){
		return Disgust;
	}
	@JsonProperty("Disgust")
	public void setDisgust(String Disgust){
		this.Disgust = Disgust;
	}
	@JsonProperty("Fear")
	public String getFear(){
		return Fear;
	}
	@JsonProperty("Fear")
	public void setFear(String Fear){
		this.Fear = Fear;
	}
	@JsonProperty("Happiness")
	public String getHappiness(){
		return Happiness;
	}
	@JsonProperty("Happiness")
	public void setHappiness(String Happiness){
		this.Happiness = Happiness;
	}
	@JsonProperty("Joy")
	public String getJoy(){
		return Joy;
	}
	@JsonProperty("Joy")
	public void setJoy(String Joy){
		this.Joy = Joy;
	}
	@JsonProperty("Neutral_Emotion")
	public String getNeutral_Emotion(){
		return Neutral_Emotion;
	}
	@JsonProperty("Neutral_Emotion")
	public void setNeutral_Emotion(String Neutral_Emotion){
		this.Neutral_Emotion = Neutral_Emotion;
	}
	@JsonProperty("Sadness")
	public String getSadness(){
		return Sadness;
	}
	@JsonProperty("Sadness")
	public void setSadness(String Sadness){
		this.Sadness = Sadness;
	}
	@JsonProperty("Surprise")
	public String getSurprise(){
		return Surprise;
	}
	@JsonProperty("Surprise")
	public void setSurprise(String Surprise){
		this.Surprise = Surprise;
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
