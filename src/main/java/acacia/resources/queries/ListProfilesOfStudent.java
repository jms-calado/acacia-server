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

@Path("/list/profiles_of_student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListProfilesOfStudent extends Resource {
	
	public ListProfilesOfStudent(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist", "Student"})
	public List<Map<String, String>> search(
			//@Auth JwtUser jwtUser,
			@Size(min = 1, max = 1)@NotEmpty List<String> student) throws FileNotFoundException {
		String query = "SELECT ?Profile ?Individual "
		        + "WHERE {"
		        + "?Profile rdfs:subClassOf* acacia:Profile ."
		        + "?Individual rdf:type ?Profile ."
		        + "?Individual acacia:Belongs_to_User ?z ."
		        + "FILTER regex(str(?z),'" + student.get(0) + "$','i') ."
		        + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(ConstantURIs.prefixes + query);	
		return buildResult(rs);
	}

}
