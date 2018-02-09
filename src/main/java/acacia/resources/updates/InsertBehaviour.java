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
import acacia.dataobjects.BehaviourObject;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/behaviour")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertBehaviour extends Resource {
	
	public InsertBehaviour(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist", "Student"})
	public Response insert(
			//@Auth JwtUser jwtUser,
			String jsonbody) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		BehaviourObject behaviour = new BehaviourObject();
		try {
			behaviour = mapper.readValue(jsonbody, BehaviourObject.class);	
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}		
		Set<ConstraintViolation<BehaviourObject>> constraintViolations = validator.validate(behaviour);
			
		if(constraintViolations.size() == 0){

			String update = ConstantURIs.prefixes 
				+ "INSERT DATA {"
				+ "acacia:Behaviour_" + behaviour.getObservationID() + " rdf:type acacia:Behaviour . "
				+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Belongs_to_Observation acacia:Digital_Observation_" + behaviour.getObservationID() + " . ";
			if(!behaviour.getActive_Participation().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Active_Participation \"" + behaviour.getActive_Participation() + "\"^^xsd:float . ";
			}
			if(!behaviour.getAttention().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Attention \"" + behaviour.getAttention() + "\"^^xsd:float . ";
			}
			if(!behaviour.getDisengaged().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Disengaged \"" + behaviour.getDisengaged() + "\"^^xsd:float . ";
			}
			if(!behaviour.getEngaged().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Engaged \"" + behaviour.getEngaged() + "\"^^xsd:float . ";
			}
			if(!behaviour.getInactive_Participation().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Inactive_Participation \"" + behaviour.getInactive_Participation() + "\"^^xsd:float . ";
			}
			if(!behaviour.getOff_Task().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Off_Task \"" + behaviour.getOff_Task() + "\"^^xsd:float . ";
			}
			if(!behaviour.getOn_Task().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:On_Task \"" + behaviour.getOn_Task() + "\"^^xsd:float . ";
			}
			if(!behaviour.getOther_Behaviour().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Other_Behaviour \"" + behaviour.getOther_Behaviour() + "\"^^xsd:float . ";
			}
			if(!behaviour.getOther_Behaviour_Name().isEmpty()){
				update = update
					+ "acacia:Behaviour_" + behaviour.getObservationID() + " acacia:Other_Behaviour_Name \"" + behaviour.getOther_Behaviour_Name() + "\"^^xsd:float . ";
			}
			update = update + "}";
			System.out.println(update);
			executeUpdate(update);
		}else{
			for (ConstraintViolation<BehaviourObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error B: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}

}
