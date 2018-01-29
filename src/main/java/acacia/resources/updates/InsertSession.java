package acacia.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.SessionObject;
import acacia.services.SparqlExecutor;

@Path("/insert/session")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertSession extends Resource {
	
	public InsertSession(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	public Response insert(String jsonbody) throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		SessionObject session = new SessionObject();
		try {
			session = mapper.readValue(jsonbody, SessionObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}
		Set<ConstraintViolation<SessionObject>> constraintViolations = validator.validate(session);
			
		if(constraintViolations.size() == 0){
			
			LocalDateTime date_time = LocalDateTime.parse(session.getDate_Time());
			String session_id = date_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			
			String update = ConstantURIs.prefixes + 
					"INSERT DATA {"
					+ "acacia:Session_" + session_id + " rdf:type acacia:Session . "
					+ "acacia:Session_" + session_id + " acacia:Date_Time \"" + session.getDate_Time() + "\"^^xsd:dateTime . "
	                + "acacia:Session_" + session_id + " acacia:Duration \"" + session.getDuration() + "\"^^xsd:time . "
	                + "acacia:Session_" + session_id + " acacia:Observation_Sample_Rate \"" + session.getObservation_Sample_Rate() + "\"^^xsd:float . "
	                + "acacia:Session_" + session_id + " acacia:Belongs_to_Scenario acacia:" + session.getScenario() + " . ";
	                
            if(session.getStudent().length > 0){
            	for(String student : session.getStudent())
				update = update + 
						"acacia:Session_" + session_id + " acacia:Has_Student acacia:" + student + " . ";
			}    
            if(session.getTeacher().length > 0){
            	for(String teacher : session.getTeacher())
				update = update + 
                		"acacia:Session_" + session_id + " acacia:Has_Teacher acacia:" + teacher + " . ";
			}    
            if(session.getSensory_Component().length > 0){
            	for(String sensory_component : session.getSensory_Component())
				update = update + 
                		"acacia:Session_" + session_id + " acacia:Has_Sensory_Component acacia:" + sensory_component + " . ";
			}

			update = update + "}";
			
			System.out.println(update);
			executeUpdate(update);
			return Response.ok("[\"Session_" + session_id + "\"]", MediaType.APPLICATION_JSON).status(201).build();

		}else{
			for (ConstraintViolation<SessionObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
			}
			return Response.status(422).build();
		}
		//return Response.status(201).build();
	}

}
