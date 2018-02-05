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
import acacia.dataobjects.DiversityProfileObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/profile/Diversity")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertDiversityProfile extends Resource{

	public InsertDiversityProfile(SparqlExecutor qe) {
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
		DiversityProfileObject diversityProfile = new DiversityProfileObject();
		try {
			diversityProfile = mapper.readValue(jsonbody, DiversityProfileObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}			
		Set<ConstraintViolation<DiversityProfileObject>> constraintViolations = validator.validate(diversityProfile);
			
		if(constraintViolations.size() == 0){

			String update = ConstantURIs.prefixes 
				+ "INSERT DATA {"
				+ "acacia:Diversity_" + diversityProfile.getUser() + " rdf:type acacia:Academic . "
				+ "acacia:Diversity_" + diversityProfile.getUser() + " acacia:Belongs_to_User acacia:" 	+ diversityProfile.getUser() 		+ " . "
				+ "acacia:Diversity_" + diversityProfile.getUser() + " acacia:Disability \"" 			+ diversityProfile.getDisability() 	+ "\"^^xsd:string . "
				+ "}";
			System.out.println(update);
			executeUpdate(update);
		}else{
			for (ConstraintViolation<DiversityProfileObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}
}
