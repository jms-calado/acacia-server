package acacia.resources.queries;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

@Path("/plot/issue")
@Consumes(MediaType.APPLICATION_JSON)
public class PlotIssue extends Resource {
	
	public PlotIssue(SparqlExecutor qe) {
		super(qe);
	}
    
	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public Response insert(
			//@Auth JwtUser jwtUser,
			@Size(min = 1, max = 1)@NotEmpty List<String> issue) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {

		String query = "SELECT ?student_id ?class_type ?session_id ?value "
				+ "WHERE { "
				+ "?class_type rdfs:subClassOf acacia:Observation . "
				+ "?b rdf:type ?class_type . "
				+ "?b acacia:Has_Student ?student_i . "
				+ "?b acacia:Belongs_to_Session ?session_id . "
				+ "?student_issue_id rdf:type acacia:Student_Issue . "
				+ "?student_issue_id acacia:Has_Issue ?issue . "
				+ "filter regex(str(?issue), '" + issue.get(0) + "', 'i') . "
				+ "?student_issue_id acacia:Belongs_to_Observation ?b . "
				+ "?student_issue_id acacia:Value ?value . "
				+ "?student_i acacia:Name ?student_id . "
				+ "} "
				+ "group by ?student_id ?class_type ?session_id ?value "
				+ "order by ?student_id ?class_type ?session_id ";

		System.out.println(query);
		ResultSet rs = executeQuery(ConstantURIs.prefixes + query);
		List<Map<String, String>> list = buildResult(rs);
		
		if (list.isEmpty()) {
			return Response.ok("{\"Error\":\"No Data Found\"}", MediaType.APPLICATION_JSON).status(200).build();
		}else {
			String lastStudent = null;
			String lastClass = null;
			JsonObjectBuilder jObjB_0 = Json.createObjectBuilder();
			JsonObjectBuilder jObjB_1 = Json.createObjectBuilder();
	        JsonObjectBuilder jObjB_2 = Json.createObjectBuilder();
			
			for(Map<String, String> map : list) {			
				String student = map.get("student_id");
				String class_type = map.get("class_type");
				String session_id = map.get("session_id");
				String value = map.get("value");
				
				if(student.equals(lastStudent)) {
					if(class_type.equals(lastClass)) {
						jObjB_2.add(session_id, value);
					}else {
						if(lastClass!=null) {
							JsonObject jobj = jObjB_2.build();//limpa o builder?							
							jObjB_1.add(lastClass, jobj);
						}
						lastClass = class_type;
						jObjB_2.add(session_id, value);
					}						
				}else {
					if(lastStudent!=null) {
						if(lastClass!=null) {
							JsonObject jobj = jObjB_2.build();							
							jObjB_1.add(lastClass, jobj);
						}						
						JsonObject jobj = jObjB_1.build();
						jObjB_0.add(lastStudent, jobj);						
						lastClass = class_type;
					}
					lastStudent = student;
					if(class_type.equals(lastClass)) {
						jObjB_2.add(session_id, value);
					}else {
						if(lastClass!=null) {
							JsonObject jobj = jObjB_2.build();							
							jObjB_1.add(lastClass, jobj);
						}
						lastClass = class_type;
						jObjB_2.add(session_id, value);
					}						
				}
				
			}
			JsonObject jobj1 = jObjB_2.build();
			jObjB_1.add(lastClass, jobj1);

			JsonObject jobj2 = jObjB_1.build();
			jObjB_0.add(lastStudent, jobj2);
			
			JsonObject jsonObj = jObjB_0.build();

			return Response.ok(jsonObj.toString(), MediaType.APPLICATION_JSON).status(200).build();
		}
	}

}
