package acacia.resources;

import java.io.FileNotFoundException;
import java.util.List;

import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import acacia.dataobjects.ConstantURIs;
import acacia.services.SparqlExecutor;

@Path("/insert/{session}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InsertSession extends Resource {
	
	public InsertSession(SparqlExecutor qe) {
		super(qe);
	}

	@POST
	public void search(@Size(min = 7, max = 7) List<String> keywords) throws FileNotFoundException {
		String update = ConstantURIs.prefixes + 
				"INSERT DATA {"
				+ "acacia:Session_" + keywords.get(0) + " rdf:type acacia:Session . "
				+ "acacia:Session_" + keywords.get(0) + " acacia:Date_Time \"" + keywords.get(0) + "\"^^xsd:dateTime . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Duration \"" + keywords.get(1) + "\"^^xsd:time . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Observation_Sample_Rate \"" + keywords.get(2) + "\"^^xsd:float . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Has_Student acacia:" + keywords.get(3) + " . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Has_Teacher acacia:" + keywords.get(4) + " . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Has_Sensory_Component acacia:" + keywords.get(5) + " . "
                + "acacia:Session_" + keywords.get(0) + " acacia:Belongs_to_Scenario acacia:" + keywords.get(6) + " . "
                //+ "acacia:Session_" + keywords.get(0) + " acacia:Has_Observations acacia:" + keywords.get(7) + " . "                
                + "}";
		System.out.println(update);
		executeUpdate(update);
	}

}
