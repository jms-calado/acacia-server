package acacia.resources;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

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
	}

	@POST
	public void insert(String jsonbody) throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		SessionObject session = new SessionObject();
		try {
			session = mapper.readValue(jsonbody, SessionObject.class);

			String update = ConstantURIs.prefixes + 
					"INSERT DATA {"
					+ "acacia:Session_" + session.getDate_Time() + " rdf:type acacia:Session . "
					+ "acacia:Session_" + session.getDate_Time() + " acacia:Date_Time \"" + session.getDate_Time() + "\"^^xsd:dateTime . "
	                + "acacia:Session_" + session.getDate_Time() + " acacia:Duration \"" + session.getDuration() + "\"^^xsd:time . "
	                + "acacia:Session_" + session.getDate_Time() + " acacia:Observation_Sample_Rate \"" + session.getObservation_Sample_Rate() + "\"^^xsd:float . "
	                //+ "acacia:Session_" + session.getDate_Time() + " acacia:Has_Student acacia:" + session.getStudent() + " . "
	                + "acacia:Session_" + session.getDate_Time() + " acacia:Has_Teacher acacia:" + session.getTeacher() + " . "
	                + "acacia:Session_" + session.getDate_Time() + " acacia:Has_Sensory_Component acacia:" + session.getSensory_Component() + " . "
	                + "acacia:Session_" + session.getDate_Time() + " acacia:Belongs_to_Scenario acacia:" + session.getScenario() + " . "             
	                + "}";
			System.out.println(update);
			executeUpdate(update);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
