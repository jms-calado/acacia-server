package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.jena.query.ResultSet;
import org.hibernate.validator.constraints.NotEmpty;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/find/{user_type}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FindUser extends Resource {
	
	public FindUser(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public List<Map<String, String>> search(@PathParam("user_type") @Pattern(regexp = "Student|Teacher") @NotEmpty String user_type, @Size(min = 1, max = 1)@NotEmpty List<String> name) throws FileNotFoundException {
		String query = ConstantURIs.prefixes + 
				"SELECT ?" + user_type + " "
                + "WHERE {"
                + "?" + user_type + " rdf:type acacia:" + user_type + " ."
                + "?" + user_type + " acacia:Name ?name .\n"
                + "FILTER regex(?name,'" + name.get(0) + "$','i') ."
                + "}";
		System.out.println(query);
		ResultSet rs = executeQuery(query);	
		return buildResult(rs);
	}

}
