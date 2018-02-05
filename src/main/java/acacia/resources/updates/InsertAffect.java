package acacia.resources.updates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
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

import acacia.core.JwtUser;
import acacia.dataobjects.AffectObject;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/affect")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertAffect extends Resource {
	
	public InsertAffect(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
	@POST
	@RolesAllowed({"Admin", "Teacher", "Annalist", "Student"})
	public Response insert(
			@Auth JwtUser jwtUser,
			String jsonbody) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		AffectObject affect = new AffectObject();
		try {
			affect = mapper.readValue(jsonbody, AffectObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}			
		Set<ConstraintViolation<AffectObject>> constraintViolations = validator.validate(affect);
			
		if(constraintViolations.size() == 0){

			String update = ConstantURIs.prefixes 
				+ "INSERT DATA {"
				+ "acacia:Affect_" + affect.getObservationID() + " rdf:type acacia:Affect . "
				+ "acacia:Affect_" + affect.getObservationID() + " acacia:Belongs_to_Observation acacia:Digital_Observation_" + affect.getObservationID() + " . ";
			if(!affect.getBored().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Bored \"" 			+ affect.getBored() 			+ "\"^^xsd:float . ";
			}
			if(!affect.getConcentrated().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Concentrated \"" 		+ affect.getConcentrated() 		+ "\"^^xsd:float . ";
			}
			if(!affect.getConfused().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Confused \"" 			+ affect.getConfused() 			+ "\"^^xsd:float . ";
			}
			if(!affect.getExcited().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Excited \"" 			+ affect.getExcited() 			+ "\"^^xsd:float . ";
			}
			if(!affect.getFrustrated().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Frustrated \"" 		+ affect.getFrustrated() 		+ "\"^^xsd:float . ";
			}
			if(!affect.getMeditation().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Meditation \"" 		+ affect.getMeditation() 		+ "\"^^xsd:float . ";
			}
			if(!affect.getNeutral_Affect().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Neutral_Affect \"" 	+ affect.getNeutral_Affect() 	+ "\"^^xsd:float . ";
			}
			if(!affect.getOther_Affect().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Other_Affect \"" 		+ affect.getOther_Affect() 		+ "\"^^xsd:float . ";
			}
			if(!affect.getOther_Affect_Name().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Other_Affect_Name \"" + affect.getOther_Affect_Name() + "\"^^xsd:float . ";
			}
			if(!affect.getSatisfaction().isEmpty()){
				update = update
					+ "acacia:Affect_" + affect.getObservationID() + " acacia:Satisfaction \"" 		+ affect.getSatisfaction() 		+ "\"^^xsd:float . ";
			}
			update = update	+ "}";
			System.out.println(update);
			executeUpdate(update);
		}else{
			for (ConstraintViolation<AffectObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error A: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}

}
