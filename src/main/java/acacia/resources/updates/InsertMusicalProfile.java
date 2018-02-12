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
import acacia.dataobjects.MusicalProfileObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/profile/Musical")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertMusicalProfile extends Resource{

	public InsertMusicalProfile(SparqlExecutor qe) {
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
		System.out.println(jsonbody);
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		MusicalProfileObject musicalProfile = new MusicalProfileObject();
		try {
			musicalProfile = mapper.readValue(jsonbody, MusicalProfileObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}			
		Set<ConstraintViolation<MusicalProfileObject>> constraintViolations = validator.validate(musicalProfile);
			
		if(constraintViolations.size() == 0){

			String update = "INSERT DATA {"
				+ "acacia:Musical_" + musicalProfile.getUser() + " rdf:type acacia:Academic . "
				+ "acacia:Musical_" + musicalProfile.getUser() + " acacia:Belongs_to_User acacia:" 	+ musicalProfile.getUser() 				+ " . "
				+ "acacia:Musical_" + musicalProfile.getUser() + " acacia:Musical_Instrument \""	+ musicalProfile.getMusical_Instrument()+ "\"^^xsd:string . "
				+ "}";
			System.out.println(update);
			executeUpdate(ConstantURIs.prefixes + update);
		}else{
			for (ConstraintViolation<MusicalProfileObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}
}
