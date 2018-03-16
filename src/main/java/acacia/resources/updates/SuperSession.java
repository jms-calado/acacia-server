package acacia.resources.updates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
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

import org.apache.jena.query.ResultSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.core.JwtUser;
import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.GlobalVar;
import acacia.dataobjects.SessionObject;
import acacia.dataobjects.SuperSessionObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/super/session")
@Consumes(MediaType.APPLICATION_JSON)
public class SuperSession extends Resource {
	
	public SuperSession(SparqlExecutor qe) {
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
			String jsonbody) throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		System.out.println(jsonbody);
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		SuperSessionObject session = new SuperSessionObject();
		try {
			session = mapper.readValue(jsonbody, SuperSessionObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}
		Set<ConstraintViolation<SuperSessionObject>> constraintViolations = validator.validate(session);
			
		if(constraintViolations.size() == 0){
			
			LocalDateTime date_time = LocalDateTime.parse(session.getDate_Time());
			LocalDateTime min_date_time = date_time.minusMinutes(60);
			String[] duration_time = session.getDuration().split(":");
			LocalDateTime max_date_time = date_time.plusSeconds(
					Integer.parseInt(duration_time[0]) * 3600 + 
					Integer.parseInt(duration_time[1]) * 60 + 
					Integer.parseInt(duration_time[2]) +
					3600);			

			String query = "SELECT ?session "
					+ "WHERE { "
					+ "?session rdf:type acacia:Session . "
					+ "?session acacia:Date_Time ?b . "
					+ "FILTER (?b >= \"" + min_date_time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\"^^xsd:dateTime && ?b <= \"" + max_date_time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "\"^^xsd:dateTime) . "
					+ "?session acacia:Has_Student acacia:" + session.getStudent() + " . ";
			if(session.getSensory_Component().length > 0){
            	for(String sensory_component : session.getSensory_Component()) {
            		if(sensory_component.equals("") || sensory_component == null)
            			return Response.ok("{\"Error\":\"Invalid sensory_component\"}", MediaType.APPLICATION_JSON).status(422).build();
            		query = query + 
            			"?session acacia:Has_Sensory_Component acacia:" + sensory_component + " . ";
            	}
			}
            if(session.getVLO() != null){
            	if(session.getVLO().length > 0 ) {
	            	for(String vlo : session.getVLO()) {
	            		if(vlo.equals("") || vlo == null)
	            			return Response.ok("{\"Error\":\"Invalid vlo\"}", MediaType.APPLICATION_JSON).status(422).build();
	            		query = query + 
	            			"?session acacia:Has_VLO acacia:" + vlo + " . ";
	            	}
            	}
			} 
            query = query + "}";
			System.out.println(query);
			ResultSet rs = executeQuery(ConstantURIs.prefixes + query);
			List<Map<String, String>> list = buildResult(rs);

			if (!list.isEmpty()) {
				for(Map<String, String> map : list) {				
					String sessionID = map.get("session");
					return Response.ok("[\"" + sessionID + "\"]", MediaType.APPLICATION_JSON).status(201).build();
				}
				return Response.status(422).build();
			}else {
				GlobalVar.GlobalSessionID++;
				String session_id = GlobalVar.GlobalSessionID + "_" + date_time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
				
				String update = "INSERT DATA {"
						+ "acacia:Session_" + session_id + " rdf:type acacia:Session . "
						+ "acacia:Session_" + session_id + " acacia:Date_Time \"" + session.getDate_Time() + "\"^^xsd:dateTime . "
		                + "acacia:Session_" + session_id + " acacia:Duration \"" + session.getDuration() + "\"^^xsd:time . "
		                + "acacia:Session_" + session_id + " acacia:Observation_Sample_Rate \"600000\"^^xsd:float . "
		                + "acacia:Session_" + session_id + " acacia:Belongs_to_Scenario acacia:Spontaneous_ASAD . "
		                + "acacia:Session_" + session_id + " acacia:Has_Teacher acacia:Teacher_Dummy . "
		                + "acacia:Session_" + session_id + " acacia:Has_Student acacia:" + session.getStudent() + " . ";	                
	            if(session.getSensory_Component().length > 0){
	            	for(String sensory_component : session.getSensory_Component()) {
	            		if(sensory_component.equals("") || sensory_component == null)
	            			return Response.ok("{\"Error\":\"Invalid sensory_component\"}", MediaType.APPLICATION_JSON).status(422).build();
	            		update = update + 
	                		"acacia:Session_" + session_id + " acacia:Has_Sensory_Component acacia:" + sensory_component + " . ";
	            	}
				}
	            if(session.getVLO() != null){
	            	if(session.getVLO().length > 0 ) {
		            	for(String vlo : session.getVLO()) {
		            		if(vlo.equals("") || vlo == null)
		            			return Response.ok("{\"Error\":\"Invalid vlo\"}", MediaType.APPLICATION_JSON).status(422).build();
		            		update = update + 
								"acacia:Session_" + session_id + " acacia:Has_VLO acacia:" + vlo + " . ";
		            	}
	            	}
				} 
				update = update + "}";
				
				System.out.println(update);
				executeUpdate(ConstantURIs.prefixes + update);
				return Response.ok("[\"Session_" + session_id + "\"]", MediaType.APPLICATION_JSON).status(201).build();
			}
		}else{
			for (ConstraintViolation<SuperSessionObject> cv : constraintViolations) {
				msg = cv.getMessage();
				System.out.println("Validator Error: " + msg);
			}
			return Response.status(422).build();
		}
	}

}
