package acacia.resources.queries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import acacia.core.JwtUser;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/plot/student_session/{class_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class PlotStudentSession extends Resource {
	
	public PlotStudentSession(SparqlExecutor qe) {
		super(qe);
	}
    
	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public Response insert(
			//@Auth JwtUser jwtUser,
			String jsonbody, 
			@PathParam("class_type") @Pattern(regexp = "Emotion|Behaviour|Affect") @NotEmpty String class_type) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {

		try (JsonReader reader = Json.createReader(new StringReader(jsonbody))) {
            JsonObject jsonMessage = reader.readObject();
            String session = jsonMessage.getString("Session");
            String student = jsonMessage.getString("Student");
			String query = "SELECT ?Observation ?Property ?Value " + 
					"WHERE { " + 
					"?y rdfs:subClassOf* acacia:" + class_type + " ." + 
					"?" + class_type + " rdf:type ?y ." + 
					"?" + class_type + " acacia:Belongs_to_Observation ?Observation ." + 
					"?Observation acacia:Has_Student ?Student ." + 
					"FILTER regex(str(?Student), '" + student + "$', 'i') ." + 
					"?Observation acacia:Belongs_to_Session ?Session ." + 
					"FILTER regex(str(?Session), '" + session + "', 'i') ." + 
					"?Observation acacia:Date_Time ?DateTime ." +
					"?" + class_type + " ?Property ?Value ." + 
					"?Property a owl:DatatypeProperty ." + 
					"}" +
					"GROUP BY ?Observation ?Property ?Value ?DateTime " +
					"ORDER BY ASC(?DateTime)";
			
			System.out.println(query);
			ResultSet rs = executeQuery(ConstantURIs.prefixes + query);
			List<Map<String, String>> list = buildResult(rs);
			//System.out.println(list.toString());
			if (list.isEmpty()) {
				return Response.ok("{\"Error\":\"No Data Found\"}", MediaType.APPLICATION_JSON).status(200).build();
			}else {
				String lastObservation = null;
				JsonObjectBuilder obj = Json.createObjectBuilder();
		        JsonObjectBuilder builder = Json.createObjectBuilder();
				for(Map<String, String> map : list) {
					//System.out.println(map.toString());				
					String observation = map.get("Observation");
					String property = map.get("Property");
					String value = map.get("Value");
					if(observation.equals(lastObservation)) {
						builder.add(property, value);
					}else {
						if(lastObservation!=null) {
							JsonObject jobj = builder.build();					
							//System.out.println(jobj);
							obj.add(lastObservation, jobj);
						}
						lastObservation = observation;
						builder.add(property, value);
					}
				}
				JsonObject jobj = builder.build();
				//System.out.println(jobj);
				//obj.entrySet().forEach(e -> builder.add(e.getKey(), e.getValue()));
				obj.add(lastObservation, jobj);
				JsonObject jsonObj = obj.build();
				//System.out.println(jsonObj);
				return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).status(200).build();
			}
		}
		catch (JsonException e){
			String msg = e.getMessage();
			System.out.println("JsonException: " + msg);
			return Response.status(422).build();
		}
	}

}
