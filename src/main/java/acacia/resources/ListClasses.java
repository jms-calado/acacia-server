package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/list/{class_type}")
@Produces(MediaType.APPLICATION_JSON)
public class ListClasses extends Resource {
	
	public ListClasses(SparqlExecutor qe) {
		super(qe);
	}

	@GET
	public List<Map<String, String>> search(@PathParam("class_type") @Pattern(regexp = "Student|Teacher|Admin|Annalist|Session|Observation|Human_Observation|Digital_Observation|Emotion|Behavior|Affect") @NotEmpty String class_type) throws FileNotFoundException {
		String query=null;
		switch (class_type){
		case "Student" : 
		case "Teacher" :
		case "Admin" :
		case "Annalist" :
			query = ConstantURIs.prefixes + 
			" SELECT ?Name "
			+ "WHERE { "
			+ "?" + class_type + " rdf:type acacia:" + class_type + " . "
			+ "?" + class_type + " acacia:Name ?Name . "
			+ "} ORDER BY ?" + class_type + "";
            break;
		case "Observation" :
			query = ConstantURIs.prefixes + 
			" SELECT ?" + class_type + " "
			+ "WHERE { "
			+ "?y rdfs:subClassOf* acacia:Observation ."
			+ "?" + class_type + " rdf:type ?y "
			+ "} ORDER BY ?" + class_type + "";
            break;
		default :
			query = ConstantURIs.prefixes + 
			" SELECT ?" + class_type + " "
			+ "WHERE { "
			+ "?" + class_type + " rdf:type acacia:" + class_type + " . "
			+ "} ORDER BY ?" + class_type + "";
            break;
		}
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
