package acacia.resources;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.UserObject;
import acacia.services.SparqlExecutor;

@Path("/insert/{user_type}")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertUser extends Resource {
	
	public InsertUser(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public void insert(String jsonbody, @PathParam("user_type") @Pattern(regexp = "Student|Teacher|Admin|Annalist") @NotEmpty String user_type) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		UserObject user = new UserObject();
		try {
			user = mapper.readValue(jsonbody, UserObject.class);

			String update = ConstantURIs.prefixes + 
					"INSERT DATA {"
	                + "acacia:" + user_type + "_" + user.getID() + " rdf:type acacia:" + user_type + " . "
	                + "acacia:" + user_type + "_" + user.getID() + " acacia:Name \"" + user.getName() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + user.getID() + " acacia:Age \"" + user.getAge() + "\"^^xsd:int . "
	                + "acacia:" + user_type + "_" + user.getID() + " acacia:Gender \"" + user.getGender() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + user.getID() + " acacia:Education_Degree \"" + user.getEducation_Degree() + "\"^^xsd:string . "
	                + "acacia:" + user_type + "_" + user.getID() + " acacia:ID \"" + user.getID() + "\"^^xsd:int . "
	                + "}";
			System.out.println(update);
			executeUpdate(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
