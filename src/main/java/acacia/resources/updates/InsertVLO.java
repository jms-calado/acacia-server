package acacia.resources.updates;

import java.io.IOException;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.util.URIUtil;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import acacia.core.JwtUser;
//import acacia.cdi.EventAlertBean;
import acacia.dataobjects.ConstantURIs;
import acacia.dataobjects.GlobalVar;
import acacia.dataobjects.ObservationObject;
import acacia.dataobjects.VloObject;
import acacia.resources.Resource;
import acacia.services.SparqlExecutor;
import io.dropwizard.auth.Auth;

//@Path("/insert/vlo/{vlo_type}")
@Path("/insert/vlo")
@Consumes(MediaType.APPLICATION_JSON)
public class InsertVLO extends Resource {
	
	public InsertVLO(SparqlExecutor qe) {
		super(qe);
		setUpValidator();
	}
	
	private Validator validator;	
/*	public Validator getValidator() {
		if (validator == null)
			setUpValidator();
		
		return validator;
	}*/
    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

	@POST
	//@RolesAllowed({"Admin", "Teacher", "Annalist"})
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(
			//@Auth JwtUser jwtUser,
			//@PathParam("vlo_type") @Pattern(regexp = "Course|Module|Other_Digital_Resource") @NotEmpty String vlo_type, 
			String jsonbody)
			throws JsonParseException, JsonMappingException, IOException {
		String msg = null;
		ObjectMapper mapper = new ObjectMapper();
		VloObject vlo = new VloObject();
		try {
			vlo = mapper.readValue(jsonbody, VloObject.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(422).build();
		}
		Set<ConstraintViolation<VloObject>> constraintViolations = validator.validate(vlo);
			
		if(constraintViolations.size() == 0){
			String vloID = URIUtil.encodePath(vlo.getName());
			String update = "INSERT DATA {" + 
					"acacia:VLO_" + vloID + " rdf:type acacia:Virtual_Learning_Object . " +
					"acacia:VLO_" + vloID + " acacia:Name \"" + vlo.getName() + "\"^^xsd:string . ";
			if(vlo.getDomain_Lexicon() != null)
				update += "acacia:VLO_" + vloID + " acacia:Domain_Lexicon acacia:" + vlo.getDomain_Lexicon() + " . ";
			if(vlo.getDescription() != null)
				update += "acacia:VLO_" + vloID + " acacia:Description \"" + vlo.getDescription() + "\"^^xsd:string . ";
			if(vlo.getUrl() != null)
				update += "acacia:VLO_" + vloID + " acacia:URL \"" + vlo.getUrl() + "\"^^xsd:string . ";
			update = update + "}";
			
			System.out.println(update);
			executeUpdate(ConstantURIs.prefixes + update);

			return Response.ok("{\"VLO_ID\":\"VLO_" + vloID + "\"}", MediaType.APPLICATION_JSON).status(201).build();
			
		}else{
			for (ConstraintViolation<VloObject> cv : constraintViolations) {
				msg = cv.getPropertyPath() + " : " + cv.getMessage();
				System.out.println("Validator Error O: " + msg);
			}
			return Response.status(422).build();
		}
	}
}
