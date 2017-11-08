package acacia.resources;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.dataobjects.AffectObject;
import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/plot/student/{class_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class PlotStudent extends Resource {
	
	public PlotStudent(SparqlExecutor qe) {
		super(qe);
	}
	    
	@POST
	public Response insert(@PathParam("class_type") @Pattern(regexp = "Emotion|Behaviour|Affect") @NotEmpty String class_type, @Size(min = 1, max = 1)@NotEmpty List<String> student) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
	
		String query = ConstantURIs.prefixes +
				"SELECT ?Session ?Property (AVG(?Value) AS ?session_average)  " +
				"WHERE {  " +
				"?Observation acacia:Has_Student ?Student . " +
				"FILTER regex(str(?Student), '" + student.get(0) + "$', 'i') . " +
				"?" + class_type + " acacia:Belongs_to_Observation ?Observation . " +
				"?y rdfs:subClassOf* acacia:" + class_type + " . " +
				"?" + class_type + " rdf:type ?y . " +
				"?Observation acacia:Belongs_to_Session ?Session . " +
				"?" + class_type + " ?Property ?Value . " +
				"?Property a owl:DatatypeProperty . " +
				"} " +
				"GROUP BY ?Session ?Property " +
				"ORDER BY ASC(?Session) ";
		System.out.println(query);
		ResultSet rs = executeQuery(query);
		List<Map<String, String>> list = buildResult(rs);
		//System.out.println(list.toString());			
		String lastObservation = null;
		JsonObjectBuilder obj = Json.createObjectBuilder();
        JsonObjectBuilder builder = Json.createObjectBuilder();
		for(Map<String, String> map : list) {
			//System.out.println(map.toString());				
			String observation = map.get("Session");
			String property = map.get("Property");
			String value = map.get("session_average");
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
