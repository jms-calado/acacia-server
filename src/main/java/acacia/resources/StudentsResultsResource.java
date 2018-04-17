package acacia.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import acacia.dao.StudentsResultsDAO;

@Path("/plot2/student_session/{class_type}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentsResultsResource {

	private final StudentsResultsDAO studentsResultsDAO;
	
	public StudentsResultsResource(StudentsResultsDAO studentsResultsDAO) {
		this.studentsResultsDAO = studentsResultsDAO;
	}

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	public Response query(
			//@Auth JwtUser jwtUser,
			String jsonbody, 
			@PathParam("class_type") @Pattern(regexp = "Emotion|Behaviour|Affect") @NotEmpty String class_type) 
			throws JsonParseException, JsonMappingException, IOException, FileNotFoundException {

		try (JsonReader reader = Json.createReader(new StringReader(jsonbody))) {
            JsonObject jsonMessage = reader.readObject();
            String session = jsonMessage.getString("Session");
            String student = jsonMessage.getString("Student");
            String result = studentsResultsDAO.result(student, session);
			//System.out.println(result);
			return Response.ok(result, MediaType.APPLICATION_JSON).status(200).build();
		}
		catch (JsonException e){
			String msg = e.getMessage();
			System.out.println("JsonException: " + msg);
			return Response.status(422).build();
		}
	}
	
}
