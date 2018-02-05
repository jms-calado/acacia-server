package acacia.resources.queries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

@Path("/plot/session/{class_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class PlotSession extends Resource {
	
	public PlotSession(SparqlExecutor qe) {
		super(qe);
	}
	    
	@POST
	@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public Response insert(
			@Auth JwtUser jwtUser,
			@PathParam("class_type") @Pattern(regexp = "Emotion|Behaviour|Affect") @NotEmpty String class_type, 
			@Size(min = 1, max = 1)@NotEmpty List<String> session) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
	
		String query = ConstantURIs.prefixes +
				"SELECT ?Student ?Property (AVG(?Value) AS ?student_average)  " +
				"WHERE {  " +
				"?Observation acacia:Belongs_to_Session ?Session . " +
				"FILTER regex(str(?Session), '" + session.get(0) + "', 'i') . " +
				"?" + class_type + " acacia:Belongs_to_Observation ?Observation . " +
				"?y rdfs:subClassOf* acacia:" + class_type + " . " +
				"?" + class_type + " rdf:type ?y . " +
				"?Observation acacia:Has_Student ?Student . " +
				"?" + class_type + " ?Property ?Value . " +
				"?Property a owl:DatatypeProperty . " +
				"} " +
				"GROUP BY ?Student ?Property " +
				"ORDER BY ASC(?Student) ";
		System.out.println(query);
		ResultSet rs = executeQuery(query);
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
				String observation = map.get("Student");
				String property = map.get("Property");
				String value = map.get("student_average");
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

}
