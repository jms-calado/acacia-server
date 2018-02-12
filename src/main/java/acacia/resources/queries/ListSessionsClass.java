package acacia.resources.queries;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

@Path("/list/sessions_class")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ListSessionsClass extends Resource {
	
	public ListSessionsClass(SparqlExecutor qe) {
		super(qe);
	}

	@GET
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public List<Map<String, String>> search(
			//@Auth JwtUser jwtUser
			) throws FileNotFoundException {
		String query = "SELECT ?Session ?Class "
		        + "WHERE {"
		        + "?Session rdf:type acacia:Session . "
		        + "?Session acacia:Has_Class ?Class . "
		        + "} ORDER BY ?Session ";
		System.out.println(query);
		ResultSet rs = executeQuery(ConstantURIs.prefixes + query);	
		return buildResult(rs);
	}

}
