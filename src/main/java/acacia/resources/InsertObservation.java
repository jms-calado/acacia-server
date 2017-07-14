package acacia.resources;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.ObservationObject;
import acacia.services.SparqlExecutor;

@Path("/insert/observation/{observation_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertObservation extends Resource {

	public InsertObservation(SparqlExecutor qe) {
		super(qe);
	}
	
	private static Validator validator;
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	public Response insert(@PathParam("observation_type") @Pattern(regexp = "Human|Digital") @NotEmpty String observation_type, String jsonbody)
			throws JsonParseException, JsonMappingException, IOException {
		//String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		ObservationObject observation = new ObservationObject();
		try {
			observation = mapper.readValue(jsonbody, ObservationObject.class);

			//Set<ConstraintViolation<ObservationObject>> constraintViolations = validator.validate(observation);
				
			//if(constraintViolations.size() == 0){
			
				String update = ConstantURIs.prefixes + 
						"INSERT DATA {" + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " rdf:type acacia:" + observation_type + "_Observation . " +
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " rdf:type acacia:" + observation_type + "_Observation . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Date_Time \"" + observation.getDate_Time() + "\"^^xsd:dateTime . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Duration \"" + observation.getDuration() + "\"^^xsd:time . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Observation_ID \"" + observation.getObservation_ID() + "\"^^xsd:int . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Belongs_to_Session acacia:" + observation.getSession() + " . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Belongs_to_Scenario acacia:" + observation.getScenario() + " . " + 
						"acacia:" + observation_type + "_Observation_" + observation.getObservation_ID() + " acacia:Has_Student acacia:" + observation.getStudent() + " . ";
				if(observation_type.equals("Human")){
					update = update + 
						"acacia:Human_Observation_" + observation.getObservation_ID() + " acacia:Has_Teacher acacia:" + observation.getTeacher() + " . ";
				}
				if(observation_type == "Digital"){
					update = update + 
						"acacia:Digital_Observation_" + observation.getObservation_ID() + " acacia:Has_Sensory_Component acacia:" + observation.getSensory_Component() + " . ";
				}
				update = update + "}";
				
				System.out.println(update);
				executeUpdate(update);
				
			/*}else{
				for (ConstraintViolation<ObservationObject> cv : constraintViolations) {
					msg = cv.getMessage();
					System.out.println("Validator Error: " + msg);
					return Response.status(422).entity(msg).build();
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			//return Response.status(422).entity("Cause: " + msg).build();
			return Response.status(422).build();
		}
		return Response.status(201).build();
	}

}
