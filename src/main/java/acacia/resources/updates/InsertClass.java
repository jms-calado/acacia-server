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

import org.eclipse.jetty.util.URIUtil;

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
	//@RolesAllowed({"Admin", "Teacher"})
	public Response insert(
			//@Auth JwtUser jwtUser,
			String jsonbody) throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {

		System.out.println(jsonbody);
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
			
			String classId = URIUtil.encodePath(class1.getSubject());
			//System.out.println(classId);
			
			String update = "INSERT DATA {"
					+ "acacia:Class_" + classId + " rdf:type acacia:Class . "
					+ "acacia:Class_" + classId + " acacia:Subject \"" + class1.getSubject() + "\"^^xsd:string . ";
	        if(class1.getDescription() != null) 
	        	update += "acacia:Class_" + classId + " acacia:Description \"" + class1.getDescription() + "\"^^xsd:string . ";
	                
            if(class1.getStudent().length > 0){
            	for(String student : class1.getStudent()) {
            		if(student.equals("") || student == null)
            			return Response.ok("{\"Error\":\"Invalid student\"}", MediaType.APPLICATION_JSON).status(422).build();
					update = update + 
						"acacia:Class_" + classId + " acacia:Has_Student acacia:" + student + " . ";
            	}
			}    
            if(class1.getTeacher().length > 0){
            	for(String teacher : class1.getTeacher()) {
            		if(teacher.equals("") || teacher == null)
            			return Response.ok("{\"Error\":\"Invalid teacher\"}", MediaType.APPLICATION_JSON).status(422).build();
					update = update + 
						"acacia:Class_" + classId + " acacia:Has_Teacher acacia:" + teacher + " . ";
            	}
			}
			update = update + "}";
			
			System.out.println(update);
			executeUpdate(ConstantURIs.prefixes + update);
			return Response.ok("[\"Class_" + classId + "\"]", MediaType.APPLICATION_JSON).status(201).build();
			//return Response.ok().status(201).build();

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
