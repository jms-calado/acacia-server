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
import acacia.dataobjects.AcademicProfileObject;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/profile/Academic")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertAcademicProfile extends Resource{

	public InsertAcademicProfile(SparqlExecutor qe) {
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
		AcademicProfileObject academicProfile = new AcademicProfileObject();
		try {
			academicProfile = mapper.readValue(jsonbody, AcademicProfileObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}			
		Set<ConstraintViolation<AcademicProfileObject>> constraintViolations = validator.validate(academicProfile);
			
		if(constraintViolations.size() == 0){

			String update = ConstantURIs.prefixes 
				+ "INSERT DATA {"
				+ "acacia:Academic_" + academicProfile.getUser() + " rdf:type acacia:Academic . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Belongs_to_User acacia:" 	+ academicProfile.getUser() 				+ " . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Education_Degree \"" 		+ academicProfile.getEducation_Degree() 	+ "\"^^xsd:string . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Area_of_Degree \"" 			+ academicProfile.getArea_of_Degree() 		+ "\"^^xsd:string . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Average_Course_Grade \"" 	+ academicProfile.getAverage_Course_Grade() + "\"^^xsd:string . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Income_Class \"" 			+ academicProfile.getIncome_Class() 		+ "\"^^xsd:string . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:Student_Status \"" 			+ academicProfile.getStudent_Status() 		+ "\"^^xsd:string . "
				+ "acacia:Academic_" + academicProfile.getUser() + " acacia:University \"" 				+ academicProfile.getUniversity() 			+ "\"^^xsd:string . "
				+ "}";
			System.out.println(update);
			executeUpdate(update);
		}else{
			for (ConstraintViolation<AcademicProfileObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
				return Response.status(422).build();
			}
		}
		return Response.status(201).build();
	}
}
