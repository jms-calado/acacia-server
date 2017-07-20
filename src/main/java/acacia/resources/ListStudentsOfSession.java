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

@Path("/list/students_of_session")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListStudentsOfSession extends Resource {
	
	public ListStudentsOfSession(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public List<Map<String, String>> search(@Size(min = 1, max = 1)@NotEmpty List<String> session) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Individual ?Name "
		        + "WHERE {"
		        + "?y rdfs:subClassOf* acacia:Session ."
		        + "?x rdf:type ?y ."
		        + "FILTER regex(str(?x),'" + session.get(0) + "$','i') ."
		        + "?x acacia:Has_Student ?Individual ."
		        + "?Individual acacia:Name ?Name ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}