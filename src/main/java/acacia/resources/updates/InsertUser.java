package acacia.resources.updates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
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

import acacia.core.JwtUser;
import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.GlobalVar;
import acacia.dataobjects.UserObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/{user_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertUser extends Resource {
	
	public InsertUser(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
	private void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist", "Student"})
	public Response insert(
			//@Auth JwtUser jwtUser,
			String jsonbody, 
			@PathParam("user_type") @Pattern(regexp = "Student|Teacher|Admin|Annalist") @NotEmpty String user_type) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		System.out.println(jsonbody);
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		UserObject user = new UserObject();
		try {
			user = mapper.readValue(jsonbody, UserObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Set<ConstraintViolation<UserObject>> constraintViolations = validator.validate(user);
		
		if(constraintViolations.size() == 0){
			GlobalVar.GlobalUserID++;
			String userID = String.valueOf(GlobalVar.GlobalUserID);
			String update = "INSERT DATA {"
	                + "acacia:" + user_type + "_" + userID + " rdf:type acacia:" + user_type + " . "
	                + "acacia:" + user_type + "_" + userID + " acacia:Name \"" + user.getName() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + userID + " acacia:Age \"" + user.getAge() + "\"^^xsd:int . "
	                + "acacia:" + user_type + "_" + userID + " acacia:Gender \"" + user.getGender() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + userID + " acacia:Race_Ethnicity \"" + user.getRace_Ethnicity() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + userID + " acacia:User_ID \"" + userID + "\"^^xsd:int . "
	                + "}";
			System.out.println(update);
			executeUpdate(ConstantURIs.prefixes + update);

			return Response.ok("[\"" + user_type + "_" + userID + "\"]", MediaType.APPLICATION_JSON).status(201).build();
		}else{
			for (ConstraintViolation<UserObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + cv.getInvalidValue() + cv.getRootBean() + msg);
			}
			return Response.status(422).build();
		}
		//return Response.created(null).build();
	}

}
