package acacia.auth;

import acacia.core.JwtUser;
import io.dropwizard.auth.Authorizer;

public class ResourceAuthorizer implements Authorizer<JwtUser>{

	@Override
	public boolean authorize(JwtUser jwtUser, String role) {
		if(jwtUser == null) {
			System.out.println("null jwtUser");
			return false;
		}
		if(jwtUser.getRole() == null) {
			System.out.println("null jwtUser role");
			return false;
		}
		return jwtUser.getRole().contains(role);
	}

}
