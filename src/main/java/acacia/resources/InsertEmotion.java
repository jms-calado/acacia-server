package acacia.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.EmotionObject;
import acacia.services.SparqlExecutor;

@Path("/insert/emotion")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertEmotion extends Resource {
	
	public InsertEmotion(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	public Response insert(String jsonbody) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		EmotionObject emotion = new EmotionObject();
		try {
			emotion = mapper.readValue(jsonbody, EmotionObject.class);			
			Set<ConstraintViolation<EmotionObject>> constraintViolations = validator.validate(emotion);
				
			if(constraintViolations.size() == 0){

				String update = ConstantURIs.prefixes 
					+ "INSERT DATA {"
					+ "acacia:Emotion_" + emotion.getObservationID() + " rdf:type acacia:Emotion . "
					+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Belongs_to_Observation acacia:" + 	emotion.getObservationID() + " . ";
				if(!emotion.getAnger().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Anger \"" + emotion.getAnger() 		   + "\"^^xsd:float . ";
				}
				if(!emotion.getContempt().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Contempt \"" + emotion.getContempt() + "\"^^xsd:float . ";
				}
				if(!emotion.getDisgust().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Disgust \"" + emotion.getDisgust() + "\"^^xsd:float . ";
				}
				if(!emotion.getFear().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Fear \"" + emotion.getFear() + "\"^^xsd:float . ";
				}
				if(!emotion.getHappiness().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Happiness \"" + emotion.getHappiness() + "\"^^xsd:float . ";
				}
				if(!emotion.getJoy().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Joy \"" + emotion.getJoy() + "\"^^xsd:float . ";
				}
				if(!emotion.getNeutral_Emotion().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Neutral_Emotion \"" + emotion.getNeutral_Emotion() + "\"^^xsd:float . ";
				}
				if(!emotion.getSadness().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Sadness \"" + emotion.getSadness() + "\"^^xsd:float . ";
				}
				if(!emotion.getSurprise().isEmpty()){
					update = update
							+ "acacia:Emotion_" + emotion.getObservationID() + " acacia:Surprise \"" + emotion.getSurprise() + "\"^^xsd:float . ";
				}
				update = update + "}";
				System.out.println(update);
				executeUpdate(update);
			}else{
				for (ConstraintViolation<EmotionObject> cv : constraintViolations) {
					msg = cv.getMessage();
					System.out.println("Validator Error: " + msg);
					return Response.status(422).entity(msg).build();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//return Response.status(422).entity("Cause: " + msg).build();
			return Response.status(422).build();
		}
		return Response.status(201).build();
	}

}
