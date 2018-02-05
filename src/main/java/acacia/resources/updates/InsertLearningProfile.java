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
import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.LearningProfileObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/profile/Learning")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertLearningProfile extends Resource{

	public InsertLearningProfile(SparqlExecutor qe) {
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
		LearningProfileObject learningProfile = new LearningProfileObject();
		try {
			learningProfile = mapper.readValue(jsonbody, LearningProfileObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}			
		Set<ConstraintViolation<LearningProfileObject>> constraintViolations = validator.validate(learningProfile);
			
		if(constraintViolations.size() == 0){

			String update = ConstantURIs.prefixes 
				+ "INSERT DATA {"
				+ "acacia:Learning_" + learningProfile.getUser() + " rdf:type acacia:Learning . "
				+ "acacia:Learning_" + learningProfile.getUser() + " acacia:Belongs_to_User acacia:" 	+ learningProfile.getUser() 			 + " . "
				+ "acacia:Learning_" + learningProfile.getUser() + " acacia:Active_Reflective \""		+ learningProfile.getActive_Reflective() + "\"^^xsd:int . "
				+ "acacia:Learning_" + learningProfile.getUser() + " acacia:Sensing_Intuitive \"" 		+ learningProfile.getSensing_Intuitive() + "\"^^xsd:int . "
				+ "acacia:Learning_" + learningProfile.getUser() + " acacia:Sequential_Global \"" 		+ learningProfile.getSequential_Global() + "\"^^xsd:int . "
				+ "acacia:Learning_" + learningProfile.getUser() + " acacia:Visual_Verbal \"" 			+ learningProfile.getVisual_Verbal() 	 + "\"^^xsd:int . "
				+ "}";
			System.out.println(update);
			executeUpdate(update);
		}else{
			for (ConstraintViolation<LearningProfileObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}
}
