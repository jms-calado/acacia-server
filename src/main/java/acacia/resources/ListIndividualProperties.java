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

@Path("/list_individual_properties/{individual}")
@Produces(MediaType.APPLICATION_JSON)
public class ListIndividualProperties extends Resource {
	
	public ListIndividualProperties(QueryExecutor qe) {
		super(qe);
	}

	@GET
	public List<Map<String, String>> search(@PathParam("individual") @NotEmpty String individual) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Property ?Value "
		        + "WHERE {"
		        + "acacia:" + individual + " ?Property ?Value ."
		        + "{?Property a owl:DatatypeProperty }"
		        + "UNION {?Property a owl:ObjectProperty } ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
