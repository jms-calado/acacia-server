package acacia.resources.updates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
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
import acacia.dataobjects.GlobalVar;
import acacia.dataobjects.IssueObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/insert/student_issue")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertStudentIssue extends Resource {
	
	public InsertStudentIssue(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
	private void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public Response insert(
			@Auth JwtUser jwtUser,
			String jsonbody) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		IssueObject issueObject = new IssueObject();
		try {
			issueObject = mapper.readValue(jsonbody, IssueObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		Set<ConstraintViolation<IssueObject>> constraintViolations = validator.validate(issueObject);
		
		if(constraintViolations.size() == 0){
			GlobalVar.GlobalObservationID++;
			String observationID = String.valueOf(GlobalVar.GlobalObservationID);
			String update1 = ConstantURIs.prefixes + 
					"INSERT DATA {" + 
					"acacia:Human_Observation_" + observationID + " rdf:type acacia:Human_Observation . " +
					"acacia:Human_Observation_" + observationID + " acacia:Date_Time \"" + issueObject.getDate_Time() + "\"^^xsd:dateTime . " + 
					"acacia:Human_Observation_" + observationID + " acacia:Duration \"" + issueObject.getDuration() + "\"^^xsd:time . " + 
					"acacia:Human_Observation_" + observationID + " acacia:Observation_ID \"" + observationID + "\"^^xsd:int . " + 
					"acacia:Human_Observation_" + observationID + " acacia:Belongs_to_Scenario acacia:" + issueObject.getScenario() + " . " + 
					"acacia:Human_Observation_" + observationID + " acacia:Has_Student acacia:" + issueObject.getStudent() + " . " +
					"acacia:Human_Observation_" + observationID + " acacia:Has_Teacher acacia:" + issueObject.getTeacher() + " . ";
			for(String session:issueObject.getSession()) {
				update1 = update1 + "acacia:Human_Observation_" + observationID + " acacia:Belongs_to_Session acacia:" + session + " . ";
			}
			update1 = update1 + "}";			
			System.out.println(update1);
			executeUpdate(update1);
						
			int i = 0;
			for(Map.Entry<String, String> entry : issueObject.getIssue().entrySet())
			{
				String update2 = ConstantURIs.prefixes + 
					"INSERT DATA {" +
	                "acacia:Student_Issue_" + observationID + "_" + i + " rdf:type acacia:Student_Issue . " +
	    			"acacia:Student_Issue_" + observationID + "_" + i + " acacia:Belongs_to_Observation acacia:Human_Observation_" + observationID + " . " +
					"acacia:Student_Issue_" + observationID + "_" + i + " acacia:Has_Issue acacia:" + entry.getKey() + " . " +
					"acacia:Student_Issue_" + observationID + "_" + i + " acacia:Value \"" + entry.getValue() + "\"^^xsd:float . " +
					"}";
				System.out.println(update2);
				executeUpdate(update2);
				i++;					
			}

			return Response.status(201).build();
		}else{
			for (ConstraintViolation<IssueObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + cv.getInvalidValue() + cv.getRootBean() + msg);
			}
			return Response.status(400).build();
		}
		//return Response.created(null).build();
	}

}
