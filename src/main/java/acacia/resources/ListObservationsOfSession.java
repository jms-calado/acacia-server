package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/list/observations_of_session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListObservationsOfSession extends Resource {
	
	public ListObservationsOfSession(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public List<Map<String, String>> search(@Size(min = 1, max = 1)@NotEmpty List<String> session) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Individual "
		        + "WHERE {"
		        + "?y rdfs:subClassOf* acacia:Observation ."
		        + "?Individual rdf:type ?y ."
		        + "?Individual acacia:Belongs_to_Session ?z ."
		        + "FILTER regex(str(?z),'" + session.get(0) + "$','i') ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
