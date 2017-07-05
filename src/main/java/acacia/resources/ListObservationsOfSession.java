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

@Path("/list_observations_of_session/{session}")
@Produces(MediaType.APPLICATION_JSON)
public class ListObservationsOfSession extends Resource {
	
	public ListObservationsOfSession(QueryExecutor qe) {
		super(qe);
	}

	@GET
	public List<Map<String, String>> search(@PathParam("session") @NotEmpty String session) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Individual "
		        + "WHERE {"
		        + "?y rdfs:subClassOf* acacia:Session ."
		        + "?x rdf:type ?y ."
		        + "FILTER regex(str(?x),'" + session + "$','i') ."
		        + "?x acacia:Has_Observations ?Individual ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
