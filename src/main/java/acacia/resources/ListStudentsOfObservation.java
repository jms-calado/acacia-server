package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import acacia.dataobjects.ConstantURIs;
import acacia.services.QueryExecutor;

@Path("/list_students_of_observation/{observation}")
@Produces(MediaType.APPLICATION_JSON)
public class ListStudentsOfObservation extends Resource {
	
	public ListStudentsOfObservation(QueryExecutor qe) {
		super(qe);
	}

	@GET
	public List<Map<String, String>> search(@PathParam("observation") @NotEmpty String observation) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Individual ?Name "
		        + "WHERE {"
		        + "?y rdfs:subClassOf* acacia:Observation ."
		        + "?x rdf:type ?y ."
		        + "FILTER regex(str(?x),'" + observation + "$','i') ."
		        + "?x acacia:Has_Student ?Individual ."
		        + "?Individual acacia:Name ?Name ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
