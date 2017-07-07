package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/insert/{user_type}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InsertUser extends Resource {
	
	public InsertUser(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public void search(@Size(min = 5, max = 5) List<String> keywords, @PathParam("user_type") @Pattern(regexp = "Student|Teacher|Admin|Annalist") @NotEmpty String user_type) throws FileNotFoundException {
		String update = ConstantURIs.prefixes + 
				"INSERT DATA {"
                + "acacia:" + user_type + "_" + keywords.get(4) + " rdf:type acacia:" + user_type + " . "
                + "acacia:" + user_type + "_" + keywords.get(4) + " acacia:Name \"" + keywords.get(0) + "\"^^xsd:string . "
                + "acacia:" + user_type + "_" + keywords.get(4) + " acacia:Age \"" + keywords.get(1) + "\"^^xsd:int . "
                + "acacia:" + user_type + "_" + keywords.get(4) + " acacia:Gender \"" + keywords.get(2) + "\"^^xsd:string . "
                + "acacia:" + user_type + "_" + keywords.get(4) + " acacia:Education_Degree \"" + keywords.get(3) + "\"^^xsd:string . "
                + "acacia:" + user_type + "_" + keywords.get(4) + " acacia:ID \"" + keywords.get(4) + "\"^^xsd:int . "
                + "}";
		System.out.println(update);
		executeUpdate(update);
	}

}
