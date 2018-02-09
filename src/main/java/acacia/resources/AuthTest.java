package acacia.resources;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import acacia.core.JwtUser;
import io.dropwizard.auth.Auth;

@Path("/authtest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthTest {
		
	@GET
	@RolesAllowed("Admin")
	public String test(@Auth JwtUser jwtUser) {
		return "{\"Server Says\" : \"Your JWT token works!\"}";		
	}

}