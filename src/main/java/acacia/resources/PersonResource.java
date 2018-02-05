package acacia.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import acacia.core.Person;
import acacia.core.BasicUser;
import acacia.core.JwtUser;
import acacia.dao.PersonDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/person/{id}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

	private final PersonDAO personDAO;
	
	public PersonResource(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@GET
@RolesAllowed("Admin")
	@UnitOfWork
	public Person getPerson(@Auth JwtUser jwtUser, @PathParam("id") LongParam id) {
		Long ident = id.get();
		return personDAO.findById(ident).orElseThrow(() -> new NotFoundException("JwtUser not found."));		
	}

}