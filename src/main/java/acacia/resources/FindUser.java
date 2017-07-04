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
import acacia.services.QueryExecutor;

@Path("/find/{user}/{name}")
@Produces(MediaType.APPLICATION_JSON)
public class FindUser extends Resource {
	
	public FindUser(QueryExecutor qe) {
		super(qe);
	}

	@GET
	public List<Map<String, String>> search(@PathParam("user") @Pattern(regexp = "Student|Teacher") @NotEmpty String user, @PathParam("name") @NotEmpty String name) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
				"SELECT ?" + user + " "
                + "WHERE {"
                + "?" + user + " rdf:type onto:" + user + " ."
                + "?" + user + " onto:Name ?name .\n"
                + "FILTER regex(?name,'" + name + "$','i') ."
                + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
