package acacia.resources.updates;

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

import acacia.core.JwtUser;
import acacia.dataobjects.ClassObject;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/Class")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertClass extends Resource {

	public InsertClass(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	public Response insert(
			@Auth JwtUser jwtUser,
			String jsonbody) throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		ClassObject class1 = new ClassObject();
		try {
			class1 = mapper.readValue(jsonbody, ClassObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}
		Set<ConstraintViolation<ClassObject>> constraintViolations = validator.validate(class1);
			
		if(constraintViolations.size() == 0){
			
			String update = ConstantURIs.prefixes + 
					"INSERT DATA {"
					+ "acacia:Class_" + class1.getSubject() + " rdf:type acacia:Class . "
					+ "acacia:Class_" + class1.getSubject() + " acacia:Subject \"" + class1.getSubject() + "\"^^xsd:string . "
	                + "acacia:Class_" + class1.getSubject() + " acacia:Description \"" + class1.getDescription() + "\"^^xsd:string . ";
	                
            if(class1.getStudent().length > 0){
            	for(String student : class1.getStudent())
				update = update + 
					"acacia:Class_" + class1.getSubject() + " acacia:Has_Student acacia:" + student + " . ";
			}    
            if(class1.getTeacher().length > 0){
            	for(String teacher : class1.getTeacher())
				update = update + 
					"acacia:Class_" + class1.getSubject() + " acacia:Has_Teacher acacia:" + teacher + " . ";
			}
			update = update + "}";
			
			System.out.println(update);
			executeUpdate(update);
			//return Response.ok("[\"Class_" + class1.getSubject() + "\"]", MediaType.APPLICATION_JSON).status(201).build();
			return Response.ok().status(201).build();

		}else{
			for (ConstraintViolation<ClassObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
			}
			return Response.status(422).build();
		}
		//return Response.status(201).build();
	}

}
