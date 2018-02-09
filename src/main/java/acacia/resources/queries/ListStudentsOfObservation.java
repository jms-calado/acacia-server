package acacia.resources.queries;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import acacia.core.JwtUser;
import acacia.dataobjects.ConstantURIs;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

@Path("/list/students_of_observation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListStudentsOfObservation extends Resource {
	
	public ListStudentsOfObservation(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public List<Map<String, String>> search(
			//@Auth JwtUser jwtUser,
			@Size(min = 1, max = 1)@NotEmpty List<String> observation) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
		        "SELECT ?Individual ?Name "
		        + "WHERE {"
		        + "?y rdfs:subClassOf* acacia:Observation ."
		        + "?x rdf:type ?y ."
		        + "FILTER regex(str(?x),'" + observation.get(0) + "$','i') ."
		        + "?x acacia:Has_Student ?Individual ."
		        + "?Individual acacia:Name ?Name ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
